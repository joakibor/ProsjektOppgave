package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;

import java.util.ArrayList;

public class AspSubscription extends AspPrimarySuffix
{
	ArrayList<AspExpr> arguments = new ArrayList<>();
	
    AspSubscription(int n) {
        super(n);
    }
    
    static AspSubscription parse(Scanner s)
    {
		Main.log.enterParser("primary suffix");
		Main.log.enterParser("arguments");
		
		AspSubscription as = new AspSubscription(s.curLineNum());
        as.arguments.add(AspExpr.parse(s));
	
		Main.log.leaveParser("arguments");
		Main.log.leaveParser("primary suffix");
        return as;
    }

    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
