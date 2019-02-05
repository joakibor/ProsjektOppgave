package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;

import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspName extends AspAtom
{
	AspName(int n)
	{
		super(n);
	}
	
	
	static AspName parse(Scanner s)
	{
		Main.log.enterParser("name");
		AspName an = new AspName(s.curLineNum());
		
		skip(s, nameToken);
		
		Main.log.leaveParser("name");
		return an;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
