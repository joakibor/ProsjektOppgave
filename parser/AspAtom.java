package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.Main;
import no.uio.ifi.asp.scanner.Scanner;

abstract class AspAtom extends AspSyntax
{
	AspAtom(int n)
	{
		super(n);
	}
	
	static AspAtom parse(Scanner s)
	{
		return null;
	}
}
