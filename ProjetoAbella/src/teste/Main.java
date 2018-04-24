package teste;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "\\src\\teste\\";

        String sourcecode = rootPath + subPath + "calc.l";
        Parser parser = new Parser(new Lexer(new FileReader(sourcecode)));
        try {
        	System.out.println(parser.hashCode());
            parser.parse();
        }       
        catch (Exception e) {
            System.out.println("Falha geral.");
        }

    }
}
// na pasta onde esta a biblioteca jflex :  java -jar jflex-1.6.1.jar Lexer.lex
// na pasta onde esta a biblioteca cup: java -jar java-cup-11a.jar -parser Parser -symbols Sym Parser.cup