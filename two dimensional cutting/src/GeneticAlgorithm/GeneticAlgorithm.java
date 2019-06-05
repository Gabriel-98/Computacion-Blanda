package GeneticAlgorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;
import java.lang.Math;

public class GeneticAlgorithm<T extends Chromosome>{

	private ChromosomeFactory<T> chromosomeFactory;
	private Vector<T> population;
	private ChromosomeComparator chromosomeComparator;
	private int populationSize;
	private int survivors;
	private Random random;
	private Double mutationPercentage;

	public GeneticAlgorithm(ChromosomeFactory<T> chromosomeFactory, Double mutationPercentage){
		this.chromosomeFactory = chromosomeFactory;
		chromosomeComparator = new ChromosomeComparator();
		random = new Random();
		this.mutationPercentage = mutationPercentage;
	}

	public void imprimirCromosomas(){
		for(int i=0; i<population.size(); i++){
			System.out.println(population.get(i).getFenotype() + "\t\t" + population.get(i).getScore());
		}
	}

	public Vector<T> getPopulation(){
		// falta clonar
		/*Vector<T> population = new Vector<T>(populationSize);
		for(int i=0; i<populationSize; i++){
			population.add(this.population.get(i).clone());
		}
		System.out.println("enviado: " + population)*/;
		return population;
	}

	public T getBestChromosome(){
		// falta clonarlo
		return population.get(populationSize - 1);
	}

	public void createInitialPopulation(int populationSize){
		this.population = new Vector<T>(populationSize);
		for(int i=0; i<populationSize; i++){
			population.add(chromosomeFactory.createRandomChromosome());
		}
		Collections.sort(population, chromosomeComparator);
		//imprimirCromosomas();
	}

	public void selection(){
		Vector<T> nextPopulation = new Vector<T>(population.size() - survivors);
		int[] ruleta = new int[population.size()];
		ruleta[0] = population.get(0).getScore();
		for(int i=1; i<population.size(); i++){
			ruleta[i] = ruleta[i-1] + population.get(i).getScore();
		}
		int total = ruleta[population.size()-1];
		int[] vectorRandom = new int[population.size() - survivors];
		for(int i=0; i<population.size()-survivors; i++){
			vectorRandom[i] = random.nextInt(total);
		}
		Arrays.sort(vectorRandom);
		for(int i=0,j=0; i<vectorRandom.length; ){
			if(vectorRandom[i] < ruleta[j]){
				nextPopulation.add(population.get(j));
				i++;
			}
			else{
				j++;
			}
		}
		for(int i=0; i<nextPopulation.size(); i++){
			population.set(i, nextPopulation.get(i));
		}
		Collections.sort(population, chromosomeComparator);
	}

	public void crossover(){
		Vector<T> nextPopulation = new Vector<T>(population.size() - survivors);
		for(int i=0; i<population.size() - survivors; i++){
			T c1, c2;
			c1 = population.get(random.nextInt(population.size()));
			c2 = population.get(random.nextInt(population.size()));
			nextPopulation.add(chromosomeFactory.crossoverFunction(c1, c2));
		}
		for(int i=0; i<nextPopulation.size(); i++){
			population.set(i, nextPopulation.get(i));
		}
		Collections.sort(population, chromosomeComparator);
	}

	public void mutation(){
		for(int i=0; i<population.size() - survivors; i++){
			if(Math.random() <= mutationPercentage)
			population.set(i, chromosomeFactory.mutationFunction(population.get(i)));
		}
		Collections.sort(population, chromosomeComparator);
	}

	public void initialize(int populationSize, int survivors){
		this.populationSize = populationSize;
		this.survivors = survivors;
		createInitialPopulation(populationSize);
	}

	public void nextGeneration(){
		selection();
		crossover();
		mutation();
	}
	
}
