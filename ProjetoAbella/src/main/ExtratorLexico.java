package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import compiladorPascal.LexicalAnalyzer;
import compiladorPascal.PascalToken;

public class ExtratorLexico {
	public static void extrator(File sourceCode)
	{
	    PascalToken token;
	    try {
		LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
	    
			while ((token = lexical.yylex()) != null) {
			    System.out.println(token.name + "\t\t " + token.value);
			    //System.out.println("<" + token.name + "\t " + token.value + "> (" + token.line + " - " + token.column + ")");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
