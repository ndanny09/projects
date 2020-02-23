import java.awt.Color;
import java.awt.Graphics;

/* Danny Nguyen
 * April 24, 2019
 * See MovingCarDriver class for program description.
*/

public class MovingCar {
  private int x, y;
  private java.awt.Color color;
  
  public MovingCar() {
    x = 0;
    y = 35;
    color = Color.blue;
  }

  public MovingCar(int x, int y, Color color, boolean filled) {
    this.x = 0;
    this.y = 0;
    this.color = color;
  }

  public void drawCar(Graphics car) {
    car.setColor(color);
    int xpoly[] = { x * 3, x * 3 + 0, x * 3 + 75, x * 3 + 100 };
    int ypoly[] = { y, y - 25, y - 25, y };
    car.fillPolygon(xpoly, ypoly, 4);
    car.fillRect(x * 3, y, 100, 25);
    car.setColor(Color.black);
    car.fillOval(x * 3 + 5, y + 15, 25, 25);
    car.fillOval(x * 3 + 65, y + 15, 25, 25);
  }

  public void movement(Graphics move) {
    x++;
    drawCar(move);
  }
}