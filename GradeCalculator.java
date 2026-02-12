/*
 * Class: CMSC203 
 * Instructor: Dr. Ahmed Tarek
 * Description: (Give a brief description for each Class)
 * Due: 02/12/2026
 * Platform/compiler: Eclipse IDE
 * I pledge that I have completed the programming assignment independently. 
 I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Yisihak B. Woldemariam
*/

package application;
import java.util.Scanner;
import java.io.*;

public class GradeCalculator {
	
	public static void main(String[] args) throws java.io.IOException {
		
		// Writing the Outputs into a Text File
			PrintWriter gradeOut = new PrintWriter("grades_report.txt");

		// Reading in Grade Configuration File		
			Scanner configFile = new Scanner(new File("gradeconfig.txt"));	
			String courseName = configFile.nextLine();
			int catNum= configFile.nextInt();
			configFile.nextLine();
			
		// Category Reading Loop
				double totalWeight = 0;
				for(int i = 0; i < catNum; i++)
				{
					String catName = configFile.next();
					double catWeight = configFile.nextDouble();
					totalWeight += catWeight;
				}			

				if (totalWeight != 100) 
				{	
					System.out.println("Invalid Configuration. System will use default.");		
					gradeOut.println("Invalid Configuration. System will use default.");		
				} 
				else 
				{
					System.out.println("Configuration Loaded Successfully.\n");
					gradeOut.println("Configuration Loaded Successfully.\n");
				}

		// Reading in student name, categories, counts, and scores
			Scanner gradeinFile = new Scanner(new File("grades_input.txt"));
			
			String firstName = gradeinFile.nextLine();
			String lastName = gradeinFile.nextLine();
			
			configFile = new Scanner(new File("gradeconfig.txt"));
			configFile.nextLine();
			configFile.nextInt();
			configFile.nextLine();
		
			System.out.println(courseName);
			gradeOut.println(courseName);
			
			System.out.println("Student Name: " + firstName + " " + lastName);
			gradeOut.println("Student Name: " + firstName + " " + lastName);
			
			// Category and Weight Reading Loop
			double finalGrade = 0;
			for (int i = 0; i < catNum; i++)		
			{	
				String catName = configFile.next();
				double catWeight = configFile.nextDouble();

				gradeinFile.next();
				int gradeNum = gradeinFile.nextInt();

					double sum = 0;
					
					for (int j = 0; j < gradeNum; j++) 
					{
						sum += gradeinFile.nextDouble();
					}
					gradeinFile.nextLine();
					double avg = sum / gradeNum;
					finalGrade += avg * (catWeight / 100);
				
				System.out.printf("%n%s (%.0f%%): average = %.2f", catName, catWeight, avg);
				gradeOut.printf("%n%s (%.0f%%): average = %.2f", catName, catWeight, avg);
			}	
			
			// Determining the Letter Grade (De)
				char letterGrade;
				if (finalGrade >= 90) {
					letterGrade = 'A';
				} else if (finalGrade >= 80) {
					letterGrade = 'B';
				} else if (finalGrade >= 70) {
					letterGrade = 'C';
				}else if (finalGrade >= 60) {
					letterGrade = 'D';
				}else {
					letterGrade = 'F';
				}
			
				// Prompt user if +/- grading should be applied
			System.out.println("\n\nApply +/- grading? (Y/N): ");
			Scanner userInput = new Scanner(System.in);
			String userChoice = userInput.next();
			
			// Input Validation (Check if the Correct Input is Entered)
				while (!(userChoice.equalsIgnoreCase("Y")) && !(userChoice.equalsIgnoreCase("N")))
					{				
						System.out.println("Please try again uisng (Y/N)");	
						userChoice = userInput.next();
					}
			// Using Modulus Operator to Determine the Last Digit of the Final Grade in order to Apply +/- Grading
			String sign = "";
				if (userChoice.equalsIgnoreCase("Y") && letterGrade != 'F')
				{
					double lastDigit = finalGrade % 10;
				
					if (lastDigit >= 7) 
					{
						sign = "+";
					
					}else if (lastDigit <= 3) 
					{
						sign = "-";
					}
			}
				
		// Printing Final Grade to the Console and Output File
			System.out.printf("Final Numeric Grade: %.2f%n", finalGrade);
			gradeOut.printf("Final Numeric Grade: %.2f%n", finalGrade);
			
			System.out.printf("Base Letter Grade: %s%n", letterGrade);
			gradeOut.printf("Base Letter Grade: %s%n", letterGrade);
			
			System.out.println("Final Letter Grade:" + letterGrade + sign);
			gradeOut.println("Final Letter Grade: " + letterGrade + sign);
			
			System.out.println("\nSummary written to grades_report.txt");
			System.out.println("\nProgrammer: Yisihak B. Woldemariam");
		// CLosing Opened Files and Terminating Background Running Thread 	
			configFile.close();
			gradeinFile.close();
			gradeOut.close();
			System.exit(0);
	}	
}