import TwoDimensionalCutting.*;
import GeneticAlgorithm.*;

import java.util.Vector;

public class Main2{
	
	public static void main(String[] args){
		int[] widths = {40,60,60,60,110,30,30,50,50};
		int[] heights = {40,100,60,100,30,50,50,40,60};
		
		int populationSize = 2;
		int survivors = 2;
		int iterations = 10;

		Double mutationPercentage = 0.1;
		Double mutationPercentagePieces = 0.3; 
		Double mutationPercentageOperator = 0.3;
		Double mutationPercentagePieceAndOperator = 0.4;

		CuttingChromosome cuttingChromosome;
		CuttingChromosomeFactory factory = new CuttingChromosomeFactory(widths, heights, mutationPercentagePieces, mutationPercentageOperator, mutationPercentagePieceAndOperator);
		GeneticAlgorithm<CuttingChromosome> geneticAlgorithm = new GeneticAlgorithm<CuttingChromosome>(factory, mutationPercentage);
		geneticAlgorithm.initialize(populationSize,survivors);
		for(int i=1; i<=iterations; i++){
			geneticAlgorithm.nextGeneration();
			Vector<CuttingChromosome> population = geneticAlgorithm.getPopulation();
		//	System.out.println("recibido: " + population);
			//System.out.println("Generacion " + i + ":");
			//for(int j=0; j<population.size(); j++){
			//	System.out.println(population.get(j).getFenotype() + "\t" + (1000000 - population.get(j).getScore()));
			//}
		}
	}
}