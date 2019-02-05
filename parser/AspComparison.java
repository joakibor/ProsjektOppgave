package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import java.util.ArrayList;

public class AspComparison extends AspSyntax
{
	ArrayList<AspTerm> terms = new ArrayList<>();
	ArrayList<AspCompOpr> compOprs = new ArrayList<>();
	
	AspComparison(int n)
	{
		super(n);
	}
	
	static AspComparison parse(Scanner s)
	{
		Main.log.enterParser("comparison");
		
		AspComparison ac = new AspComparison(s.curLineNum());
		
		boolean opr = true;
		while(opr)
		{
			ac.terms.add(AspTerm.parse(s));
			
			switch(s.curToken().kind)
			{
				case lessToken:
				case greaterToken:
				case doubleEqualToken:
				case lessEqualToken:
				case greaterEqualToken:
				case notEqualToken:
					ac.compOprs.add(AspCompOpr.parse(s));
					break;
				default:
					opr = false;
			}
		}
		
		Main.log.leaveParser("comparison");
		return ac;
	}
	
	@Override
	void prettyPrint()
	{
	
	}
	
	@Override
	RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue
	{
		return null;
	}
}
