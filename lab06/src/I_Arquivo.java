import java.util.ArrayList;

public interface I_Arquivo {

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos);

    public ArrayList<?> lerArquivo(String arquivo);

}   