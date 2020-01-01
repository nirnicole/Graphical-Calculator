import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//nir nicole, mmn13
//Graph GUI genarator
public class GraphGUI  extends JFrame {

	private static ArrayList<Polynom> polynoms;		//this list holds the current polynoms in use
	private static AxisesScaleGUIExtention graph = new AxisesScaleGUIExtention(10,10);	//the graph itself
	private JPanel buttons = new JPanel();			//will contain all jbuttons
	private JPanel polynomynalPanel = new JPanel();	// will contain all polynom related jbuttons
	private JButton cmdSetX = new JButton("Set X exis");
	private JButton cmdSetY = new JButton("Set Y exis");
	private JButton cmdClearAll = new JButton("Clear All");
	private JButton cmdClearInstance = new JButton("Clear Instance");
	private JButton cmdPolynom = new JButton("Add Polynom");
	private JButton cmdDerivative = new JButton("Derivative");
	private JButton cmdSum = new JButton("Sum");
	private JButton cmdSubstruct = new JButton("Substruct");
	private JButton cmdClosestPair = new JButton("Find Closest Pair");
	private JButton cmdShortestPath = new JButton("Find Shortest Path From u To v");
	private JButton cmdCurrentPolynoms = new JButton("Show Polynoms");

	private static boolean scaleShift = false;		//did a scale shift made
	private static double xScale=10;
	private static double yScale=10;

	
	
	//costume constructor  with only frame sizes
	public GraphGUI(int width, int height) {
		super("Graph: Coordinate System");
		
		polynoms = new ArrayList<Polynom>(0);

		if(!scaleShift)
			graph = new AxisesScaleGUIExtention(5,5);
		this.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(1,3,10,5));
		buttons.add(cmdSetX);
		buttons.add(cmdSetY);
		buttons.add(cmdClearAll);
		buttons.add(cmdClearInstance);

		polynomynalPanel.setLayout(new GridLayout(7,1,1,1));
		polynomynalPanel.add(cmdPolynom);
		polynomynalPanel.add(cmdDerivative);
		polynomynalPanel.add(cmdSum);
		polynomynalPanel.add(cmdSubstruct);
		polynomynalPanel.add(cmdClosestPair);
		polynomynalPanel.add(cmdShortestPath);
		polynomynalPanel.add(cmdCurrentPolynoms);

		ButtonListenerCMD listener = new ButtonListenerCMD();
		cmdSetX.addActionListener(listener);
		cmdSetY.addActionListener(listener);
		cmdClearAll.addActionListener(listener);
		cmdClearInstance.addActionListener(listener);
		cmdPolynom.addActionListener(listener);
		cmdDerivative.addActionListener(listener);
		cmdCurrentPolynoms.addActionListener(listener);
		cmdSum.addActionListener(listener);
		cmdSubstruct.addActionListener(listener);
		cmdClosestPair.addActionListener(listener);
		cmdShortestPath.addActionListener(listener);

		polynomynalPanel.setBackground(Color.lightGray);
		buttons.setBackground(Color.lightGray);
		this.add(buttons, BorderLayout.SOUTH);
		this.add(polynomynalPanel, BorderLayout.WEST);
		this.add(graph, BorderLayout.CENTER);
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scaleShift = false;


	}
	
	//costume constructor with a given array of graph instances
	public GraphGUI(int width, int height, ArrayList<GraphInstance> arrayGraphs) {
		super("Graph: Coordinate System");
		
		polynoms = new ArrayList<Polynom>(0);
		if(!scaleShift)
			graph = new AxisesScaleGUIExtention(5,5,arrayGraphs);
		this.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(1,3,10,5));
		buttons.add(cmdSetX);
		buttons.add(cmdSetY);
		buttons.add(cmdClearAll);
		buttons.add(cmdClearInstance);

		polynomynalPanel.setLayout(new GridLayout(7,1,1,1));
		polynomynalPanel.add(cmdPolynom);
		polynomynalPanel.add(cmdDerivative);
		polynomynalPanel.add(cmdSum);
		polynomynalPanel.add(cmdSubstruct);
		polynomynalPanel.add(cmdClosestPair);
		polynomynalPanel.add(cmdShortestPath);
		polynomynalPanel.add(cmdCurrentPolynoms);

		ButtonListenerCMD listener = new ButtonListenerCMD();
		cmdSetX.addActionListener(listener);
		cmdSetY.addActionListener(listener);
		cmdClearAll.addActionListener(listener);
		cmdClearInstance.addActionListener(listener);
		cmdPolynom.addActionListener(listener);
		cmdDerivative.addActionListener(listener);
		cmdCurrentPolynoms.addActionListener(listener);
		cmdSum.addActionListener(listener);
		cmdSubstruct.addActionListener(listener);
		cmdClosestPair.addActionListener(listener);
		cmdShortestPath.addActionListener(listener);
		
		polynomynalPanel.setBackground(Color.lightGray);
		buttons.setBackground(Color.lightGray);
		this.add(buttons, BorderLayout.SOUTH);
		this.add(polynomynalPanel, BorderLayout.WEST);
		this.add(graph, BorderLayout.CENTER);
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scaleShift = false;


	}
	
	
	 

	public class ButtonListenerCMD implements ActionListener { 
		  public void  actionPerformed(ActionEvent e) {
			  
			  //sets x scale on the graph for all instances
			  if(e.getSource() == cmdSetX) {
				  
			    	double tempXscale =  Double.parseDouble(JOptionPane.showInputDialog("Enter x axis scale:"));
			    	System.out.println("x changed");
			    	System.out.println("x: " + xScale + "=> " + tempXscale);
			    	
				
					for(int pIndex = 0; pIndex<graph.getGraphs().size() ; pIndex++)
					   {		    	
			    			for(int i=0 ; i<graph.getGraphs().get(pIndex).getListOfPoints().size() ; i++)
			    			{					       		System.out.println(	graph.getGraphs().get(pIndex).getListOfPoints().get(i).getX()+"   " + graph.getXScale()+ "   " + tempXscale);

			    				double oldXmax = graph.getXScale() * graph.getNumberOfGaps();
			    				double newXmax = tempXscale * graph.getNumberOfGaps();
				    		
			    				graph.getGraphs().get(pIndex).getListOfPoints().get(i).setX((double)(0.5 + (oldXmax*graph.getGraphs().get(pIndex).getListOfPoints().get(i).getX())/newXmax) - oldXmax/(2*newXmax) );
			    				System.out.println("(" +graph.getGraphs().get(pIndex).getListOfPoints().get(i).getX() + "," + graph.getGraphs().get(pIndex).getListOfPoints().get(i).getY() + ")");
				    		
			    			}
			    		}
			    	
	    			xScale = tempXscale;
	    			graph.setXScale(tempXscale);
			    	scaleShift=true;
			  }
			  
			  //sets y scale on the graph for all instances
			  if(e.getSource() == cmdSetY) {
				  
			    	double tempYScale =  Double.parseDouble(JOptionPane.showInputDialog("Enter y axis scale:"));
			    	System.out.println("y changed");
			    	System.out.println("y: " + yScale + "=> " + tempYScale);   
			    		
			    			// a*oldscale   = b*newscale    => b = a*oldscale/newscale
						for(int pIndex = 0; pIndex<graph.getGraphs().size() ; pIndex++)
						   {		    	
				    			for(int i=0 ; i<graph.getGraphs().get(pIndex).getListOfPoints().size() ; i++)
				    			{					       		System.out.println(	graph.getGraphs().get(pIndex).getListOfPoints().get(i).getY()+"   " + graph.getYScale()+ "   " + tempYScale);

			    				double oldYmax = graph.getYScale() * graph.getNumberOfGaps();
			    				double newYmax = tempYScale * graph.getNumberOfGaps();
				    		
			    				graph.getGraphs().get(pIndex).getListOfPoints().get(i).setY((double)(0.5 - oldYmax/(2*newYmax) + (oldYmax*graph.getGraphs().get(pIndex).getListOfPoints().get(i).getY())/newYmax));
			    				System.out.println("(" +graph.getGraphs().get(pIndex).getListOfPoints().get(i).getX() + "," + graph.getGraphs().get(pIndex).getListOfPoints().get(i).getY() + ")");
				    		
			    				}
						   }
					   	
	    			yScale = tempYScale;
	    			graph.setYScale(tempYScale);
			    	scaleShift=true;

			  }			 
			  
			  //clearing instances of graph
			  if(e.getSource() == cmdClearAll) {
				  
				  System.out.println("cleared");
				  graph.cleanGraph();
				  polynoms.clear();

			  }			 
			  
			  //clearing specific instance
			  if(e.getSource() == cmdClearInstance) {
				  
				  
				  String message = "Choose Instance to delete:\n";
				  
				  message+="Instance" + 0  + ": " + " default graph\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Instance " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom = Integer.parseInt(JOptionPane.showInputDialog(message));
				  
				  if(choosePolynom==0)
				  {
					  graph.removeGraph(0);
				  }else {
					  polynoms.remove(choosePolynom - 1);
					  graph.removeGraph(choosePolynom);
					  System.out.println("instance " + choosePolynom + " cleared.");
				  }

			  }			 
			  
			  //adding new polynom
			  if(e.getSource() == cmdPolynom) {
				  
				  
				  polynoms.add(NumberReader.polynomReader());
				  ArrayList<Point> newAP = polynoms.get(polynoms.size()-1).polynomToPoints() ;
				  GraphInstance newG = new GraphInstance(newAP,false, graph.getMaxX(), graph.getMaxY(), getWidth(), getHeight()  ) ;	  
				  graph.addToGraph( newG ) ;

			  
			  }
			  
			  //Derivative polynom and show it new instance on the graph in red
			  if(e.getSource() == cmdDerivative) {
				  
				  String message = "Choose Polynom:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom = Integer.parseInt(JOptionPane.showInputDialog(message));
				  Polynom derivativeP = polynoms.get(choosePolynom - 1).derivative();
				  polynoms.remove(choosePolynom - 1);
				  polynoms.add(choosePolynom - 1,derivativeP);
				  ArrayList<Point> newAP = polynoms.get(choosePolynom - 1).polynomToPoints() ;
				  GraphInstance newG = new GraphInstance(newAP,true, graph.getMaxX(), graph.getMaxY(), getWidth(), getHeight()  ) ;	  
				  graph.setPolynomInGraph(choosePolynom, newG );
				  
				  System.out.println("\n\n______\n");
				  for(int i=0; i<graph.getGraphs().size(); i++)
					  System.out.println(i + ": \n" + graph.getGraphs().get(i));
						  
				  
				  System.out.println(choosePolynom + " derivative.");
	  
			  
		  }
			  
			  //sum to instances and show the new instance on the graph(and the new polynom created in the list)
			  if(e.getSource() == cmdSum) {
				  
				  String message = "Choose Polynom 1:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom1 = Integer.parseInt(JOptionPane.showInputDialog(message));
				  message = "Choose Polynom 2:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom2 = Integer.parseInt(JOptionPane.showInputDialog(message));
				   Polynom polynomsSum = polynoms.get(choosePolynom1 - 1).plus(polynoms.get(choosePolynom2 - 1));
				  
				  polynoms.remove(choosePolynom1 - 1);
				  polynoms.add(choosePolynom1 - 1,polynomsSum);
				  ArrayList<Point> newAP = polynoms.get(choosePolynom1 - 1).polynomToPoints() ;
				  GraphInstance newG = new GraphInstance(newAP,false, graph.getMaxX(), graph.getMaxY(), getWidth(), getHeight()  ) ;	  
				  graph.setPolynomInGraph(choosePolynom1, newG );
				  graph.removeGraph(choosePolynom2);
				  polynoms.remove(choosePolynom2 - 1);
			  
		  }
			  
			  //substruct 2 polynoms and show the new instance on the graph(and the new polynom created in the list)
			  if(e.getSource() == cmdSubstruct) {
				  
				  String message = "Choose Polynom 1:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom1 = Integer.parseInt(JOptionPane.showInputDialog(message));
				  message = "Choose Polynom 2:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  Integer choosePolynom2 = Integer.parseInt(JOptionPane.showInputDialog(message));
				   Polynom polynomsSub = polynoms.get(choosePolynom1 - 1).minus(polynoms.get(choosePolynom2 - 1));
				  
				   				  
				  if( ! polynoms.get(choosePolynom1 - 1).equals(polynoms.get(choosePolynom2 - 1)))
				  	{ 
					  
					  polynoms.remove(choosePolynom1 - 1);
					  polynoms.add(choosePolynom1 - 1,polynomsSub);

				  ArrayList<Point> newAP = polynoms.get(choosePolynom1 - 1).polynomToPoints() ;
				  GraphInstance newG = new GraphInstance(newAP,false, graph.getMaxX(), graph.getMaxY(), getWidth(), getHeight()  ) ;	  
				  graph.setPolynomInGraph(choosePolynom1, newG );
				  graph.removeGraph(choosePolynom2);
				  polynoms.remove(choosePolynom2 - 1);
				  	}else 
				  		{
				  		if(choosePolynom2>choosePolynom1) {
				  			polynoms.remove(choosePolynom2 - 1);
							graph.removeGraph(choosePolynom2);
				  			polynoms.remove(choosePolynom1 - 1);
				  			graph.removeGraph(choosePolynom1);
				  		}else {
					  		polynoms.remove(choosePolynom1 - 1);
							graph.removeGraph(choosePolynom1);
							polynoms.remove(choosePolynom2 - 1);
							graph.removeGraph(choosePolynom2);
				  			}
				  		}
				  
		  }
			  
			  //show polynoms that are in display at the moment
			  if(e.getSource() == cmdCurrentPolynoms) {
				  
				  String message = "Polynoms:\n";
				  
				  for(int i=0; i<polynoms.size() ; i++) 
					message+="Polynom " + (i+1) + ": " + polynoms.get(i) + "\n";

				  JOptionPane.showMessageDialog(null, message);

				  System.out.println(" showing polynoms.");
			  
		  }
			  
			  //not finished
			  if(e.getSource() == cmdClosestPair) {
				  JOptionPane.showMessageDialog(null, "Function in prograss!");
			  }
			  //not finished
			  if(e.getSource() == cmdShortestPath) {
				  JOptionPane.showMessageDialog(null, "Function in prograss!");
			  }
				  
				  
		repaint();

		  }//end of event handeler
	 }//end of action listner

	
}//end of class
