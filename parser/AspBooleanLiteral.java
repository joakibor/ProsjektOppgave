package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBooleanLiteral extends AspAtom
{
	AspBooleanLiteral(int n)
	{
		super(n);
	}
	
	static AspBooleanLiteral parse(Scanner s)
	{
		Main.log.enterParser("boolean literal");
		
		AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
		
		if(s.curToken().kind == trueToken)
			skip(s, trueToken);
		else
			skip(s, falseToken);
		
		Main.log.leaveParser("boolean literal");
		return abl;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
