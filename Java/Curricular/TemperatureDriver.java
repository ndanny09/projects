import javax.swing.JFrame;

/* Temperature Conversion
 * Danny Nguyen
 * April 18, 2019
 * Program is a simple GUI with accessible buttons 
 * that prompts user to input a value to convert 
 * either from Fahrenheit to Celcius or vice versa.
 * 
 * Updated: February 23, 2020
 * Legacy code was preserved and not edited in any way other than
 * the title of the classes being renamed from "Farenheit" &
 * "FarenheitPanel" to "Temperature" & "Temperature Panel" for 
 * better coherence. Unnecessary blank lines were also removed
 * for better readability.
 * 
 * Limitations:
 * - Does not accept user input doubles
 * 
 * What I Learned:
 * Although this is a basic temperature conversion calculator,
 * this was my first project in developing graphical user
 * interfaces using Java swing. I adapted various concepts
 * learned from the classroom in order to implement the GUI
 * such as events, listeners, labels, buttons, and text fields.
 * In the future if I were to improve this program, I would
 * add a validation process to check if the user input anything
 * besides a valid integer and in turn output an error message.
 * It might also be necessary to support double data types and
 * control the degree of decimal places output by the program.
*/

public class TemperatureDriver {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Temperature");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    TemperaturePanel panel = new TemperaturePanel();
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}