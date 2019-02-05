package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspDictDisplay extends AspAtom
{
	ArrayList<AspAtom> atoms = new ArrayList<>();
	
	AspDictDisplay(int n)
	{
		super(n);
	}
	
	static AspDictDisplay scanner(Scanner s)
	{
		Main.log.enterParser("dict display");
		AspDictDisplay add = new AspDictDisplay(s.curLineNum());
		
		skip(s, leftBraceToken);
		
		while(s.curToken().kind != rightBraceToken)
		{
			add.atoms.add(AspStringLiteral.parse(s));
			skip(s, colonToken);
			add.atoms.add(AspInnerExpr.parse(s));
			
			if(s.curToken().kind == commaToken)
				skip(s, commaToken);
		}
		
		skip(s, rightBraceToken);
		
		Main.log.leaveParser("dict display");
		return add;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
