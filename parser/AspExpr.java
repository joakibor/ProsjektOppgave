package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspExpr extends AspSyntax {
    //-- Must be changed in part 2:
    ArrayList<AspAndTest> andTests = new ArrayList<>();

    AspExpr(int n) {
	super(n);
    }
	
	public static AspExpr parse(Scanner s)
	{
		Main.log.enterParser("expr");
		
		AspExpr ae = new AspExpr(s.curLineNum());
		
		while(true)
		{
			ae.andTests.add(AspAndTest.parse(s));
			if (s.curToken().kind != orToken) break;
			skip(s, orToken);
		}
		
		Main.log.leaveParser("expr");
		return ae;
	}


    @Override
    public void prettyPrint() {
	//-- Must be changed in part 2:
    }


    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
	//-- Must be changed in part 3:
	return null;
    }
}
