package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspStringLiteral extends AspAtom
{
	AspStringLiteral(int n)
	{
		super(n);
	}
	
	static AspStringLiteral parse(Scanner s)
	{
		Main.log.enterParser("string literal");
		
		AspStringLiteral asl = new AspStringLiteral(s.curLineNum());
		skip(s, nameToken);
		
		Main.log.leaveParser("string literal");
		return asl;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
