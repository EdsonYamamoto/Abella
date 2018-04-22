package compiladorPascal;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class PascalLexicalAnalyzer {
	public static void main(String[] args) throws IOException {

        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/compiladorPascalTeste";

        String sourceCode = rootPath + subPath + "/program.pas";

        LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
        
        PascalToken token;

        while ((token = lexical.yylex()) != null) {
        	if(token.name!="fimLinha")
        		System.out.println("--"+token.name + "--"+token.line+"\n" + token.value+"\n");
            //System.out.println("<" + token.name + "\t " + token.value + "> (" + token.line + " - " + token.column + ")");
        }
    }
}