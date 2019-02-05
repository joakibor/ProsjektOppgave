package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspInnerExpr extends AspAtom
{
	ArrayList<AspAndTest> andTests = new ArrayList<>();
	
	AspInnerExpr(int n)
	{
		super(n);
	}
	
	public static AspInnerExpr parse(Scanner s)
	{
		Main.log.enterParser("expr");
		
		AspInnerExpr ae = new AspInnerExpr(s.curLineNum());
		
		while(true)
		{
			ae.andTests.add(AspAndTest.parse(s));
			if (s.curToken().kind != orToken) break;
			skip(s, orToken);
		}
		
		Main.log.leaveParser("expr");
		return ae;
	}
	
	void prettyPrint()
	{
	
	}
	
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
