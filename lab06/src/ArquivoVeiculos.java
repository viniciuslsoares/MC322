import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ArquivoVeiculos implements I_Arquivo {

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos) {
        return false;
    }

    public ArrayList<?> lerArquivo(String arquivo) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            Veiculo novo;
            ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String placa = inputs[0];
                String marca = inputs[1];
                String modelo = inputs[2];
                int ano = Integer.parseInt(inputs[3]);

                try {
                    novo = new Veiculo(placa, marca, modelo, ano);
                    listaVeiculos.add(novo);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            leitor.close();
            return listaVeiculos;

        } catch (Exception e) {
        System.out.println(e);
            return null;
        }
    }

}
