package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
    private LineNumberReader sourceFile = null;
    private String curFileName;
    private ArrayList<Token> curLineTokens = new ArrayList<>();
    private int indents[] = new int[100];
    private int numIndents = 0;
    private final int tabDist = 4;
    private boolean doubleQuoteToken = false, singleQuoteToken = false,
			lastWasNum = false, negativeToken = false, floatMode = false;


    public Scanner(String fileName) {
		curFileName = fileName;
		indents[0] = 0;  numIndents = 1;
	
		try {
			sourceFile = new LineNumberReader(
					new InputStreamReader(
					new FileInputStream(fileName),
					"UTF-8"));
		} catch (IOException e) {
			scannerError("Cannot read " + fileName + "!");
		}
    }


    private void scannerError(String message) {
		String m = "Asp scanner error";
		if (curLineNum() > 0)
			m += " on line " + curLineNum();
		m += ": " + message;
	
		Main.error(m);
    }


    public Token curToken()
	{
		while (curLineTokens.isEmpty())
		{
			readNextLine();
		}
		return curLineTokens.get(0);
    }


    public void readNextToken()
	{
		if (!curLineTokens.isEmpty())
			curLineTokens.remove(0);
    }


    public boolean anyEqualToken()
	{
		for (Token t: curLineTokens) {
			if (t.kind == equalToken) return true;
		}
		return false;
    }


    private void readNextLine()
	{
		curLineTokens.clear();
		// Read the next line:
		String line = null;
		try
		{
			line = sourceFile.readLine();
			if (line == null)
			{
				sourceFile.close();
				sourceFile = null;
				
				//Add remainig dedents and eofToken
				for(int i : indents)
					if(i > 0)
						curLineTokens.add(new Token(dedentToken, curLineNum()));
				
				curLineTokens.add(new Token(eofToken,curLineNum()));
				
				for (Token t: curLineTokens)
					Main.log.noteToken(t);
				return;
			}
			else
				Main.log.noteSourceLine(curLineNum(), line);
		}
		catch (IOException e)
		{
			sourceFile = null;
			scannerError("Unspecified I/O error!");
		}
		
		//-- Must be changed in part 1:
		line = handleIndents(line);
		int indent = findIndent(line);
		
		//Ignore line if empty or comment
		if(indent == line.length()) return;
		else if(line.charAt(indent) == '#') return;
		
		char[] lineArray = line.toCharArray();
		boolean comment = false;
		StringBuilder token = new StringBuilder();
		
		//Iterates through the line and splits it into tokens
		for (int i = indent; i < lineArray.length; i++)
		{
			boolean hasLastChar = false, hasNextChar = false;
			if(i > 0) hasLastChar = true;
			if(i < lineArray.length-1) hasNextChar = true;
			char thisChar = lineArray[i];
			
			//Adds the char to token if it is reading a string literal encapsulated by ""
			if(doubleQuoteToken && thisChar != '"' )
			{
				token.append(thisChar);
				continue;
			}
			
			//Adds the char to token if it is reading a string literal encapsulated by ''
			if(singleQuoteToken && thisChar != '\'')
			{
				token.append(thisChar);
				continue;
			}
			
			//Handles float numbers
			if(thisChar == '.' && (hasLastChar && isDigit(lineArray[i-1])))
			{
				if(!floatMode)
				{
					token.append(thisChar);
					floatMode = true;
					continue;
				}
				else
					floatMode = false;
			}
			
			/*
			//This handles signed numbers, but the reference scanner does not
			if((thisChar == '-' || thisChar == '+') && (hasNextChar && isDigit(lineArray[i+1])))
			{
				if(!(hasLastChar && (isDigit(lineArray[i-1]) || isLetterAZ(lineArray[i-1]))))
				{
					token.append(thisChar);
					continue;
				}
			}
			*/
			
			//Handles operators and delimiters
			String specialToken = "";
			for (int j = i; j < lineArray.length; j++)
			{
				if(searchForToken(specialToken + lineArray[j]))
					specialToken += lineArray[j];
				else
					break;
			}
			
			if(specialToken.length() > 0)
			{
				addToken(token.toString());
				token = new StringBuilder();
				addToken(specialToken);
				i += specialToken.length()-1;
				continue;
			}
			
			//Handles the rest of the cases
			switch(lineArray[i])
			{
				case ' ':
				case '\t':
					addToken(token.toString());
					token = new StringBuilder();
					break;
					
				case '"':
					addToken(token.toString());
					token = new StringBuilder();
					doubleQuoteToken = !doubleQuoteToken;
					break;
					
				case '\'':
					addToken(token.toString());
					token = new StringBuilder();
					singleQuoteToken = !singleQuoteToken;
					break;
				
				case '#':
					addToken(token.toString());
					token = new StringBuilder();
					comment = true;
					break;
					
				default:
					token.append(lineArray[i]);
					break;
			}
			if(comment)
				break;
		}
		
		//Line iterated through or comment hit, closing statements
		if(token.length() > 0)
			addToken(token.toString());
		
		System.out.println(line);
		// Terminate line:
		curLineTokens.add(new Token(newLineToken,curLineNum()));
	
		for (Token t: curLineTokens)
			Main.log.noteToken(t);
    }
	
	//Determines type of token and adds it to curLineTokens
	private void addToken(String token)
	{
		//Token is string literal
		if(doubleQuoteToken || singleQuoteToken)
		{
			Token t = new Token(stringToken, curLineNum());
			t.stringLit = token;
			curLineTokens.add(t);
			return;
		}
		
		if(token.length() <= 0)
			return;
		
		//Token exits in TokenKind
		if(searchForToken(token))
		{
			for(TokenKind tk : EnumSet.range(andToken, slashEqualToken))
				if(token.equals(tk.image))
					curLineTokens.add(new Token(tk, curLineNum()));
			return;
		}
		
		//Iterates through token, checks if it is a float or int
		boolean intNum = true, floatNum = false;
		for (int i = 0; i < token.length(); i++)
		{
			char c = token.charAt(i);
			if(c == '.')
			{
				floatNum = true;
				intNum = false;
			}
			else if(!isDigit(c))
			{
				if(!(c == '+' || c == '-'))
				{
					floatNum = false;
					intNum = false;
					break;
				}
			}
		}
		
		Token t;
		//It is an int
		if(intNum)
		{
			int num;
			try
			{
				num = new Integer(token);
				t = new Token(integerToken, curLineNum());
				t.integerLit = num;
				curLineTokens.add(t);
				return;
			}
			catch(NumberFormatException e)
			{
				//Nvm, its a float
				floatNum = true;
			}
		}
		
		//It is a float
		if(floatNum)
		{
			t = new Token(floatToken, curLineNum());
			t.floatLit = new Float(token);
			curLineTokens.add(t);
			floatMode = false;
			return;
		}
		
		//It is nothing else, so must be a name
		t = new Token(nameToken, curLineNum());
		t.name = token;
		curLineTokens.add(t);
	}
	
	//Checks if token is a keyword, operator or delimiter
	private boolean searchForToken(String token)
	{
		if(token.length() > 0)
			for(TokenKind tk : EnumSet.range(andToken, slashEqualToken))
				if(token.equals(tk.image))
					return true;
		return false;
	}

    public int curLineNum()
	{
		return sourceFile!=null ? sourceFile.getLineNumber() : 0;
    }

    private int findIndent(String s)
	{
		int indent = 0;
	
		while (indent<s.length() && s.charAt(indent)==' ') indent++;
			return indent;
    }
	
	//Expands tabs and calculates indentation
	private String handleIndents(String line)
	{
		line = expandLeadingTabs(line);
		int indent = findIndent(line);
		if(indent == line.length()) return "";
		
		if(indent > indents[numIndents-1])
		{
			//Indent
			indents[numIndents] = indent;
			numIndents++;
			curLineTokens.add(new Token(indentToken,curLineNum()));
		}
		else
		{
			//Dedent(s)
			while(indent < indents[numIndents - 1])
			{
				indents[numIndents - 1] = 0;
				numIndents--;
				curLineTokens.add(new Token(dedentToken, curLineNum()));
			}
			
			if(indent != indents[numIndents - 1])
				scannerError("Indentation error at line: " + curLineNum());
		}
		return line;
	}

    private String expandLeadingTabs(String s)
	{
		String newS = "";
		for (int i = 0;  i < s.length();  i++) {
			char c = s.charAt(i);
			if (c == '\t') {
			do {
				newS += " ";
			} while (newS.length()%tabDist != 0);
			} else if (c == ' ') {
			newS += " ";
			} else {
			newS += s.substring(i);
			break;
			}
		}
		return newS;
    }

    private boolean isLetterAZ(char c) {
		return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
    }


    private boolean isDigit(char c) {
		return '0'<=c && c<='9';
    }


    public boolean isCompOpr() {
		TokenKind k = curToken().kind;
		//-- Must be changed in part 2:
		return false;
    }


    public boolean isFactorPrefix() {
		TokenKind k = curToken().kind;
		//-- Must be changed in part 2:
		return false;
    }


    public boolean isFactorOpr() {
		TokenKind k = curToken().kind;
		//-- Must be changed in part 2:
		return false;
    }
	

    public boolean isTermOpr() {
		TokenKind k = curToken().kind;
		//-- Must be changed in part 2:
		return false;
    }
}
