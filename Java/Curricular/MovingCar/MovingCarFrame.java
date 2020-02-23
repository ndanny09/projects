import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/* Danny Nguyen
 * April 24, 2019
 * See MovingCarDriver class for program description.
*/

public class MovingCarFrame extends JPanel {
  private MovingCar car;

  public MovingCarFrame() {
    car = new MovingCar();
  }

  public void run() {
    while (true) {
      try {
        repaint();
        Thread.sleep(30);
      } catch (InterruptedException event) {
      }
    }
  }

  public void paint(Graphics graphic) {
    super.paint(graphic);
    car.movement(graphic);
  }
}