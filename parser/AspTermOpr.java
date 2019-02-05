package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax
{
    AspTermOpr(int n) {
        super(n);
    }
    
    
    static AspTermOpr parse(Scanner s)
    {
		Main.log.enterParser("term opr");
		AspTermOpr ato = new AspTermOpr(s.curLineNum());
	
		if(s.curToken().kind == plusToken)
			skip(s, plusToken);
		else if(s.curToken().kind == minusToken)
			skip(s, minusToken);
	
		Main.log.leaveParser("term opr");
        return ato;
    }
    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
