package TwoDimensionalCutting;

import GeneticAlgorithm.*;

public class CuttingChromosome implements Chromosome{

	private int codeX = -1;
	private int codeY = -2;
	private int chromosomeSize;
	private int[] tree;
	private int[] left;
	private int[] right;
	private int[] x;
	private int[] y;
	private int[] w;
	private int[] h;
	private int score;

	public CuttingChromosome(int[] tree, int[] left, int[] right, int[] x, int[] y, int[] w, int[] h, int score){
		chromosomeSize = tree.length;
		this.score = score;
		this.tree = new int[chromosomeSize];
		this.left = new int[chromosomeSize];
		this.right = new int[chromosomeSize];
		this.x = new int[chromosomeSize];
		this.y = new int[chromosomeSize];
		this.w = new int[chromosomeSize];
		this.h = new int[chromosomeSize];
		for(int i=0; i<chromosomeSize; i++){
			this.tree[i] = tree[i];
			this.left[i] = left[i];
			this.right[i] = right[i];
			this.x[i] = x[i];
			this.y[i] = y[i];
			this.h[i] = h[i];
			this.w[i] = w[i];
		}
		//for(int i=0; i<chromosomeSize; i++){ System.out.print(this.tree[i] + " "); }	System.out.println();
		/*for(int i=0; i<chromosomeSize; i++){ System.out.print(this.left[i] + " "); }	System.out.println();
		for(int i=0; i<chromosomeSize; i++){ System.out.print(this.right[i] + " "); }	System.out.println();
		for(int i=0; i<chromosomeSize; i++){ System.out.print(this.x[i] + " "); }		System.out.println();
		for(int i=0; i<chromosomeSize; i++){ System.out.print(this.y[i] + " "); }		System.out.println();
		for(int i=0; i<chromosomeSize; i++){ System.out.print(this.h[i] + " "); }		System.out.println();
		for(int i=0; i<chromosomeSize; i++){ System.out.print(this.w[i] + " "); }		System.out.println();*/
	}

	public int[] getTree(){
		int[] tree = new int[chromosomeSize];
		for(int i=0; i<chromosomeSize; i++){
			tree[i] = this.tree[i];
		}
		return tree;
	}
	
	public int[] getX(){
		int numberPieces = 1 + (chromosomeSize / 2);
		int[] pos = new int[numberPieces+1];
		for(int i=0,j=0; i<chromosomeSize; i++){
			if(tree[i] != codeX && tree[i] != codeY){
				pos[tree[i]] = i;			
			}
		}
		int[] x = new int[numberPieces];
		for(int i=0; i<numberPieces; i++){
			x[i] = this.x[pos[i+1]];
		}
		return x;
	}

	public int[] getY(){
		int numberPieces = 1 + (chromosomeSize / 2);
		int[] pos = new int[numberPieces+1];
		for(int i=0,j=0; i<chromosomeSize; i++){
			if(tree[i] != codeX && tree[i] != codeY){
				pos[tree[i]] = i;			
			}
		}
		int[] y = new int[numberPieces];
		for(int i=0; i<numberPieces; i++){
			y[i] = this.y[pos[i+1]];
		}
		return y;
	}

	public int[] getW(){
		int numberPieces = 1 + (chromosomeSize / 2);
		int[] pos = new int[numberPieces+1];
		for(int i=0,j=0; i<chromosomeSize; i++){
			if(tree[i] != codeX && tree[i] != codeY){
				pos[tree[i]] = i;			
			}
		}
		int[] w = new int[numberPieces];
		for(int i=0; i<numberPieces; i++){
			w[i] = this.w[pos[i+1]];
		}
		return w;
	}

	public int[] getH(){
		int numberPieces = 1 + (chromosomeSize / 2);
		int[] pos = new int[numberPieces+1];
		for(int i=0,j=0; i<chromosomeSize; i++){
			if(tree[i] != codeX && tree[i] != codeY){
				pos[tree[i]] = i;			
			}
		}
		int[] h = new int[numberPieces];
		for(int i=0; i<numberPieces; i++){
			h[i] = this.h[pos[i+1]];
		}
		return h;
	}

	@Override
	public int getScore(){
		return score;
	}

	@Override
	public String getFenotype(){
		String fenotype = "";
		fenotype += "[";
		for(int i=0; i<chromosomeSize; i++){
			if(i > 0)
			fenotype += ",";

			if(tree[i] == codeX)
			fenotype += "H";
			else if(tree[i] == codeY)
			fenotype += "V";
			else{
				fenotype += String.valueOf(tree[i]);
			}
		}
		fenotype += "]";
		return fenotype;
	}
}
