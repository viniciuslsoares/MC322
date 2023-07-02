import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;

public class ArquivoCondutor implements I_Arquivo {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos) {
        return false;
    }

    public ArrayList<?> lerArquivo(String arquivo) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            Condutor novo;
            ArrayList<Condutor> listaCondutor = new ArrayList<Condutor>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String cpf = inputs[0];
                String nome = inputs[1];
                String telefone = inputs[2];
                String endereco = inputs[3];
                String email = inputs[4];
                Date dataNascimento = sdf.parse(inputs[5]);

                try {
                    novo = new Condutor(cpf, nome, telefone, email, endereco, dataNascimento, null);
                    listaCondutor.add(novo);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            leitor.close();
            return listaCondutor;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
