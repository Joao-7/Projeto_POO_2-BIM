import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Extends JPanel transforma a classe em um componente visual que pode ser adicionado a janela.
class PainelCadastro extends JPanel {

    // Armazena os objetos Personagem criados durante o cadastro.
    private ArrayList<Personagem> personagens = new ArrayList<>();

    // Atributos de instância para que possam ser utilizados em qualquer método da classe.
    private int jogadorAtual = 1; // Contador para saber qual jogador esta sendo cadastrado (1 ou 2)
    private JLabel labelNome;
    private JTextField campoNome; 
    private JComboBox<String> comboClasse; // Caixa de seleção.
    private JLabel resultado;
    private JButton btnCadastrar;
    private JButton btnIniciarBatalha;
    private JButton btnVoltar;
    private JPanel mainPanel;
    private CardLayout cardLayout; 

    // Construtor: primeiro método da classe a ser executado.
    public PainelCadastro(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout()); // Gerenciador de layout para dividir a tela em 5 áreas diferentes (Norte, sul, leste, oeste e centro)

        JLabel titulo = new JLabel("CADASTRO DE PERSONAGENS");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelFormulario = criarPainelFormulario(); // painel de formulário que irá ficar no centro.
        JPanel painelBotoes = criarPainelBotoes(); // painel de botões que irá ficar na parte inferior

        JPanel painelTitulo = new JPanel();
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // painel de formulário central
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); // Gerenciador de layout mais preciso. 

        labelNome = new JLabel("Jogador 1:");
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 10); 
        painel.add(labelNome, gbc);

        campoNome = new JTextField(20); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        painel.add(campoNome, gbc);

        JLabel labelPersonagem = new JLabel("Escolha o Personagem:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(labelPersonagem, gbc);

        String[] classes = {
                "Guerreiro (Dano: 50, Defesa: 20)",
                "Mago (Dano: 30, Defesa: 10)",
                "Arqueiro (Dano: 25, Defesa: 15)"
        };

        comboClasse = new JComboBox<>(classes);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        painel.add(comboClasse, gbc);

        btnCadastrar = new JButton("CADASTRAR");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        painel.add(btnCadastrar, gbc);

        resultado = new JLabel("");
        resultado.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        painel.add(resultado, gbc);

        btnCadastrar.addActionListener(e -> {
            cadastrarPersonagem();
        });

        return painel;
    }

    // painel inferior para os botões
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel();

        btnVoltar = new JButton("VOLTAR");
        btnIniciarBatalha = new JButton("INICIAR BATALHA");
        btnIniciarBatalha.setVisible(false);

        btnVoltar.addActionListener(e -> {
            resetarTela();
            cardLayout.show(mainPanel, "inicial");
        });

        btnIniciarBatalha.addActionListener(e -> {
            iniciarBatalha();
        });

        painel.add(btnVoltar);
        painel.add(btnIniciarBatalha);

        return painel;
    }

    // Método usado para resetar a tela e voltar quando apertar no btnvoltar.
    private void resetarTela() {
        personagens.clear();
        jogadorAtual = 1;
        campoNome.setText("");
        labelNome.setText("Jogador 1:");
        btnCadastrar.setEnabled(true); 
        btnIniciarBatalha.setVisible(false);
        resultado.setText("");
    }

    private void cadastrarPersonagem() {
        String nome = campoNome.getText().trim();
        String classe = (String) comboClasse.getSelectedItem();
   
        try {
            Personagem novo = criarPersonagem(nome, classe);
            if (novo != null) {
                personagens.add(novo);
                mostrarSucesso("Jogador " + jogadorAtual + " cadastrado com sucesso!");

                campoNome.setText("");
                jogadorAtual++;

                if (jogadorAtual == 2) {
                    labelNome.setText("Jogador 2:");
                }

                if (jogadorAtual > 2) {
                    btnCadastrar.setEnabled(false);
                    btnIniciarBatalha.setVisible(true);
                }
            }
        } catch (NomeInvalidoException e) {
            mostrarErro("Erro ao criar personagem: " + e.getMessage());
        }
    }

    private Personagem criarPersonagem(String nome, String classe) throws NomeInvalidoException {
        if (classe.equals("Guerreiro (Dano: 50, Defesa: 20)")) {
            return new Guerreiro(nome);
        } else if (classe.equals("Mago (Dano: 30, Defesa: 10)")) {
            return new Mago(nome);
        } else if (classe.equals("Arqueiro (Dano: 25, Defesa: 15)")) {
            return new Arqueiro(nome);
        }
        return null;
    }

    private void mostrarSucesso(String mensagem) {
        resultado.setText(mensagem);
    }

    private void mostrarErro(String mensagem) {
        resultado.setText(mensagem);
    }

    private void iniciarBatalha() {
        if (personagens.size() == 2) {
            PainelBatalha painelBatalha = new PainelBatalha(mainPanel, cardLayout, personagens.get(0),
                    personagens.get(1));
            mainPanel.add(painelBatalha, "batalha");
            cardLayout.show(mainPanel, "batalha");
        }
    }
}
