package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.scanner.Scanner;

public abstract class AspPrimarySuffix extends AspSyntax{
    AspPrimarySuffix(int n) {
        super(n);
    }
    
    static AspPrimarySuffix parse(Scanner s)
    {
        return null;
    }
}
