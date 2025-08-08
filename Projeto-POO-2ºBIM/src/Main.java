import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("RPG Battle Arena"); 
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(800, 600);
        janela.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout(); // Gerenciador de layout que permite a troca de paineis 
        JPanel mainPanel = new JPanel(cardLayout); // Painel onde vai ocorrer a troca de paineis

        PainelInicial painelInicial = new PainelInicial(mainPanel, cardLayout); 
        PainelCadastro painelCadastro = new PainelCadastro(mainPanel, cardLayout);

        mainPanel.add(painelInicial, "inicial");
        mainPanel.add(painelCadastro, "cadastro");

        janela.setContentPane(mainPanel);  // Define o mainPanel como o painel que irá fixo na janela.
        janela.setVisible(true);
    }
}
