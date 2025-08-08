import javax.swing.*;
import java.awt.*;

public class PainelInicial extends JPanel {

    // Acessados apenas dentro da classe
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public PainelInicial(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());

        // Botão centralizado
        JButton botaoJogar = new JButton("JOGAR");
        botaoJogar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoJogar.setPreferredSize(new Dimension(150, 50));

        botaoJogar.addActionListener(e -> {
            cardLayout.show(mainPanel, "cadastro");
        });

        // Painel central para centralizar o botão
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridBagLayout());
        painelCentral.setOpaque(false); // Torna transparente para mostrar a imagem

        painelCentral.add(botaoJogar);

        add(painelCentral, BorderLayout.CENTER);
    }
}