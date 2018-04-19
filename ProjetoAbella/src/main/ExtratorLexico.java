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
				//if(token.name!="fimLinha"&&token.name!="comentario1"&&token.name!="comentario2"&&token.name!="comentario3"&&token.name!="comentario4")
				//	System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);

				
				if(token.name!="fimLinha")
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
