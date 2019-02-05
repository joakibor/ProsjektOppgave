package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSuite extends AspSyntax {
	
	ArrayList<AspStmt> stmts = new ArrayList<>();
	
    AspSuite(int n)
	{
        super(n);
    }
	
	
	public static AspSuite parse(Scanner s)
	{
		Main.log.enterParser("suite");
		AspSuite as = new AspSuite(s.curLineNum());
		skip(s, newLineToken);
		skip(s, indentToken);
		
		while(s.curToken().kind != dedentToken)
			as.stmts.add(AspStmt.parse(s));
		
		skip(s, dedentToken);
		Main.log.leaveParser("suite");
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
