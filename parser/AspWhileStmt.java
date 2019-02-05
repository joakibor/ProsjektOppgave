package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspWhileStmt extends AspStmt
{
	ArrayList<AspExpr> exprs = new ArrayList<>();
	ArrayList<AspSuite> suites = new ArrayList<>();
	
    AspWhileStmt(int n)
	{
        super(n);
    }
    
    public static AspWhileStmt parse(Scanner s)
    {
        Main.log.enterParser("while");
		AspWhileStmt aws = new AspWhileStmt(s.curLineNum());
        
        skip(s, whileToken);
		aws.exprs.add(AspExpr.parse(s));
		skip(s, colonToken);
		aws.suites.add(AspSuite.parse(s));
        
        Main.log.leaveParser("while");
        return aws;
    }
}
