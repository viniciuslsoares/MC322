import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class ArquivoSeguro implements I_Arquivo {

    public ArrayList<?> lerArquivo(String arquivo) {
        return null;
    }

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos) {

        try {
            File file = new File(arquivo);
            file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            writer.write("id,data_inicio,data_fim,seguradora,sinistros,condutores,valor_mensal,nome,veiculos/frota");
            for (Seguradora s : Seguradora.getListaSeguradoras()) {
                for (Seguro seguro : s.getListaSeguros()) {
                    writer.write(seguro.seguroCsv());
                }
            }
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
