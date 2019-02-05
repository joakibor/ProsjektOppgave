package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspStmt extends AspSyntax {
    
    ArrayList<AspStmt> statements = new ArrayList<>();
    
    AspStmt(int n)
    {
        super(n);
    }
    
    public static AspStmt parse(Scanner s)
    {
        Main.log.enterParser("stmt");
        AspStmt as = new AspStmt(s.curLineNum());
        
        switch(s.curToken().kind)
        {
            case nameToken:
                as.statements.add(AspAssignment.parse(s));
                break;
            case ifToken:
                as.statements.add(AspIfStmt.parse(s));
                break;
            case whileToken:
                as.statements.add(AspWhileStmt.parse(s));
                break;
            case passToken:
                as.statements.add(AspPassStmt.parse(s));
                break;
            case returnToken:
                as.statements.add(AspReturnStmt.parse(s));
                break;
            case defToken:
                as.statements.add(AspFuncDef.parse(s));
                break;
            default:
                as.statements.add(AspExprStmt.parse(s));
        }
        
        Main.log.leaveParser("stmt");
        return as;
    }
    
    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
