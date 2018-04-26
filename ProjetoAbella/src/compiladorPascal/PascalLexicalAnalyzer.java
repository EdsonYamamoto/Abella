package compiladorPascal;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class PascalLexicalAnalyzer {
	public static void main(String[] args) throws IOException {

        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/compiladorPascalTeste";

        String sourceCode = rootPath + subPath + "/UCadastroAtores.pas";

        LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
        
        PascalToken token;

        while ((token = lexical.yylex()) != null) {
        	if(token.name.trim().compareTo("fimLinha")!=0)
				System.out.println(" lin:"+token.line+" -"+token.name + "-  " + token.value);
            //System.out.println("<" + token.name + "\t " + token.value + "> (" + token.line + " - " + token.column + ")");
        }
    }
}