
//this class represent a singal polynom instance.
public class PolynomInstance {
	
	private int variableDegree;
	private double coefficient;
	
	//default constructor
	public PolynomInstance() {
		this.variableDegree = 0;
		this.coefficient = 0;
	}
	
	//costume constructor
	public PolynomInstance(int degree, double coefficient) {
		this.variableDegree = degree;
		this.coefficient = coefficient;
	}
	
	//copy constructor
	public PolynomInstance(PolynomInstance c) {
		this.variableDegree = c.getDegree();
		this.coefficient = c.getCoefficient();
	}
	
	public int getDegree() {
		return this.variableDegree;
	}
	
	public double getCoefficient() {
		return this.coefficient;
	}
	
	public void setCoefficient(double newcoefficient) {
		this.coefficient = newcoefficient;
	}
		
	public void setDegree(int newDegree) {
		this.variableDegree = newDegree;
	}
	
	public boolean isEqualTo(PolynomInstance p)
	{
		if(this.variableDegree == p.getDegree()  && this.coefficient == p.getCoefficient())
			return true;
		else return false;
	}
	
	//stringing the instance by formal presentation rules
	public String toString()
	{
		if(this.variableDegree < 0)											//"aX^(-b)
			return this.coefficient + "X^(" + this.variableDegree + ")"; 	 
		if(this.variableDegree == 0)										//"a"
			return "" + this.coefficient;
		if(this.variableDegree == 1)										//"aX"
			return this.coefficient + "X";						
		
		return this.coefficient + "X^" + this.variableDegree; 				//"aX^b
	}
}
