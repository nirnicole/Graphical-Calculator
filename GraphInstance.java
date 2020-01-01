import java.util.ArrayList;


//nir nicole, mmn13
//Graph instance genarator
public class GraphInstance {

	private ArrayList<Point> listOfPoints;		
	private  Boolean derivativePolynomIndicator = false;		
	private  Boolean inSource=true;  	//OutSource ==false
	private  Boolean pointsConverted = false;		


		//default constructor
		public GraphInstance() {
			listOfPoints = new ArrayList<Point>(0);
		}
		
		//costume constructor (no convertion needed)
		public GraphInstance(ArrayList<Point> points, boolean derivativeFlag) {
			
			listOfPoints = new ArrayList<Point>(0);
			for(int i = 0; i<points.size() ; i++)
				listOfPoints.add(points.get(i));
			
			setDerivativePolynomIndicator(derivativeFlag);
		}
		
		
		//costume constructor (with convertion)
		public GraphInstance(ArrayList<Point> points, boolean derivativeFlag, double maxX, double maxY,int getWidth, int getHeight) {
			
			listOfPoints = new ArrayList<Point>(0);
		
			for(int i = 0; i<points.size() ; i++)
				listOfPoints.add(points.get(i));
					
			if(!pointsConverted)
				{
				convetOutsourcePoints(listOfPoints, maxX, maxY);
				pointsConverted = true;
				}
			
			derivativePolynomIndicator = derivativeFlag;
		}
		
		//copy constructor
		public GraphInstance(GraphInstance c) {
			
			listOfPoints = new ArrayList<Point>(0);
			for(int i = 0; i<c.listOfPoints.size() ; i++)
				listOfPoints.add(c.listOfPoints.get(i));
			
			 derivativePolynomIndicator = c.derivativePolynomIndicator;		
			 inSource= c.inSource;  	//OutSource ==false
			 pointsConverted = c.pointsConverted;	
		}
		
		
		//getters and setters
		//
		public ArrayList<Point> getListOfPoints() {
			return listOfPoints;
		}
		
		public Boolean getInSource() {
			return inSource;
		}
		
		public Boolean getPointsConverted() {
			return pointsConverted;
		}
		
		public void addPoint(Point p) {
			this.listOfPoints.add(p);
		}
		
		public void setListOfPoints(ArrayList<Point> points, double maxX, double maxY,int getWidth, int getHeight) {

			if(!pointsConverted)
				{
				if(!inSource)
					convetOutsourcePoints(points, maxX, maxY);
				pointsConverted = true;
				}
			
		}
			

		public void setInSource(Boolean flag) {
			inSource = flag ;
		}

		public void setPointsConverted(Boolean flag) {
			pointsConverted = flag ;
		}
		
		


		public Boolean getDerivativePolynomIndicator() {
			return derivativePolynomIndicator;
		}

		public void setDerivativePolynomIndicator(Boolean flag) {
			derivativePolynomIndicator = flag;
		}

		
		//convert outsource ratio to actual point
		public ArrayList<Point> convetOutsourcePoints(ArrayList<Point> points, double maxX, double maxY) {
			
			for(int j=0; j<points.size();j++)
				{
				points.get(j).setX((double)(points.get(j).getX() + maxX)/ (2*maxX ));		// 0.5 + (this.points.get(j).getY())/(2*maxY)
				points.get(j).setY( (double)(maxY - points.get(j).getY())/(2*maxY) );    // 0.5 - (this.points.get(j).getY())/(2*maxY)
				}
			
			return points;
		}
	

		//stringing the instance by formal presentation rules
		public String toString()
		{
			String s = "List of points:\n";		
			for(int i=0; i<this.listOfPoints.size(); i++)
				s+="point " + (i+1) + listOfPoints.get(i) + "\n";
			return s; 				//"(x,y)"
		}

}