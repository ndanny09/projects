import javax.swing.JFrame;

/* Danny Nguyen
 * April 24, 2019
 * Program displays a blue car moving from the left
 * to the right of the screen.
 * 
 * Updated: February 23, 2020
 * An unused private field called "filled" in the MovingCar 
 * class was removed. "MovingCarDriver" class was created from 
 * the separation of the main method from the MovingCar class.
 * Unnecessary blank lines were also removed for easier 
 * readability. The rest of the code remains untampered with. 
 * 
 * Limitations:
 * - Car drives off the right side of the screen (and doesn't return).
 * 
 * What I Learned:
 * In order to create this program, I had to explore the features 
 * that Java graphics provide. There was also a new concept I learned 
 * to implement in establishing a thread timer to constantly redraw 
 * the car to give the illusion of movement. An extension to this 
 * project would be to create a secondary image built of the car to 
 * mirror movement from the right side to the left side of the screen once 
 * it detected by x coordinate that it reached the right side of the window.
 */

public class MovingCarDriver {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    MovingCarFrame panel = new MovingCarFrame();
    frame.setTitle("Moving Car");
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 125);
    frame.setVisible(true);
    panel.run();
  }
}
