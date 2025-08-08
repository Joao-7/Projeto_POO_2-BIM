import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoBatalha implements Serializable {
    private String jogador1;
    private String jogador2;
    private String vencedor;
    private int rodadas;
    private String data;

    public HistoricoBatalha(String jogador1, String jogador2, String vencedor, int rodadas) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.vencedor = vencedor;
        this.rodadas = rodadas;
        this.data = java.time.LocalDate.now().toString();
    }

    public String getJogador1() {
        return jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public String getVencedor() {
        return vencedor;
    }

    public int getRodadas() {
        return rodadas;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data + " - " + jogador1 + " vs " + jogador2 + " - Vencedor: " + vencedor + " (" + rodadas + " rodadas)";
    }

    public static void salvarHistorico(HistoricoBatalha historico) {
        List<HistoricoBatalha> historicos = carregarHistoricos();
        historicos.add(historico);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("historico_batalhas.dat"))) {
            oos.writeObject(historicos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<HistoricoBatalha> carregarHistoricos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("historico_batalhas.dat"))) {
            return (List<HistoricoBatalha>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}