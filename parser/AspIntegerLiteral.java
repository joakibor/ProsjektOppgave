package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIntegerLiteral extends AspAtom
{
	AspIntegerLiteral(int n)
	{
		super(n);
	}
	
	static AspIntegerLiteral parse(Scanner s)
	{
		Main.log.enterParser("integer literal");
		
		AspIntegerLiteral ail = new AspIntegerLiteral(s.curLineNum());
		skip(s, integerToken);
		
		Main.log.leaveParser("integer literal");
		return ail;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
