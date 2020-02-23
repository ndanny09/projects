import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Temperature Conversion Panel
 * Danny Nguyen
 * April 18, 2019
 * See TemperatureDriver class for program description.
*/

public class TemperaturePanel extends JPanel {
  private JLabel inputLabel, outputLabel, resultLabel;
  private JTextField temperature;
  private JButton f, c;

  public TemperaturePanel() {
    inputLabel = new JLabel("Temperature: ");
    outputLabel = new JLabel("Temperature Conversion: ");
    resultLabel = new JLabel("");
    temperature = new JTextField(3);
    f = new JButton("Convert to Fahrenheit (C -> F)");
    c = new JButton("Convert to Celcius (F -> C)");
    ButtonListener listener = new ButtonListener();
    f.addActionListener(listener);
    c.addActionListener(listener);
    add(inputLabel);
    add(temperature);
    add(f);
    add(c);
    add(outputLabel);
    add(resultLabel);
    setPreferredSize(new Dimension(250, 125));
    setBackground(Color.gray);
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (event.getSource() == f) {
        int fahrenheitTemp, celciusTemp;
        String text = temperature.getText();
        celciusTemp = Integer.parseInt(text);
        fahrenheitTemp = (celciusTemp * 9 / 5) + 32;
        resultLabel.setText(Integer.toString(fahrenheitTemp) + " F");
      }
      if (event.getSource() == c) {
        int fahrenheitTemp, celciusTemp;
        String text = temperature.getText();
        fahrenheitTemp = Integer.parseInt(text);
        celciusTemp = (fahrenheitTemp - 32) * 5 / 9;
        resultLabel.setText(Integer.toString(celciusTemp) + " C");
      }
    }
  }
}
