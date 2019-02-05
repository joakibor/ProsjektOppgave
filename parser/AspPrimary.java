package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;

import java.util.ArrayList;

import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax
{
    ArrayList<AspAtom> atoms = new ArrayList<>();
	ArrayList<AspPrimarySuffix> primarySuffix = new ArrayList<>();
    
    
    AspPrimary(int n) {
        super(n);
    }
    
    static AspPrimary parse(Scanner s)
    {
        Main.log.enterParser("primary");
		Main.log.enterParser("atom");
		AspPrimary ap = new AspPrimary(s.curLineNum());
	
		switch(s.curToken().kind)
		{
			case nameToken:
				ap.atoms.add(AspName.parse(s));
				
				if(s.curToken().kind == leftParToken)
				{
					skip(s, leftParToken);
					while(s.curToken().kind != rightParToken)
					{
						ap.primarySuffix.add(AspArguments.parse(s));
						if(s.curToken().kind == commaToken)
							skip(s, commaToken);
					}
					skip(s, rightParToken);
				}
				else if(s.curToken().kind == leftBracketToken)
				{
					skip(s, leftBracketToken);
					ap.primarySuffix.add(AspSubscription.parse(s));
					skip(s, rightBracketToken);
				}
				break;
				
			case integerToken:
				ap.atoms.add(AspIntegerLiteral.parse(s));
				break;
				
			case floatToken:
				ap.atoms.add(AspFloatLitertal.parse(s));
				break;
				
			case stringToken:
				ap.atoms.add(AspStringLiteral.parse(s));
				break;
				
			case trueToken:
			case falseToken:
				ap.atoms.add(AspBooleanLiteral.parse(s));
				break;
				
			case noneToken:
				ap.atoms.add(AspNoneLiteral.parse(s));
				break;
				
			case leftParToken:
				skip(s, leftParToken);
				ap.atoms.add(AspInnerExpr.parse(s));
				skip(s, rightParToken);
				break;
				
			case leftBracketToken:
				ap.atoms.add(AspListDisplay.parse(s));
				break;
				
			case leftBraceToken:
				ap.atoms.add(AspDictDisplay.parse(s));
				break;
		}
		Main.log.leaveParser("atom");
        Main.log.leaveParser("primary");
        return ap;
    }
    

    @Override
    void prettyPrint() {

    }

    @Override
    RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
        return null;
    }
}
