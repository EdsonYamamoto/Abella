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
        	if(
					token.name.trim().compareTo("fimLinha")!=0&&
					token.name.trim().compareTo("metodo")!=0&&
					token.name.trim().compareTo("condicao else ou if")!=0&&
					token.name.trim().compareTo("condicao else")!=0&&
					token.name.trim().compareTo("atribuicao")!=0&&
					token.name.trim().compareTo("SqlAdd")!=0&&
					token.name.trim().compareTo("SqlOpen")!=0&&
					token.name.trim().compareTo("SqlExec")!=0&&
					token.name.trim().compareTo("declaracaoVariavel")!=0&&
					token.name.trim().compareTo("raise error")!=0&&
					token.name.trim().compareTo("comentario")!=0&&
					token.name.trim().compareTo("end")!=0&&
					token.name.trim().compareTo("begin")!=0&&
					token.name.trim().compareTo("showMessage")!=0&&
					token.name.trim().compareTo("SqlClear")!=0
					//&&token.line!=null
				)
				System.out.println(" lin:"+token.line+" -"+token.name + "-  " + token.value);
            //System.out.println("<" + token.name + "\t " + token.value + "> (" + token.line + " - " + token.column + ")");
        }
    }
}