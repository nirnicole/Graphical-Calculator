import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

//this class provides a scanner functions for the main program
public class NumberReader {

	//scan integers array as a string and returns int array
	    public static int[] readIntNumsFromCommandLine() {

			String inputString= JOptionPane.showInputDialog("Please enter a set of degrees for your first polynom:\n(note that you must choose a similar number of coefficients)\n");	        
	        String[] inputParsed = inputString.split(" ");
	        int[] intArray = new int[inputParsed.length], temp;
	        int i=0;
	        
	        for(int j=0;j<inputParsed.length;i++,j++){
	            try{
	            	intArray[i] = Integer.parseInt(inputParsed[j]);
	            }catch(NumberFormatException e){
	            	i--;
	            }
	        }
	        temp = new int[i];
	        temp = Arrays.copyOf(intArray,i);
	        return temp;
	    }
	    
	 //scan doubles array as a string and returns double array
	    public static double[] readDoubleNumsFromCommandLine() {

			String inputString= JOptionPane.showInputDialog("Please enter a set of coefficients for your first polynom:\n(Make sure it match to the number of degrees.)");	        
	        String[] inputParsed = inputString.split(" ");
	        double[] doubleArray = new double[inputParsed.length], temp;
	        int i=0;

	        for(int j=0;j<inputParsed.length;i++,j++){
	            try{
	            	doubleArray[i] = Double.parseDouble(inputParsed[j]);
	            }catch(NumberFormatException e){
	            	i--;
	            }
	        }
	        temp = new double[i];
	        temp = Arrays.copyOf(doubleArray,i);
	        return temp;
	    }
	   
	  //get polynom values from user
		 public static Polynom polynomReader () {
			
			 boolean tryAgainFlag = true;
			 
			 while(tryAgainFlag) {

				 try {		//input the sets of arrays
					 //the user provide 2 sets of 2 arrays to build the polynoms.
					 int[] arrIntNumbers1, arrIntNumbers2;
					 double[] arrDoubleNumbers1, arrDoubleNumbers2;
					 arrIntNumbers1 = NumberReader.readIntNumsFromCommandLine();
					 arrDoubleNumbers1 = NumberReader.readDoubleNumsFromCommandLine();
					 return new Polynom(arrIntNumbers1,arrDoubleNumbers1);
				
					//catches inside exception that constructor has thrown
				 }catch(ArrayIndexOutOfBoundsException error) {
					 if(NumberReader.readYesOrNo())
						 tryAgainFlag=true;
					 else tryAgainFlag = false;			//except "yes" or "no" as indication to start over.
					
				 	}			 		
			 }
			 return null;
		 }
		 
	    
	    //scan yes or no  as a string and return boolean indicator
	    public static boolean readYesOrNo() {

			String inputString;
			
			while(true) {
				inputString = JOptionPane.showInputDialog("do you want to try again? (yes/no)");
				inputString.strip();
			
				if(inputString.equals("yes"))
					{
					  JOptionPane.showMessageDialog(null, "Starting over!");
					return true;
					}
				if(inputString.equals("no"))
					{
					JOptionPane.showMessageDialog(null, "Terminating program, goodbye!");
					return false;
					}
				else {
					JOptionPane.showMessageDialog(null, "'"+inputString+"' is not a valid request, please type again .");
				}
			}
			
}
}
