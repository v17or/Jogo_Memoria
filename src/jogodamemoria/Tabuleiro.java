package jogodamemoria;

import java.awt.GridLayout;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {


    private Deck[] cartas;
    private Deck ultimaCartaVirada = null;
    private int paresFormados = 0;
    private boolean lock = false;
    String dificuldade;
    int modoJogo;
    int quantidadeTotal;

    Player player1 = null;
    Player player2 = null;
    Player playerAtual = null;
    
    public void setDificuldade(String dificuldade) {
        switch(dificuldade){
            case "facil":
                quantidadeTotal = 12;
            break;
            case "medio":
                quantidadeTotal = 16;
            break;
            case "dificil":
                quantidadeTotal = 18;
            break;
        }
        this.dificuldade = dificuldade;
    }
    
    public void setModoJogo(int modoJogo) {
        this.modoJogo = modoJogo;
    }
    
    public void createJogadores(String Jogador1, String Jogador2){
        player1 = new Player(Jogador1, 0);
        if(modoJogo == 2) {
            player2 = new Player(Jogador2, 1);
        }
        playerAtual = player1;
    }
    
    public void createTabuleiro(){		
        cartas = new Deck[quantidadeTotal];
        for (int i = 0; i < quantidadeTotal; i++) {
            cartas[i] = new Deck((i / 2) + 1, new ImageIcon(getClass().getResource("assets/" + ((i / 2) + 1) + ".png")), this);
        }
        Embaralhar.embaralhar(cartas);
        setLayout(new GridLayout(3, 4, 1, 1));
        for (Deck carta : cartas) {
            add(carta);
        }
    }

    public void trocaJogador(Player player) {
        if ("dificil".equals(this.dificuldade)) {
            player.diminuirPontuacao();
        } 
        if (player.getID() == 0) {
            if (this.modoJogo == 2) {
                this.playerAtual = player2;
            }
        } else {
            this.playerAtual = player1;
        }
    }

    public void aumentaPonto(Player player) {
        player.aumentarPontuacao();
    }
    
    public String ganhador(){
        if(player1.getPontuacao() > player2.getPontuacao()) {
            return "Parabéns "+player1.getNome()+", você ganhou!!!";
        } else {
            return "Parabéns "+player2.getNome()+", você ganhou!!!";
        }
    }

    public void acao(Deck carta) {
        if (!carta.isViradaParaCima() && !lock) {
            if (ultimaCartaVirada == null) {
                carta.mudarPosicao();
                ultimaCartaVirada = carta;
            } else {
                if (carta.equals(ultimaCartaVirada)) {
                    ultimaCartaVirada = null;			
                    carta.mudarPosicao();
                    paresFormados++;
                    if (paresFormados == (quantidadeTotal/2)) {
                        aumentaPonto(playerAtual);
                        if(modoJogo == 1) {
                            JOptionPane.showMessageDialog(this,
                                "O jogo acabou "+player1.getNome()+"! Sua pontuação: " + player1.getPontuacao(),
                                "Fim de jogo",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this,
                                "O jogo acabou! Confira as pontuações\n"+
                                player1.getNome()+", sua pontuação: " + player1.getPontuacao() +"\n"+
                                player2.getNome()+", sua pontuação: " + player2.getPontuacao(),
                                this.ganhador(),
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        if(modoJogo == 2) {
                            JOptionPane.showMessageDialog(this,
                            "Parábens " + playerAtual.getNome() +", continue assim.",
                            "Acertou",
                            JOptionPane.INFORMATION_MESSAGE);
                        }
                        aumentaPonto(playerAtual);
                    }
                } else {
                    TimerTask t1 = new TimerTask() {
                        @Override
                        public void run() {
                            carta.mudarPosicao();
                            lock = true;
                        }
                    };
                    TimerTask t2 = new TimerTask() {
                        @Override
                        public void run() {
                            ultimaCartaVirada.mudarPosicao();
                            carta.mudarPosicao();
                            ultimaCartaVirada = null;
                            lock = false;
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(t1, 1);
                    timer.schedule(t2, 1000);
                    
                    if(modoJogo == 2) {
                        trocaJogador(playerAtual);
                        JOptionPane.showMessageDialog(this,
                        "Você errou, é a vez do Jogador: " + playerAtual.getNome(),
                        "Errou",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
}
