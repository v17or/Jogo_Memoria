package jogodamemoria;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Deck extends JLabel {
	private boolean cartaPraCima;
	private final Icon imgCartaFrente;
	private final Icon imgCartaVerso;
	private final int id;
	private final Tabuleiro tabuleiro;
	
	public Deck(int id, Icon imagemFrontalDaCarta, Tabuleiro tabuleiro){
		super();
		this.imgCartaFrente = imagemFrontalDaCarta;
		imgCartaVerso = new ImageIcon(getClass().getResource("assets/carta_img_traseira.png"));
		this.tabuleiro = tabuleiro;
		cartaPraCima = false;		
		this.id = id;
		setIcon(imgCartaVerso);
		addMouseListener(new MouseClique());
	}	
	public boolean isViradaParaCima() {
		return cartaPraCima;
	}
	/*
	 * 
	 */	
	public void mudarPosicao(){
		if(isViradaParaCima()){
			cartaPraCima = false;
			setIcon(imgCartaVerso);
		}else{
			cartaPraCima = true;		
			setIcon(imgCartaFrente);
		}		
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		return id == other.id;
	}
	//'MouseListener' que tratar� o evento do clique na carta
	private class MouseClique implements MouseListener{
		
		@Override
		public void mouseReleased(MouseEvent e) {
			//obt�m a carta que foi clicada e passa-a para o tabuleiro
			Deck c = (Deck) e.getSource();
			c.tabuleiro.acao(c);			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//n�o precisa implementar
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//n�o precisa implementar
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//n�o precisa implementar
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//n�o precisa implementar
		}		
	}
}
