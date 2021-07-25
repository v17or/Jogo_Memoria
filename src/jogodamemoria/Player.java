package jogodamemoria;

public class Player {
    private final int id;
    private String nome;
    private int pontuacao;

    public Player(String nome, int id) {
        this.nome = nome;
        this.pontuacao = 0;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void aumentarPontuacao() {
        this.pontuacao++;
    }

    public void diminuirPontuacao() {
        this.pontuacao--;
    }
    
    
}
