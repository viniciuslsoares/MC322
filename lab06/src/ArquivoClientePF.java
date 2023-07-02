import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Date;

public class ArquivoClientePF implements I_Arquivo {

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
            ClientePF novo;
            ArrayList<ClientePF> listaClientes = new ArrayList<ClientePF>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String cpf = inputs[0];
                String nome = inputs[1];
                String telefone = inputs[2];
                String endereco = inputs[3];
                String email = inputs[4];
                String genero = inputs[5];
                String educacao = inputs[6];
                Date dataNascimento = sdf.parse(inputs[7]);
                try {
                    novo = new ClientePF(nome, endereco, telefone, email, genero, educacao, dataNascimento, cpf);
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

    public ArrayList<?> lerArquivo(String arquivo, ArrayList<Veiculo> listaVeiculos) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            ClientePF novo;
            ArrayList<ClientePF> listaClientes = new ArrayList<ClientePF>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String cpf = inputs[0];
                String nome = inputs[1];
                String telefone = inputs[2];
                String endereco = inputs[3];
                String email = inputs[4];
                String genero = inputs[5];
                String educacao = inputs[6];
                Date dataNascimento = sdf.parse(inputs[7]);
                String placa = inputs[8];
                try {
                    novo = new ClientePF(nome, endereco, telefone, email, genero, educacao, dataNascimento, cpf);
                    for (Veiculo v : listaVeiculos) {
                        if (v.getPlaca().equals(placa))
                            novo.cadastrarVeiculo(v);
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
