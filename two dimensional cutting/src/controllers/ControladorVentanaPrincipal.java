package controllers;

import TwoDimensionalCutting.*;
import GeneticAlgorithm.*;
import views.VentanaPrincipal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Integer;
import java.util.Vector;

public class ControladorVentanaPrincipal implements ActionListener{

	private VentanaPrincipal ventanaPrincipal;
	private CuttingChromosomeFactory factory;
	private GeneticAlgorithm<CuttingChromosome> geneticAlgorithm;
//	private int[] widths = {40,60,60,60,110,30,30,50,50};
//	private	int[] heights = {40,100,60,100,30,50,50,40,60};
//	private int[] widths = {4,6,6,6,11,3,3,5,5};
//	private int[] heights = {4,10,6,10,3,5,5,4,6};
//	private int[] widths = {13,48,65,12,35,45,78,56,45,34,17,29};
//	private int[] heights = {14,21,17,34,12,54,14,54,52,87,12,13};
	private int[] widths = {26,96,130,24,70,90,156,112,90,68,34,58};
	private int[] heights = {28,42,34,68,24,108,28,108,104,174,24,26};
	private Vector<CuttingChromosome> best;

	public ControladorVentanaPrincipal(){
		ventanaPrincipal = new VentanaPrincipal(this);
	}

	/*public validarNumero(String text){

	}*/

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "Iniciar"){
			String sPopulationSize = ventanaPrincipal.getTextPopulationSize();
			String sSurvivors = ventanaPrincipal.getTextSurvivors();
			String sIterations = ventanaPrincipal.getTextIterations();
			String sPercentageMutation = ventanaPrincipal.getTextPercentageMutation();
			String sPercentageMutationPiece = ventanaPrincipal.getTextPercentageMutationPiece();
			String sPercentageMutationOperator = ventanaPrincipal.getTextPercentageMutationOperator();
			String sPercentageMutationPieceAndOperator = ventanaPrincipal.getTextPercentageMutationPieceAndOperator();
			int populationSize = Integer.parseInt(sPopulationSize);
			int survivors = Integer.parseInt(sSurvivors);
			int iterations = Integer.parseInt(sIterations);
			int mutationPercentage = Integer.parseInt(sPercentageMutation);
			int mutationPercentagePieces = Integer.parseInt(sPercentageMutationPiece);
			int mutationPercentageOperator = Integer.parseInt(sPercentageMutationOperator);
			int mutationPercentagePieceAndOperator = Integer.parseInt(sPercentageMutationPieceAndOperator);
			ventanaPrincipal.activateTextFieldsGeneticAlgortihmConfiguration(false);
			ventanaPrincipal.setEnabledButtonStart(false);

			best = new Vector<CuttingChromosome>(iterations+1);
			factory = new CuttingChromosomeFactory(widths, heights, mutationPercentagePieces/100.0, mutationPercentageOperator/100.0, mutationPercentagePieceAndOperator/100.0);
			geneticAlgorithm = new GeneticAlgorithm<CuttingChromosome>(factory, mutationPercentage/100.0);
			geneticAlgorithm.initialize(populationSize,survivors);
			best.add(geneticAlgorithm.getBestChromosome());
			run(iterations);
		}
		else if(e.getActionCommand() == "Visualizar"){
			int generacion = Integer.parseInt(ventanaPrincipal.getTextGeneration());
			int[] x,y,w,h;
			x = best.get(generacion).getX();
			y = best.get(generacion).getY();
			w = best.get(generacion).getW();
			h = best.get(generacion).getH();
			ventanaPrincipal.dibujarRectangulos(x,y,w,h);
		}
	}

	public void run(int iterations){
		int[] scores = new int[iterations+1];
		for(int i=1; i<=iterations; i++){
			geneticAlgorithm.nextGeneration();
			Vector<CuttingChromosome> population = geneticAlgorithm.getPopulation();
			best.add(geneticAlgorithm.getBestChromosome());
		//	System.out.println("recibido: " + population);
		/*	System.out.println("Generacion " + i + ":");
			for(int j=0; j<population.size(); j++){
				System.out.println(population.get(j).getFenotype() + "\t" + (100000000 - population.get(j).getScore()));
			}*/
		}
		for(int i=0; i<=iterations; i++){
			scores[i] = 100000000 - best.get(i).getScore();
		}
		/*for(int i=0; i<=iterations; i++){
			System.out.println(i + "\t" + best.get(i).getFenotype() + "\t" + (100000000 - best.get(i).getScore()));
		}*/
		int[] x,y,w,h;
		x = best.get(iterations).getX();
		y = best.get(iterations).getY();
		w = best.get(iterations).getW();
		h = best.get(iterations).getH();
		ventanaPrincipal.dibujarRectangulos(x,y,w,h);
		//ventanaPrincipal.dibujarGrafica(scores);
	}
}