import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ArquivoFrota {

    public boolean gravarArquivo(String arquivo, ArrayList<?> listaObjetos) {
        return false;
    }

    public ArrayList<?> lerArquivo(String arquivo) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            Frota novo;
            ArrayList<Frota> listaFrotas = new ArrayList<Frota>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String code = inputs[0];
                novo = new Frota(code, null);
                listaFrotas.add(novo);
            }

            leitor.close();
            return listaFrotas;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<?> lerArquivo(String arquivo, ArrayList<Veiculo> listaVeiculos) {

        try {
            File file = new File(arquivo);
            BufferedReader leitor;
            leitor = new BufferedReader(new FileReader(file));
            String linha;
            Frota novo;
            ArrayList<Frota> listaFrotas = new ArrayList<Frota>();
            linha = leitor.readLine(); // Descarta o cabeçalho

            while ((linha = leitor.readLine()) != null) {
                String[] inputs = linha.split(",");
                String code = inputs[0];
                novo = new Frota(code, null);
                for (int i = 1; i < inputs.length; i++) {
                    String placa = inputs[i];
                    for (Veiculo v : listaVeiculos) {
                        if (v.getPlaca().equals(placa)) {
                            novo.addVeiculo(v);
                        }
                    }
                }
                listaFrotas.add(novo);
            }

            leitor.close();
            return listaFrotas;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
