package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax
{
	ArrayList<AspComparison> comparisons = new ArrayList<>();
	
    AspNotTest(int n)
	{
        super(n);
    }
	
	static AspNotTest parse(Scanner s)
	{
		Main.log.enterParser("not test");
		
		AspNotTest ant = new AspNotTest(s.curLineNum());
		
		if(s.curToken().kind == notToken)
			skip(s, notToken);
		
		ant.comparisons.add(AspComparison.parse(s));
		
		Main.log.leaveParser("not test");
		return ant;
	}

    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
