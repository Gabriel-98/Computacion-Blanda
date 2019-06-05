package views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.Vector;

public class PanelGraphics extends JPanel{

	private Color[] colores = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
								Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, new Color(200,40,73), Color.YELLOW};
	private int operation;
	private int[] scores;
	private int[] x, y, w, h;

	public PanelGraphics(){
		operation = 0;
		x = new int[1];
		y = new int[1];
		w = new int[1];
		h = new int[1];
	}

	@Override
	public void paintComponent(Graphics g) {
		dibujarRectangulos2(g);
	}

	public void dibujarRectangulos(int[] x, int[] y, int[] w, int[] h){
		this.x = new int[x.length];
		this.y = new int[y.length];
		this.w = new int[w.length];
		this.h = new int[h.length];
		for(int i=0; i<x.length; i++){
			this.x[i] = x[i];
			this.y[i] = y[i];
			this.w[i] = w[i];
			this.h[i] = h[i];
		}
	}

	public void dibujarRectangulos2(Graphics g){
		g.clearRect(0,0,600,600);
		for(int i=0; i<x.length && i<colores.length; i++){
			g.setColor(colores[i]);
			g.fillRect(x[i], y[i], w[i], h[i]);
		}
	}

	public void dibujarGrafica(int[] scores){
		this.scores = new int[scores.length];
		for(int i=0; i<scores.length; i++){
			this.scores[i] = scores[i];
		}
		operation = 1;
	}

	public void dibujarGrafica2(Graphics g){
		g.setColor(Color.WHITE);
		int menor, mayor;
		menor = scores[0];
		mayor = scores[0];
		for(int i=0; i<scores.length; i++){
			menor = Math.min(menor, scores[i]);
			mayor = Math.max(mayor, scores[i]);
		}
		for(int i=1; i<scores.length; i++){
			/*int x,y;
			x = (i*600) / scores.length;
			y = ((scores[i] - menor)*300) / (mayor - menor);
			g.fillOval(x,300-y,5,5);*/

			int x1,y1,x2,y2;
			x1 = ((i-1)*600) / scores.length;
			y1 = ((scores[i-1] - menor)*300) / (mayor - menor);
			x2 = (i*600) / scores.length;
			y2 = ((scores[i] - menor)*300) / (mayor - menor);

			y1 = 300 - y1;
			y2 = 300 - y2;
			g.drawLine(x1,y1,x2,y2);
		}
	}

}