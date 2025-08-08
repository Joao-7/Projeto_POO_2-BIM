import javax.swing.*;
import java.awt.*;

public class PainelInicial extends JPanel {

    // Acessados apenas dentro da classe
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private ImageIcon imagemFundo;

    public PainelInicial(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());

        // Carregar imagem de fundo
        try {
            imagemFundo = new ImageIcon("resources/imgbackground.png");
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem de fundo: " + e.getMessage());
        }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagemFundo != null) {
            // Desenhar a imagem de fundo
            g.drawImage(imagemFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}