package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;

import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNoneLiteral extends AspAtom
{
	AspNoneLiteral(int n)
	{
		super(n);
	}
	
	static AspNoneLiteral parse(Scanner s)
	{
		Main.log.enterParser("none literal");
		
		AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
		skip(s, noneToken);
		
		Main.log.leaveParser("none literal");
		return anl;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
