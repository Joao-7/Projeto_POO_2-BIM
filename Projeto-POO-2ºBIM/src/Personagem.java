import java.io.Serializable;

// Classe abstrada que não pode ser instanciada e cede atributos para classes filhas 
// Herança, abstração e polimorfismo
// Implements serializable: diz que a classe pode ser serializável 
public abstract class Personagem implements Serializable {

    // Protected: os atributos só podem ser utilizados dentro da própria classe, mesmo pacote e em subclasses.
    protected String nome;
    protected String tipo;
    protected int vida;
    protected int dano;
    protected int defesa;

    // Construtor
    public Personagem(String nome, String tipo, int dano, int defesa) throws NomeInvalidoException {
        validarNome(nome); 

        this.nome = nome.trim();
        this.tipo = tipo;
        this.vida = 100;
        this.dano = dano;
        this.defesa = defesa;
    }

    
    private void validarNome(String nome) throws NomeInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("Nome obrigatório");
        }
        if (nome.length() < 2) {
            throw new NomeInvalidoException("Escreva pelo menos dois caracteres");
        }
    }

    // Protege os atributos 
    // getNome-Encapsulamento: Conseguimos acessar os atributos mais não modificar 
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public int getDefesa() {
        return defesa;
    }

    // Verifica se o personagem ainda tem vida, retorna true se o personagem ainda tem vida.
    public boolean estaVivo() {
        return vida > 0;
    }

    // Garante que a vida após o dano recebido nunca fique negativa
    public void receberDano(int danoRecebido) {
        this.vida = Math.max(0, vida - danoRecebido);
    }

    // Representação textual do personagem
    // %s: string
    // %d: int
    @Override
    public String toString() {
        return String.format("%s (%s) - Vida: %d | Dano: %d | Defesa: %d", nome, tipo, vida, dano, defesa);
    }
}

