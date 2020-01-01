import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

//nir nicole, mmn13
//Graph panel genarator
public class AxisesScaleGUIExtention extends JPanel{

	private static int scaleX = 10;
	private static int scaleY = 10;
 	private static int numberOfGaps = 20;
 	private int width = MINIMUM_ROUNDED_LENGTH;
 	private int height = MINIMUM_ROUNDED_LENGTH;
	private static ArrayList<GraphInstance> arrayListOfGraphs;		


	private static final int MINIMUM_ROUNDED_LENGTH = 100; 
	
	
	 
    // default constructor 
	public AxisesScaleGUIExtention() 
    { 
    } 
	
    // costume constructor 
    public AxisesScaleGUIExtention(int scaleX, int scaleY)
    {
    	
    	AxisesScaleGUIExtention.arrayListOfGraphs = new ArrayList<GraphInstance>(0);
    	GraphInstance initialGraphInstance = new GraphInstance();
    	AxisesScaleGUIExtention.arrayListOfGraphs.add(initialGraphInstance);
    	
    	AxisesScaleGUIExtention.scaleX = scaleX;
    	AxisesScaleGUIExtention.scaleY = scaleY;
        this.addMouseListener(new MouseListenerCMD());
    }
    
    // costume constructor with premade set of graphs
    public AxisesScaleGUIExtention(int scaleX, int scaleY, ArrayList<GraphInstance> arrayGraphs)
    {
    	AxisesScaleGUIExtention.arrayListOfGraphs = new ArrayList<GraphInstance>(0);
    	GraphInstance initialGraphInstance = new GraphInstance();
    	AxisesScaleGUIExtention.arrayListOfGraphs.add(initialGraphInstance);
    	    	
    	for(int index=0 ; index<arrayGraphs.size(); index++)
    		arrayListOfGraphs.add(arrayGraphs.get(index));

    	AxisesScaleGUIExtention.scaleX = scaleX;
    	AxisesScaleGUIExtention.scaleY = scaleY;
        this.addMouseListener(new MouseListenerCMD());
    }


	 
	 @Override
	    public void paintComponent(Graphics g)
	    {
		   super.paintComponent(g); 
		   	
		   if(this.getWidth()>MINIMUM_ROUNDED_LENGTH && this.getHeight()>MINIMUM_ROUNDED_LENGTH)
		 	{
		 	width = this.getWidth();
		 	height = this.getHeight();
		 	}
	     	
	        //  Draw scale lines starting from top to bottom
	        int parameterLine = 2;
	        
	        int maxX = numberOfGaps*scaleX/2;
	        int maxY = numberOfGaps*scaleY/2;
	        
	        //draw Y axis
            g.drawLine(width/2, 0, width/2, height);
            //draw X axis
            g.drawLine(0, height/2,width,height/2);
            
            //drawing x scales
	        for (int i = 1; i<numberOfGaps ; i++)
	        {	
	        	if(i!=numberOfGaps/2) {
	        		g.drawString(String.valueOf(i*scaleX - maxX) , i*width/numberOfGaps, height/2-3-parameterLine);
	        		g.drawLine(i*width/numberOfGaps, height/2-parameterLine,i*width/numberOfGaps,height/2+parameterLine);
	        		}else {
		        		g.drawString(String.valueOf(i*scaleX - maxX) , i*width/numberOfGaps+3, height/2-1-parameterLine);			// (0,0)
		        		g.drawLine(i*width/numberOfGaps, height/2-parameterLine,i*width/numberOfGaps,height/2+parameterLine);
	        		}
	        		
	        }
	        
	        //drawing y scales
	        for (int i = 1; i<numberOfGaps; i++)
	        {	
        		if(i!=numberOfGaps/2) {
        		    g.drawString(String.valueOf(maxY - i*scaleY) , width/2+2*parameterLine , i*height/numberOfGaps+parameterLine);
	        		g.drawLine(width/2-parameterLine, i*height/numberOfGaps, width/2+parameterLine,  i*height/numberOfGaps);
        		}
	        		
	        }
	        
	        //draw the instancess on the graph
	        //
	        if(arrayListOfGraphs.size()>0) {       
	        	
	        	for(int pIndex = 0; pIndex<arrayListOfGraphs.size() ; pIndex++)
	        	{
	        		GraphInstance GraphPoints =  arrayListOfGraphs.get(pIndex);	
	        
	    	
	        		for(int i =0; i<GraphPoints.getListOfPoints().size() ;i++){
	        			g.setColor(Color.blue);
	        			int xCoordinate =  (int)(GraphPoints.getListOfPoints().get(i).getX() * this.getWidth());
    					int yCoordinate =  (int)((GraphPoints.getListOfPoints().get(i).getY()) * this.getHeight());
    					int xInormativeValue = (int)( Math.rint(GraphPoints.getListOfPoints().get(i).getX() * 2* maxX - maxX));
    					int yInormativeValue = (int) Math.rint((maxY - GraphPoints.getListOfPoints().get(i).getY() *2* maxY));
    					
    					g.fillOval(xCoordinate, yCoordinate , 5, 5);
    					String s = "(" + (int)(xInormativeValue) + "," + (int)(yInormativeValue) + ")";
    					g.drawString(s, xCoordinate, yCoordinate );
    				
    					if(i>0)
    					{
    						int previusXCoordinate =  (int)(GraphPoints.getListOfPoints().get(i-1).getX() * this.getWidth());
    						int previusYCoordinate =  (int)(GraphPoints.getListOfPoints().get(i-1).getY() * this.getHeight());
    						if(pIndex>0)
    							g.setColor(Color.green);
    						if(GraphPoints.getDerivativePolynomIndicator())
    							g.setColor(Color.RED);
    						g.drawLine(xCoordinate,yCoordinate , previusXCoordinate , previusYCoordinate);
	    					}
    					g.setColor(Color.black);  
      					}
	        		GraphPoints.setDerivativePolynomIndicator(false);
	        	}
	        }
	    		
	    }
	 
	 //relevant functions to handel graph
	 //
	 public void cleanGraph() {

			arrayListOfGraphs.clear();
			arrayListOfGraphs.add(new GraphInstance());
	    }
	    
	public void addToGraph(GraphInstance newGraph) {
			 
			 arrayListOfGraphs.add(newGraph);
		 }
		 
	public void setPolynomInGraph(int index, GraphInstance newGraph) {
			 
			 arrayListOfGraphs.remove(index);
			 arrayListOfGraphs.add(index,newGraph);

		 }

	public void removeGraph(int index) {
				if(index>0)
					arrayListOfGraphs.remove(index);
				else if(index==0)
					setPolynomInGraph(index, new GraphInstance());
					
		    }
			
	//getters and setters
	//
	 public ArrayList<GraphInstance> getGraphs(){
		 return AxisesScaleGUIExtention.arrayListOfGraphs;
	 }
	 
	 public double getXScale()
	 {
		 return this.scaleX;
	 }

	 public double getYScale()
	 {
		 return this.scaleY;
	 }

	 public int getNumberOfGaps()
	 {
		 return numberOfGaps;
	 }
	 
	 public double getMaxX() {
		 return numberOfGaps*scaleX/2;
	 }
	
	 public double getMaxY() {
		 return numberOfGaps*scaleY/2;
	 }

	 public void setXScale(double newScale )
	 {
		 this.scaleX = (int)newScale;
	 }

	 public void setYScale(double newScale )
	 {
		this.scaleY = (int)newScale;
	 }
	 
	 
	 private class MouseListenerCMD implements MouseListener { 
	
		    // default constructor 
			MouseListenerCMD() 
		    { 

		    }

			  
		    // getX() and getY() functions return the 
		    // x and y coordinates of the current 
		    // mouse position 
		    // getClickCount() returns the number of 
		    // quick consecutive clicks made by the user 
		  
			//must be implemented
			//
		    public void mousePressed(MouseEvent e) 
		    { 
		    } 
		    public void mouseReleased(MouseEvent e) 
		    { 
		    } 	  
		    public void mouseExited(MouseEvent e) 
		    { 
		    } 
		    public void mouseEntered(MouseEvent e) 
		    { 
		    } 
		  
		    // this function is invoked when the mouse is pressed or released 
		    public void mouseClicked(MouseEvent e) 
		    { 
		  
		        // getClickCount gives the number of quick, 
		        // consecutive clicks made by the user 
		        // show the point where the mouse is i.e 
		        // the x and y coordinates 
		    		
		    	arrayListOfGraphs.get(0).addPoint( convetInsourcePoint(new Point( e.getX(),e.getY()) , getMaxX(),getMaxY(),getWidth(),getHeight() ) ) ;     
		    	System.out.println("point generated");
	            repaint();
		    } 
    		
	 }
	 
	 //a function to convert insource ratio to points
		public  Point convetInsourcePoint( Point point, double maxX, double maxY, int getWidth, int getHeight) {

			point.setX((double)point.getX()/getWidth);
			point.setY((double)point.getY()/getHeight );
			
			return point;
		}


}//end of class
