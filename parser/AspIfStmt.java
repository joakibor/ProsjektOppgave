package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspIfStmt extends AspStmt
{
	ArrayList<AspExpr> exprs = new ArrayList<>();
	ArrayList<AspSuite> suites = new ArrayList<>();
	
    AspIfStmt(int n)
	{
        super(n);
    }
    
    public static AspIfStmt parse(Scanner s)
    {
        Main.log.enterParser("if statement");
		AspIfStmt ais = new AspIfStmt(s.curLineNum());
	
		skip(s, ifToken);
		
		do
		{
			ais.exprs.add(AspExpr.parse(s));
			skip(s, colonToken);
			ais.suites.add(AspSuite.parse(s));
		} while(s.curToken().kind == elifToken);
		
		if(s.curToken().kind == elseToken)
		{
			skip(s, elseToken);
			skip(s, colonToken);
			ais.suites.add(AspSuite.parse(s));
		}
		
        
        
        Main.log.leaveParser("if statement");
        return ais;
    }
}
