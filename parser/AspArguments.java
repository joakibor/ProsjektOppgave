package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;

public class AspArguments extends AspPrimarySuffix
{
	ArrayList<AspExpr> arguments = new ArrayList<>();
	
    AspArguments(int n) {
        super(n);
    }
	
	static AspArguments parse(Scanner s)
	{
		Main.log.enterParser("primary suffix");
		Main.log.enterParser("arguments");
		AspArguments aa = new AspArguments(s.curLineNum());
		aa.arguments.add(AspExpr.parse(s));
		
		Main.log.leaveParser("arguments");
		Main.log.leaveParser("primary suffix");
		return aa;
	}

    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
