
//this class represent a singal polynom instance.
public class Point {
	
	private double x;
	private double y;
	
	//default constructor
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	//costume constructor
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//copy constructor
	public Point(Point c) {
		this.x = c.getX();
		this.y = c.getY();
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
		
	public void setY(double y) {
		this.y = y;
	}
	
	public boolean isEqualTo(Point p)
	{
		if(this.x == p.getX()  && this.y == p.getY())
			return true;
		else return false;
	}
	
	//stringing the instance by formal presentation rules
	public String toString()
	{
					
		
		return "(" + this.x + "," + this.y + ")"; 				//"(x,y)"
	}
}

