package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFuncDef extends AspExprStmt
{
	ArrayList<AspName> names = new ArrayList<>();
	ArrayList<AspSuite> suites = new ArrayList<>();

    AspFuncDef(int n) {
        super(n);
    }
    
    public static AspFuncDef parse(Scanner s)
    {
        Main.log.enterParser("func def");
        AspFuncDef afd = new AspFuncDef(s.curLineNum());
        
        skip(s, defToken);
		afd.names.add(AspName.parse(s));
		skip(s, leftParToken);
		
		while(s.curToken().kind != rightParToken)
		{
			if(s.curToken().kind == commaToken)
				skip(s, commaToken);
			
			afd.names.add(AspName.parse(s));
		}
	
		skip(s, rightParToken);
		skip(s, colonToken);
		afd.suites.add(AspSuite.parse(s));
		
        Main.log.leaveParser("func def");
        return afd;
    }
}
