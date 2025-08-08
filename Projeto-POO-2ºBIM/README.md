# RPG Battle Arena - Projeto POO

## Correções e Melhorias Implementadas

### Problemas Corrigidos:

1. **Navegação entre telas**: 
   - Corrigido o problema onde "NOVA BATALHA" não voltava para o jogador 1
   - Adicionado método `resetarTela()` no PainelCadastro
   - PainelBatalha agora remove a si mesmo ao navegar

2. **Sistema de Persistência**:
   - Removido GerenciadorPersistencia antigo que não estava sendo usado
   - Criado novo sistema simples para salvar apenas histórico de batalhas
   - Nova classe `HistoricoBatalha` com serialização

3. **Simplificação do Código**:
   - Removidos comentários excessivos de todas as classes
   - **Removida toda a estilização da interface**
   - Interface básica sem cores ou estilos especiais
   - Código mais direto e focado na funcionalidade

### Novas Funcionalidades:

1. **Histórico de Batalhas**:
   - Salva automaticamente cada batalha finalizada em arquivo
   - Não há interface para visualizar (apenas salvamento)

2. **Interface Simplificada**:
   - Interface básica sem estilização
   - Componentes padrão do Swing
   - Fácil de entender e modificar

### Como Usar:

1. **Compilar**: `javac -d bin src/*.java`
2. **Executar**: `java -cp bin Main`
3. **Jogar**: Cadastre 2 jogadores e inicie a batalha
4. **Histórico**: Salvo automaticamente em `historico_batalhas.dat`

### Estrutura do Projeto:

- `Main.java`: Classe principal
- `PainelInicial.java`: Tela inicial com botão JOGAR
- `PainelCadastro.java`: Cadastro de personagens
- `PainelBatalha.java`: Sistema de batalha
- `HistoricoBatalha.java`: Sistema de persistência (salvamento automático)
- `Personagem.java`: Classe abstrata base
- `Guerreiro.java`, `Mago.java`, `Arqueiro.java`: Classes específicas
- `NomeInvalidoException.java`: Exceção personalizada

### Características dos Personagens:

- **Guerreiro**: Dano 50, Defesa 30 (equilibrado)
- **Mago**: Dano 60, Defesa 15 (alto dano, baixa defesa)
- **Arqueiro**: Dano 45, Defesa 25 (médio)

### Interface:

- Interface básica sem estilização
- Componentes padrão do Swing
- Fácil de personalizar e modificar
- Funcionalidade completa mantida

O projeto agora está com interface básica, funcional e fácil de entender para iniciantes!
