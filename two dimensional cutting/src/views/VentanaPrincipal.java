package views;
 
import controllers.ControladorVentanaPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Vector;

public class VentanaPrincipal extends JFrame{

	private ControladorVentanaPrincipal controladorVentanaPrincipal;

	private Container cp;

	private JPanel panelGeneticAlgorithmConfiguration;
	private PanelGraphics panelGraphics;
	private JPanel panelOperations;

	// Labels
	private JLabel labelPopulationSize;
	private JLabel labelSurvivors;
	private JLabel labelIterations;
	private JLabel labelPercentageMutation;
	private JLabel labelPercentageMutationPiece;
	private JLabel labelPercentageMutationOperator;
	private JLabel labelPercentageMutationPieceAndOperator;

	// TextFields
	private JTextField textFieldPopulationSize;
	private JTextField textFieldSurvivors;
	private JTextField textFieldIterations;
	private JTextField textFieldPercentageMutation;
	private JTextField textFieldPercentageMutationPiece;
	private JTextField textFieldPercentageMutationOperator;
	private JTextField textFieldPercentageMutationPieceAndOperator;
	private JTextField textFieldGeneration;

	// Buttons
	private JButton buttonStart;
	private JButton buttonShow;

	// ComboBox
	private JComboBox<String> comboBoxGraphics;

	// opciones comboBoxGraphics
	Vector<String> optionsComboBoxGraphics;
	
	public VentanaPrincipal(ControladorVentanaPrincipal controladorVentanaPrincipal){
		super("Two Dimensional Cutting");
		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
		inicializarParametros();
		configurarVentana();
		inicializarComponentes();
		pack();
		setVisible(true);
	}

	public void inicializarParametros(){
		optionsComboBoxGraphics = new Vector<String>(2);
		optionsComboBoxGraphics.add("grafica score");
		optionsComboBoxGraphics.add("generaciones");
	}

	public void configurarVentana(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void inicializarComponentes(){
		cp = getContentPane();
		cp.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		panelGeneticAlgorithmConfiguration = new JPanel(new GridBagLayout());
	//	panelGraphics = new JPanel();
		panelGraphics = new PanelGraphics();
		panelOperations = new JPanel();
		//panelGraphics.setBackground(Color.BLACK);
		//panelGeneticAlgorithmConfiguration.setBackground(Color.RED);
		panelGraphics.setPreferredSize(new Dimension(800,600));
		//cp.setBackground(Color.BLUE);

		

		// labels
		labelPopulationSize = new JLabel("poblacion:");
		labelSurvivors = new JLabel("sobrevivientes:");
		labelIterations = new JLabel("iteraciones:");
		labelPercentageMutation = new JLabel("mutacion:");
		labelPercentageMutationPiece = new JLabel("pieza:");
		labelPercentageMutationOperator = new JLabel("operador:");
		labelPercentageMutationPieceAndOperator = new JLabel("pieza-operador:");

		//textFields
		textFieldPopulationSize = new JTextField(10);
		textFieldSurvivors = new JTextField(10);
		textFieldIterations = new JTextField(10);
		textFieldPercentageMutation = new JTextField(10);
		textFieldPercentageMutationPiece = new JTextField(10);
		textFieldPercentageMutationOperator = new JTextField(10);
		textFieldPercentageMutationPieceAndOperator = new JTextField(10);
		textFieldGeneration = new JTextField(10);

		// Buttons
		buttonStart = new JButton("Iniciar");
		buttonShow = new JButton("Visualizar");

		// ComboBox
		comboBoxGraphics = new JComboBox<String>(optionsComboBoxGraphics);

		// adiciona componentes al panelGeneticAlgorithm
		constraints.gridx = 0;	constraints.gridy = 0; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelPopulationSize, constraints);
		constraints.gridx = 1;	constraints.gridy = 0; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldPopulationSize, constraints);
		constraints.gridx = 0;	constraints.gridy = 1; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelSurvivors, constraints);
		constraints.gridx = 1;	constraints.gridy = 1; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldSurvivors, constraints);
		constraints.gridx = 0;	constraints.gridy = 2; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelIterations, constraints);
		constraints.gridx = 1;	constraints.gridy = 2; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldIterations, constraints);
		constraints.gridx = 0;	constraints.gridy = 3; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelPercentageMutation, constraints);
		constraints.gridx = 1;	constraints.gridy = 3; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldPercentageMutation, constraints);
		constraints.gridx = 0;	constraints.gridy = 4; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelPercentageMutationPiece, constraints);
		constraints.gridx = 1;	constraints.gridy = 4; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldPercentageMutationPiece, constraints);
		constraints.gridx = 0;	constraints.gridy = 5; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelPercentageMutationOperator, constraints);
		constraints.gridx = 1;	constraints.gridy = 5; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldPercentageMutationOperator, constraints);
		constraints.gridx = 0;	constraints.gridy = 6; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(labelPercentageMutationPieceAndOperator, constraints);
		constraints.gridx = 1;	constraints.gridy = 6; constraints.gridwidth = 1;	constraints.gridheight = 1;
		panelGeneticAlgorithmConfiguration.add(textFieldPercentageMutationPieceAndOperator, constraints);

		// adiciona componentes al panelGraphics
		panelOperations.add(buttonStart);
		panelOperations.add(textFieldGeneration);
		panelOperations.add(buttonShow);
		//panelOperations.add(comboBoxGraphics);

		// adiciona componentes al panel principal
		constraints.gridx = 0;	constraints.gridy = 0;	constraints.gridwidth = 1;	constraints.gridheight = 1;
		cp.add(panelGeneticAlgorithmConfiguration, constraints);
		constraints.gridx = 1;	constraints.gridy = 0;	constraints.gridwidth = 1;	constraints.gridheight = 1;
		cp.add(panelGraphics, constraints);
		constraints.gridx = 0;	constraints.gridy = 1;	constraints.gridwidth = 2;	constraints.gridheight = 1;
		cp.add(panelOperations, constraints);

		// adiciona Listeners
		buttonStart.addActionListener(controladorVentanaPrincipal);
		buttonShow.addActionListener(controladorVentanaPrincipal);
	}

	public String getTextPopulationSize(){
		return textFieldPopulationSize.getText();
	}

	public String getTextSurvivors(){
		return textFieldSurvivors.getText();
	}
	
	public String getTextIterations(){
		return textFieldIterations.getText();
	}

	public String getTextPercentageMutation(){
		return textFieldPercentageMutation.getText();
	}

	public String getTextPercentageMutationPiece(){
		return textFieldPercentageMutationPiece.getText();
	}

	public String getTextPercentageMutationOperator(){
		return textFieldPercentageMutationOperator.getText();
	}

	public String getTextPercentageMutationPieceAndOperator(){
		return textFieldPercentageMutationPieceAndOperator.getText();
	}

	public String getTextGeneration(){
		return textFieldGeneration.getText();
	}

	public void activateTextFieldsGeneticAlgortihmConfiguration(boolean b){
		textFieldPopulationSize.setEditable(b);
		textFieldSurvivors.setEditable(b);
		textFieldIterations.setEditable(b);
		textFieldPercentageMutation.setEditable(b);
		textFieldPercentageMutationPiece.setEditable(b);
		textFieldPercentageMutationOperator.setEditable(b);
		textFieldPercentageMutationPieceAndOperator.setEditable(b);
	}

	public void setEnabledButtonStart(boolean b){
		buttonStart.setEnabled(b);
	}

	public void dibujarRectangulos(int[] x, int[] y, int[] w, int[] h){
		panelGraphics.dibujarRectangulos(x, y, w, h);
		panelGraphics.repaint();
	}

	public void dibujarGrafica(int[] scores){
		panelGraphics.dibujarGrafica(scores);
		panelGraphics.repaint();
	}
}