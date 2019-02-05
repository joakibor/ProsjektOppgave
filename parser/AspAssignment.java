package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspAssignment extends AspStmt
{
	ArrayList<AspSubscription> subscriptions = new ArrayList<>();
	ArrayList<AspExpr> exprs = new ArrayList<>();
	
    AspAssignment(int n) {
        super(n);
    }
	
	public static AspAssignment parse(Scanner s)
	{
		Main.log.enterParser("assignment");
		AspAssignment aa = new AspAssignment(s.curLineNum());
		
		while(s.curToken().kind != equalToken)
			aa.subscriptions.add(AspSubscription.parse(s));
		
		skip(s, equalToken);
		aa.exprs.add(AspExpr.parse(s));
		skip(s, newLineToken);
		
		Main.log.leaveParser("assignment");
		return aa;
	}
 
}
