package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax {
    AspFactorPrefix(int n) {
        super(n);
    }

    @Override
    void prettyPrint() {

    }
    
    static AspFactorPrefix parse(Scanner s)
    {
		Main.log.enterParser("factor prefix");
		AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
		
		if(s.curToken().kind == plusToken)
			skip(s, plusToken);
		else if(s.curToken().kind == minusToken)
			skip(s, minusToken);
	
		Main.log.leaveParser("factor prefix");
		return afp;
    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
