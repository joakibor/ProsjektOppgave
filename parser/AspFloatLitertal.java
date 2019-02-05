package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFloatLitertal extends AspAtom
{
	AspFloatLitertal(int n)
	{
		super(n);
	}
	
	static AspFloatLitertal parse(Scanner s)
	{
		Main.log.enterParser("float literal");
		
		AspFloatLitertal afl = new AspFloatLitertal(s.curLineNum());
		skip(s, floatToken);
		
		Main.log.leaveParser("float literal");
		return afl;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
