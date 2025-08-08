import javax.swing.*;
import java.awt.*;

class PainelBatalha extends JPanel {
    private JTextArea logArea;
    private JButton btnAtacar;
    private JButton btnNovaBatalha;
    private JButton btnVoltar;
    
    private Personagem jogador1;
    private Personagem jogador2;
    private Personagem atacanteAtual;
    private Personagem defensorAtual;
    
    private JLabel labelJogador1;
    private JLabel labelJogador2;
    private JLabel labelTurno;
    
    private int rodada = 1;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public PainelBatalha(JPanel mainPanel, CardLayout cardLayout, Personagem p1, Personagem p2) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.jogador1 = p1;
        this.jogador2 = p2;
        
        setLayout(new BorderLayout());
        
        jogador1.vida = 100;
        jogador2.vida = 100;
        
        JPanel painelSuperior = criarPainelSuperior();
        add(painelSuperior, BorderLayout.NORTH);
        
        JPanel painelCentral = criarPainelCentral();
        add(painelCentral, BorderLayout.CENTER);
        
        JPanel painelInferior = criarPainelInferior();
        add(painelInferior, BorderLayout.SOUTH);
        
        iniciarBatalha();
    }
    
    private JPanel criarPainelSuperior() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        JPanel painelJ1 = new JPanel();
        painelJ1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        labelJogador1 = new JLabel("Jogador 1: " + jogador1.toString());
        labelJogador1.setFont(new Font("Arial", Font.BOLD, 12));
        painelJ1.add(labelJogador1);
        
        JPanel painelJ2 = new JPanel();
        painelJ2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        labelJogador2 = new JLabel("Jogador 2: " + jogador2.toString());
        labelJogador2.setFont(new Font("Arial", Font.BOLD, 12));
        painelJ2.add(labelJogador2);
        
        painel.add(painelJ1);
        painel.add(painelJ2);
        
        return painel;
    }
    
    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new BorderLayout());
        
        labelTurno = new JLabel("Rodada " + rodada + " - Preparando batalha...");
        labelTurno.setFont(new Font("Arial", Font.BOLD, 14));
        labelTurno.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(labelTurno, BorderLayout.NORTH);
        
        logArea = new JTextArea(15, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel();
        
        btnAtacar = new JButton("ATACAR");
        btnNovaBatalha = new JButton("NOVA BATALHA");
        btnNovaBatalha.setVisible(false);

        btnVoltar = new JButton("VOLTAR");
        
        btnAtacar.addActionListener(e -> {
            executarAtaque();
        });

        btnNovaBatalha.addActionListener(e -> {
            mainPanel.remove(this);
            PainelCadastro novoPainelCadastro = new PainelCadastro(mainPanel, cardLayout);
            mainPanel.add(novoPainelCadastro, "cadastro");
            cardLayout.show(mainPanel, "cadastro");
        });
        
        btnVoltar.addActionListener(e -> {
            mainPanel.remove(this);
            cardLayout.show(mainPanel, "inicial");
        });
        
        painel.add(btnAtacar);
        painel.add(btnNovaBatalha);
        painel.add(btnVoltar);
        
        return painel;
    }
    
    private void iniciarBatalha() {
        log("=== BATALHA INICIADA ===");
        log("Jogador 1: " + jogador1.toString());
        log("Jogador 2: " + jogador2.toString());
        log("");
        
        determinarAtacante();
    }
    
    private void determinarAtacante() {
        int sorte = (int) (Math.random() * 2);
        if (sorte == 0) {
            atacanteAtual = jogador1;
            defensorAtual = jogador2;
        } else {
            atacanteAtual = jogador2;
            defensorAtual = jogador1;
        }
        
        labelTurno.setText("Rodada " + rodada + " - " + atacanteAtual.getNome() + " ataca primeiro!");
        log(atacanteAtual.getNome() + " ganhou a iniciativa!");
        log("");
    }
    
    private void executarAtaque() {
        if (!jogador1.estaVivo() || !jogador2.estaVivo()) {
            return;
        }
        
        log("=== RODADA " + rodada + " ===");
        
        atacar(atacanteAtual, defensorAtual);
        
        if (!defensorAtual.estaVivo()) {
            finalizarBatalha();
            return;
        }
        
        Personagem temp = atacanteAtual;
        atacanteAtual = defensorAtual;
        defensorAtual = temp;
        
        rodada++;
        labelTurno.setText("Rodada " + rodada + " - " + atacanteAtual.getNome() + " ataca!");
        
        log("");
        atualizarLabels();
    }

    private void atacar(Personagem atacante, Personagem defensor) {
        int danoBase = atacante.getDano();
        int danoCausado = danoBase - defensor.getDefesa();
        
        if (danoCausado < 0) {
            danoCausado = 0;
        }
        
        defensor.receberDano(danoCausado);
        
        log(atacante.getNome() + " atacou " + defensor.getNome());
        log("Dano base: " + danoBase + " | Defesa: " + defensor.getDefesa() + " | Dano causado: " + danoCausado);
        log(defensor.getNome() + " - Vida restante: " + defensor.getVida());
    }
    
    private void finalizarBatalha() {
        String vencedor = jogador1.estaVivo() ? jogador1.getNome() : jogador2.getNome();
        String perdedor = jogador1.estaVivo() ? jogador2.getNome() : jogador1.getNome();
        
        log("=== BATALHA FINALIZADA ===");
        log("VENCEDOR: " + vencedor + "!");
        log(perdedor + " foi derrotado!");
        log("Total de rodadas: " + rodada);
        
        HistoricoBatalha historico = new HistoricoBatalha(
            jogador1.getNome(), 
            jogador2.getNome(), 
            vencedor, 
            rodada
        );
        HistoricoBatalha.salvarHistorico(historico);
        
        btnAtacar.setEnabled(false);
        btnNovaBatalha.setVisible(true);
        
        labelTurno.setText("Batalha Finalizada - " + vencedor + " venceu!");
    }
    
    private void atualizarLabels() {
        labelJogador1.setText("Jogador 1: " + jogador1.toString());
        labelJogador2.setText("Jogador 2: " + jogador2.toString());
    }

    private void log(String mensagem) {
        logArea.append(mensagem + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}
