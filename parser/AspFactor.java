package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactor extends AspSyntax
{
	
	ArrayList<AspPrimary> primaries = new ArrayList<>();
	ArrayList<AspFactorPrefix> factorPrefixes = new ArrayList<>();
	ArrayList<AspFactorOpr> factorOprs = new ArrayList<>();
	
    AspFactor(int n) {
        super(n);
    }

    @Override
    void prettyPrint() {

    }
	
	static AspFactor parse(Scanner s)
	{
		Main.log.enterParser("factor");
		AspFactor af = new AspFactor(s.curLineNum());
		
		if(s.curToken().kind == minusToken  || s.curToken().kind == plusToken)
		{
			af.factorPrefixes.add(AspFactorPrefix.parse(s));
		}
		
		
		boolean opr = true;
		while(opr)
		{
			af.primaries.add(AspPrimary.parse(s));
			
			switch(s.curToken().kind)
			{
				case astToken:
				case slashToken:
				case percentToken:
				case doubleSlashToken:
					af.factorOprs.add(AspFactorOpr.parse(s));
				default:
					opr = false;
			}
		}
		
		Main.log.leaveParser("factor");
		return af;
	}

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
