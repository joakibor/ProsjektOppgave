package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspExprStmt  extends AspStmt{
	
	ArrayList<AspExpr> exprs = new ArrayList<>();

    AspExprStmt(int n)
	{
        super(n);
    }
    
    public static AspExprStmt parse(Scanner s)
    {
        Main.log.enterParser("Expr statement");
		AspExprStmt aes = new AspExprStmt(s.curLineNum());
	
		aes.exprs.add(AspExpr.parse(s));
        skip(s, newLineToken);
        
        Main.log.leaveParser("Expr statement");
        return aes;
    }
}
