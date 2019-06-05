package GeneticAlgorithm;

public interface ChromosomeFactory<T extends Chromosome>{
	public T createRandomChromosome();
	public T crossoverFunction(T c1, T c2);
	public T mutationFunction(T c);
}