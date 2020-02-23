/* Danny Nguyen
 * May 8, 2019
 * Grade Me! is a weighted course grade calculator
 * that has the flexibility to estimate total course grade
 * early in the semester based on existing and missing 
 * data input by the user. The program accepts and validates 
 * user input by checking for potential errors in data 
 * types and ranges. Menu features include displaying, 
 * adding, editing, and removing categories and their 
 * fields' data.
 * 
 * Updated: February 23, 2020
 * The code remains mostly the same besides rebranding the 
 * program to "Grade Me!" from "Weighted Course Grade Calculator". 
 * Some of the fields and methods were refactored to be self 
 * commenting, eliminating the need for comments as to what 
 * they represent and do. Unnecessary blank lines were removed 
 * for easier readability.
 * 
 * Known Bugs:
 * - Naming a category that is two words (separated by a space)
 * is unrecognizable by the program to be edited or removed. 
 * 
 * Inconveniences:
 * - If the user makes an incorrect choice, they will be forced
 * to perform that incorrect choice's function to return to the 
 * main menu. There are no return features implemented.
 * - The program does not store any permanent data about the
 * course grade categories, forcing the user to input the 
 * categories and their fields each time they wish to calculate
 * their grade on a different instance of the program.
 * 
 * What I Learned:
 * This program has been the largest effort in terms of size
 * and necessary computational logic committed to my own personal
 * projects so far. I still use this program from time to time to
 * have the convenience of calculating course grades early in the
 * semester and predicting potential outcomes for upcoming grades. The
 * newest concepts I employed in this program which I have not
 * used before in my previous projects have included accounting for
 * potential error exceptions and displaying their appropriate
 * mismatch, and multilevel menu options. As the program began to
 * grow more complex, it became more necessary to map out their 
 * pathways and how each method interacts with its category field.
 * In the scenario that I would revisit this program and improve
 * its functionality, I would look into storing user category data
 * in some type of file for later instances so that it would not 
 * require inputting category field data from scratch, as well as 
 * adding some form of a return input or feature that allows escaping
 * the current method that is being run. 
 */

public class GradeMeDriver {
	public static void main(String args[]) {
		Menus menu = new Menus();
		menu.executeProgram();
	}
}
