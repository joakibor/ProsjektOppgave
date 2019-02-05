package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;

public class AspTerm extends AspSyntax
{
	ArrayList<AspFactor> factors = new ArrayList<>();
	ArrayList<AspTermOpr> termOprs = new ArrayList<>();
	
    AspTerm(int n) {
        super(n);
    }

    @Override
    void prettyPrint() {

    }
	
	static AspTerm parse(Scanner s)
	{
		Main.log.enterParser("term");
		AspTerm at = new AspTerm(s.curLineNum());
		
		boolean opr = true;
		while(opr)
		{
			at.factors.add(AspFactor.parse(s));
			
			switch(s.curToken().kind)
			{
				case plusToken:
				case minusToken:
					at.termOprs.add(AspTermOpr.parse(s));
				default:
					opr = false;
			}
		}
		
		Main.log.leaveParser("term");
		return at;
	}
	
	
    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
