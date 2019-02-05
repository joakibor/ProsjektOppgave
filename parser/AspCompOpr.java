package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspCompOpr extends AspSyntax
{
	AspCompOpr(int n)
	{
		super(n);
	}
	
	static AspCompOpr parse(Scanner s)
	{
		Main.log.enterParser("comp opr");
		AspCompOpr aco = new AspCompOpr(s.curLineNum());
		
		switch(s.curToken().kind)
		{
			case lessToken:
				skip(s, lessToken);
				break;
			case greaterToken:
				skip(s, greaterToken);
				break;
			case doubleEqualToken:
				skip(s, doubleEqualToken);
				break;
			case lessEqualToken:
				skip(s, lessEqualToken);
				break;
			case greaterEqualToken:
				skip(s, greaterEqualToken);
				break;
			case notEqualToken:
				skip(s, notEqualToken);
				break;
		}
		
		Main.log.leaveParser("comp opr");
		return aco;
	}
	
	@Override
	void prettyPrint()
	{
	
	}
	
	@Override
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
