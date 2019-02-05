package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPassStmt extends AspStmt {
    AspPassStmt(int n)
    {
        super(n);
    }
    
    
    public static AspPassStmt parse(Scanner s)
    {
        Main.log.enterParser("pass");
        AspPassStmt as = new AspPassStmt(s.curLineNum());
        
        skip(s, passToken);
        skip(s, newLineToken);
        
        Main.log.leaveParser("pass");
        return as;
    }
}
