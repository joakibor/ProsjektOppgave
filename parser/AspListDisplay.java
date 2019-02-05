package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;

import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspListDisplay extends AspAtom
{
	ArrayList<AspAtom> atoms = new ArrayList<>();
	
	AspListDisplay(int n)
	{
		super(n);
	}
	
	
	static AspListDisplay scanner(Scanner s)
	{
		Main.log.enterParser("list display");
		AspListDisplay ald = new AspListDisplay(s.curLineNum());
		
		skip(s, leftBracketToken);
		
		while(s.curToken().kind != rightBracketToken)
		{
			ald.atoms.add(AspInnerExpr.parse(s));
			if(s.curToken().kind == commaToken)
				skip(s, commaToken);
			
		}
		
		skip(s, rightBracketToken);
		
		Main.log.leaveParser("list display");
		return ald;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
