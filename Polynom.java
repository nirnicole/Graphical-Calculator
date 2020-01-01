import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class Polynom {
	
	//we will have a dynamic array of polynom instance and consider them to a sum.
	private ArrayList<PolynomInstance> PolynomPresentation;		
	
	//Costume constructor setting a polynom by a given degrees and coefficients arrays.
	public Polynom(int[] degrees, double[] coefficients) {
		try{
			this.PolynomPresentation = new ArrayList<PolynomInstance>(0);
			
			for(int i = 0 ; i<degrees.length || i<coefficients.length; i++) 
				this.PolynomPresentation.add(new PolynomInstance(degrees[i],coefficients[i]));
			
			}catch(ArrayIndexOutOfBoundsException e){
				//	if(degrees.length != coefficients.length)
				JOptionPane.showMessageDialog(null, "\nError: \nNumber of coefficients and degrees do not match.\n");
				throw e; 	//pop the exception to the main for indication.
			}
		
	}
	
    // copy constructor 
	public Polynom(Polynom c) { 
		
		this.PolynomPresentation = new ArrayList<PolynomInstance>(0);
		for(int i=0; i < c.getPolynomParameters().size(); i++)
			this.PolynomPresentation.add(new PolynomInstance(c.getPolynomParameters().get(i)));
    } 
	
	
	//unite similar degrees
	//this is a private method supporting public sort method, the dependency on a previuos sorting (for efficiency) must be protected.
	private void uniteSimilarDegree() {
		
		int i=0;
		
		//to be efficient and unite in O(n) we will check only the next instance in a polynom(and delete it if we sum it) based on a previuos sort function that gerenteed descending order of degrees.
		while( i<this.PolynomPresentation.size() )
			{
				while( i+1<this.PolynomPresentation.size() && this.PolynomPresentation.get(i+1).getDegree()==this.PolynomPresentation.get(i).getDegree() )
				{
					this.PolynomPresentation.get(i).setCoefficient(this.PolynomPresentation.get(i).getCoefficient() + this.PolynomPresentation.get(i+1).getCoefficient() );
					this.PolynomPresentation.remove(i+1);
				}//end of internal loop
			
			if(this.PolynomPresentation.get(i).getCoefficient()==0)
				this.PolynomPresentation.remove(i);
				else i++;	//if we removed index i, all the sub array shifted left anyway.
			}//end of loop

	}//end of function.
	
	// Sorting the polynom by degree order.
	//O(nlogn) complexity.
	public void sort() {
			Comparator<PolynomInstance> compareByDegree = new Comparator<PolynomInstance>() {
		    @Override
		    public int compare(PolynomInstance o1, PolynomInstance o2) {
		        return Integer.compare(o1.getDegree(),o2.getDegree());
		    }
		};
		
		Collections.sort(this.PolynomPresentation,  Collections.reverseOrder(compareByDegree));		//by descending order
		this.uniteSimilarDegree();	//O(n).
	}

	//added clones of the polynoms and return a polynom(so the source will not be harmed)
	public Polynom plus(Polynom inputParameter) {
		
		Polynom tempPolynomSource = new Polynom(this);
		Polynom tempInputParameterr = new Polynom(inputParameter);

		for(int i=0; i<tempInputParameterr.getPolynomParameters().size() ; i++)
			tempPolynomSource.getPolynomParameters().add(tempInputParameterr.getPolynomParameters().get(i));
		
		tempPolynomSource.sort();
		
		return tempPolynomSource;
	}
	
	//substructing 2 polynoms, again not operating on the source itself
	public Polynom minus(Polynom sourceParameter) {
		
		Polynom temporaryPolynom = new Polynom(sourceParameter);

		//we basicly reverse the sign of each coefficient and repeat the function plus.
		for(int i=0; i<temporaryPolynom.getPolynomParameters().size() ; i++)
			temporaryPolynom.getPolynomParameters().get(i).setCoefficient(temporaryPolynom.getPolynomParameters().get(i).getCoefficient() * (-1));
		
		return this.plus(temporaryPolynom);
	}
	
	//derivative by applying on each instance the derivative formal method
	public Polynom derivative() {
		
		Polynom temporaryPolynom = new Polynom(this);

		temporaryPolynom.sort();
		
		for(int i=0; i<temporaryPolynom.getPolynomParameters().size() ; i++) {
			temporaryPolynom.getPolynomParameters().get(i).setCoefficient(temporaryPolynom.getPolynomParameters().get(i).getCoefficient() * temporaryPolynom.getPolynomParameters().get(i).getDegree());
			temporaryPolynom.getPolynomParameters().get(i).setDegree(temporaryPolynom.getPolynomParameters().get(i).getDegree()-1);
		}

		temporaryPolynom.sort();
		
		return temporaryPolynom;

	}
	
	//comparing each polynom instance and return true or false
	public boolean equals(Polynom sourceParameter) {
		
		Polynom tempPolynomSource = new Polynom(this);
		Polynom tempInputParameterr = new Polynom(sourceParameter);

		tempPolynomSource.sort();
		tempInputParameterr.sort();
		
		for(int i=0; i<tempPolynomSource.getPolynomParameters().size() && i<tempInputParameterr.getPolynomParameters().size(); i++) {
			if(!tempPolynomSource.getPolynomParameters().get(i).isEqualTo(tempInputParameterr.getPolynomParameters().get(i)))
				return false;
		}
		
		return true;
		
	}
	
	//return 100 points represent the polynom from x=-50 to x=50
	public ArrayList<Point> polynomToPoints() {
		
		ArrayList<Point> points = new ArrayList<Point>(100);		

		double coefficient, degree, sum;
		//coff*x^degree
		System.out.println("Generating points of a polynom:");
		for(int i = 0 ; i<100 ; i++) {
			sum=0;
			points.add(new Point());
			points.get(i).setX(i - 50);  //x=-50, -49, ....0,1...49,50
			
			for(int j=0; j< this.PolynomPresentation.size(); j++)
				{
				coefficient = this.PolynomPresentation.get(j).getCoefficient();
				degree = this.PolynomPresentation.get(j).getDegree();
				sum += coefficient * (Math.pow(points.get(i).getX(), degree) );
				}
			points.get(i).setY(sum);
			System.out.println("Point " + (i+1) + " ->\tX: " +points.get(i).getX()+ "\tY: " +points.get(i).getY());
		}
		return points;

	}
	
	
	 
	//getter
	public ArrayList<PolynomInstance> getPolynomParameters() {
		return this.PolynomPresentation;	
	}


	//string the polynom expression
	public String toString()
	{
		String PolynomExpression = new String();
		int polynomIndex = 0;
		
		while(polynomIndex < PolynomPresentation.size())
			{
			PolynomExpression += PolynomPresentation.get(polynomIndex);
			polynomIndex++;
			if(polynomIndex < PolynomPresentation.size())
				if(PolynomPresentation.get(polynomIndex).getCoefficient() >= 0) 
					PolynomExpression += "+";
			}

		return PolynomExpression;
	}
}
