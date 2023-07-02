import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Date;

public class ArquivoClientePJ implements I_Arquivo {

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
            ClientePJ novo;
            ArrayList<ClientePJ> listaClientes = new ArrayList<ClientePJ>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String cnpj = inputs[0];
                String nome = inputs[1];
                String telefone = inputs[2];
                String endereco = inputs[3];
                String email = inputs[4];
                Date datafund = sdf.parse(inputs[5]);
                try {
                    novo = new ClientePJ(nome, endereco, telefone, email, cnpj, datafund, 1);
                    listaClientes.add(novo);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            leitor.close();
            return listaClientes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<?> lerArquivo(String arquivo, ArrayList<Frota> listaFrotas) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            ClientePJ novo;
            ArrayList<ClientePJ> listaClientes = new ArrayList<ClientePJ>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String cnpj = inputs[0];
                String nome = inputs[1];
                String telefone = inputs[2];
                String endereco = inputs[3];
                String email = inputs[4];
                Date datafund = sdf.parse(inputs[5]);
                String code = inputs[6];
                try {
                    novo = new ClientePJ(nome, endereco, telefone, email, cnpj, datafund, 1);
                    for (Frota f : listaFrotas) {
                        if (f.getCode().equals(code))
                            novo.cadastrarFrota(f);
                    }
                    listaClientes.add(novo);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            leitor.close();
            return listaClientes;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
