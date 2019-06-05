package TwoDimensionalCutting;

import java.lang.Math;
import java.util.Stack;
import java.util.Random;
import GeneticAlgorithm.*;

public class CuttingChromosomeFactory implements ChromosomeFactory<CuttingChromosome>{

	private final int codeX = -1;
	private final int codeY = -2;
	private Double mutationPercentagePieces; 
	private Double mutationPercentageOperator;
	private Double mutationPercentagePieceAndOperator;
	private Random random;
	private int numberOfPieces;
	private int numberOfOperators;
	private int chromosomeSize;
	private int[] widths;
	private int[] heights;


	public CuttingChromosomeFactory(int[] widths, int[] heights, Double mutationPercentagePieces, Double mutationPercentageOperator, Double mutationPercentagePieceAndOperator){
		random = new Random();
		this.mutationPercentagePieces = mutationPercentagePieces;
		this.mutationPercentageOperator = mutationPercentageOperator;
		this.mutationPercentagePieceAndOperator = mutationPercentagePieceAndOperator;
		numberOfPieces = widths.length;
		numberOfOperators = numberOfPieces - 1;
		chromosomeSize = numberOfPieces + numberOfOperators;
		this.widths = new int[widths.length];
		this.heights = new int[heights.length];
		for(int i=0; i<widths.length; i++){
			this.widths[i] = widths[i];
		}
		for(int i=0; i<heights.length; i++){
			this.heights[i] = heights[i];
		}
	}

	public void locatePieces(int node, int posX, int posY, int[] tree, int[] left, int[] right, int x[], int[] y, int[] w, int[] h){
		x[node] = posX;
		y[node] = posY;
		if(tree[node] == codeX || tree[node] == codeY){
			locatePieces(left[node], posX, posY, tree, left, right, x, y, w, h);
			if(tree[node] == codeX){
				locatePieces(right[node], posX + w[left[node]], posY, tree, left, right, x, y, w, h);
				w[node] = w[left[node]] + w[right[node]];
				h[node] = Math.max(h[left[node]], h[right[node]]);
			}
			else{
				locatePieces(right[node], posX, posY + h[left[node]], tree, left, right, x, y, w, h);
				w[node] = Math.max(w[left[node]], w[right[node]]);
				h[node] = h[left[node]] + h[right[node]];
			}
		}
		else{
			w[node] = widths[tree[node]-1];
			h[node] = heights[tree[node]-1];
		}
	}

	public CuttingChromosome createChromosome(int[] tree){
		int[] left = new int[chromosomeSize];
		int[] right = new int[chromosomeSize];
		int[] x = new int[chromosomeSize];
		int[] y = new int[chromosomeSize];
		int[] w = new int[chromosomeSize];
		int[] h = new int[chromosomeSize];
		for(int i=0,id=1; i<chromosomeSize; i++){
			if(tree[i] == codeX || tree[i] == codeY){
				left[i] = id;
				right[i] = id + 1;
				id += 2;
			}
			else{
				left[i] = -1;
				right[i] = -1;
			}
		}
		locatePieces(0, 0, 0, tree, left, right, x, y, w, h);

		// generar constante para el limite
		int score = 100000000 - w[0] * h[0];
		return new CuttingChromosome(tree, left, right, x, y, w, h, score);
	}

	@Override
	public CuttingChromosome createRandomChromosome(){
		int[] tree = new int[chromosomeSize];
		int[] pieces = new int[numberOfPieces];
		for(int i=0; i<numberOfPieces; i++){
			pieces[i] = i+1;
		}
		int createdNodes = 1, usedNodes = 0, availablePieces = numberOfPieces;
		for(int i=0; i<chromosomeSize; i++){
			boolean operator = false, piece = false;
			if(createdNodes < chromosomeSize)
			operator = true;
			if(usedNodes < (createdNodes - 1) || usedNodes == chromosomeSize - 1)
			piece = true;

			usedNodes = i + 1;
			if(piece && operator){
				if(random.nextInt(2) == 0)
				operator = false;
				else
				piece = false;
			}

			if(piece){
				int id = random.nextInt(availablePieces);
				tree[i] = pieces[id];
				pieces[id] = pieces[availablePieces-1];
				pieces[availablePieces-1] = -1;
				availablePieces--;
			}
			else{
				tree[i] = random.nextInt(2) - 2;
				createdNodes += 2;
			}
		}

		return createChromosome(tree);
	}

	private int[] separatePices(int[] tree){
		int[] pieces = new int[numberOfPieces];
		for(int i=0,j=0; i<chromosomeSize; i++){
			if(tree[i] != codeX && tree[i] != codeY){
				pieces[j] = tree[i];
				j++;
			}
		}
		return pieces;
	}

	@Override
	public CuttingChromosome crossoverFunction(CuttingChromosome c1, CuttingChromosome c2){
		// Se requiere saber la posicion de cada pieza en ambos cromosomas
		// 

		int first, last;
		int[] tree;
		int[] tree1 = c1.getTree();
		int[] tree2 = c2.getTree();
		int[] pieces1 = separatePices(tree1);
		int[] pieces2 = separatePices(tree2);
		int[] pieces = new int[numberOfPieces];
		boolean[] usedPieces = new boolean[numberOfPieces+1];
		int[] indexOfPieces1 = new int[numberOfPieces+1];
		int[] indexOfPieces2 = new int[numberOfPieces+1];
		first = random.nextInt(numberOfPieces);
		last = (first + (numberOfPieces / 2)) % numberOfPieces;


	/*	System.out.println(".");
		for(int i=0; i<tree1.length; i++){ System.out.print(tree1[i] + " " ); }System.out.println();
		for(int i=0; i<tree2.length; i++){ System.out.print(tree2[i] + " " ); }System.out.println();
		for(int i=0; i<pieces1.length; i++){ System.out.print(pieces1[i] + " " ); }System.out.println();
		for(int i=0; i<pieces2.length; i++){ System.out.print(pieces2[i] + " " ); }System.out.println();*/

		for(int i=0; i<=numberOfPieces; i++){
			usedPieces[i] = false;
		}
		for(int i=0; i<numberOfPieces; i++){
			pieces[i] = 0;
			indexOfPieces1[pieces1[i]] = i;
			indexOfPieces2[pieces2[i]] = i;
		}
		for(int i=first,j=0; i!=last; i=(i+1)%numberOfPieces, j+=1){
			pieces[i] = pieces1[i];
			usedPieces[pieces1[i]] = true;
		}

		/*for(int i=0; i<usedPieces.length; i++){ System.out.print(usedPieces[i] + " " ); }System.out.println();
		for(int i=0; i<pieces.length; i++){ System.out.print(pieces[i] + " " ); }System.out.println();
		for(int i=0; i<indexOfPieces1.length; i++){ System.out.print(indexOfPieces1[i] + " " ); }System.out.println();
		for(int i=0; i<indexOfPieces2.length; i++){ System.out.print(indexOfPieces2[i] + " " ); }System.out.println();*/

		for(int i=1; i<=numberOfPieces; i++){
			int idPiece = i;
			if(!usedPieces[i]){
				while(pieces[indexOfPieces2[idPiece]] != 0){
					idPiece = pieces1[indexOfPieces2[idPiece]];
				}
				pieces[indexOfPieces2[idPiece]] = i;
				usedPieces[i] = true;
			}
		}

		tree = new int[chromosomeSize];
		for(int i=0,counterOfPieces=0; i<chromosomeSize; i++){
			if(tree1[i] == codeX || tree1[i] == codeY)
			tree[i] = tree1[i];
			else{
				tree[i] = pieces[counterOfPieces];
				counterOfPieces++;
			}
		}		
		return createChromosome(tree);
	}

	@Override
	public CuttingChromosome mutationFunction(CuttingChromosome c){
		Double r = Math.random();
		int[] tree = new int[chromosomeSize];
		int[] tree1 = c.getTree();
		for(int i=0; i<chromosomeSize; i++){
			tree[i] = tree1[i];
		}
		if(r <= mutationPercentageOperator){
			// cambiar operador
			int positionOperator = random.nextInt(numberOfOperators);
			for(int i=0, counterOfOperators=0; i<chromosomeSize; i++){
				if(tree[i] == codeX || tree[i] == codeY){
					if(counterOfOperators == positionOperator){
						if(tree[i] == codeX)
						tree[i] = codeY;
						else
						tree[i] = codeX;
						break;
					}
					counterOfOperators++;
				}
			}
		}
		else if(r <= (mutationPercentagePieces + mutationPercentageOperator)){
			//intercambiar piezas
			int positionPiece1, positionPiece2, id1 = 0, id2 = 0;
			positionPiece1 = random.nextInt(numberOfPieces);
			positionPiece2 = random.nextInt(numberOfPieces);
			for(int i=0, counterOfPieces=0; i<chromosomeSize; i++){
				if(tree[i] != codeX && tree[i] != codeY){
					if(counterOfPieces == positionPiece1)
					id1 = i;
					if(counterOfPieces == positionPiece2)
					id2 = i;

					counterOfPieces++;
				}
			}
			int temp = tree[id1];
			tree[id1] = tree[id2];
			tree[id2] = temp;
		}
		else{
			// intercambiar pieza con operador
			int positionOperator, positionPiece, id1 = 0, id2 = 0;
			positionOperator = random.nextInt(numberOfOperators);
			for(int i=0, counterOfOperators=0; i<chromosomeSize; i++){
				if(tree[i] == codeX || tree[i] == codeY){
					if(counterOfOperators == positionOperator){
						id1 = i;
						break;
					}
					counterOfOperators++;
				}
			}
			// determina la posicion mas alejada a la que puede escoger una pieza
			int createdNodes = 1, limitPieces = 0;
			for(int i=0; i<chromosomeSize && i<createdNodes; i++){
				if(tree[i] == codeX || tree[i] == codeY){
					if(i != id2)
					createdNodes += 2;
				}
				else
				limitPieces++;
			}
			if(limitPieces > 0){
				positionPiece = random.nextInt(numberOfPieces);
				for(int i=0, counterOfPieces=0; i<chromosomeSize; i++){
					if(tree[i] != codeX && tree[i] != codeY){
						if(counterOfPieces == positionPiece){
							id2 = i;
							break;
						}
						counterOfPieces++;
					}
				}
				int temp = tree[id1];
				tree[id1] = tree[id2];
				tree[id2] = temp;
			}
		}

		return createChromosome(tree);
	}
}
