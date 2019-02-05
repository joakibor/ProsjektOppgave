package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspReturnStmt extends AspStmt
{
	ArrayList<AspExpr> exprs = new ArrayList<>();
	
    AspReturnStmt(int n)
	{
        super(n);
    }
    
    public static AspReturnStmt parse(Scanner s)
    {
        Main.log.enterParser("return");
		AspReturnStmt ars = new AspReturnStmt(s.curLineNum());
        
        skip(s, returnToken);
		ars.exprs.add(AspExpr.parse(s));
		skip(s, newLineToken);
        
        Main.log.leaveParser("return");
        return ars;
    }
}
