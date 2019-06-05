package GeneticAlgorithm;

import java.util.Comparator;

public class ChromosomeComparator implements Comparator<Chromosome>{

	public int compare(Chromosome c1, Chromosome c2){
		if(c1.getScore() < c2.getScore())
		return -1;
		else if(c1.getScore() == c2.getScore())
		return 0;
		return 1;
	}
}