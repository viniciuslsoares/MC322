import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class ArquivoSinistro implements I_Arquivo {

    public ArrayList<?> lerArquivo(String arquivo) {
        return null;
    }

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos) {

        try {
            File file = new File(arquivo);
            file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            writer.write("id,data,endereço,seguro");
            for (Sinistro s : Sinistro.getListaSinistros()) {
                writer.write(s.sinistroCsv());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
