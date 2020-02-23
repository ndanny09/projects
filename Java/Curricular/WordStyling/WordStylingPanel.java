import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.*;

public class WordStylingPanel extends JPanel {
  private JLabel saying;
  private JCheckBox bold, italic;
  private JSlider font;

  public WordStylingPanel() {
    saying = new JLabel("Say it with style!");
    saying.setFont(new Font("Helvetica", Font.PLAIN, 12));
    bold = new JCheckBox("Bold");
    bold.setBackground(Color.gray);
    italic = new JCheckBox("Italic");
    italic.setBackground(Color.gray);
    StyleListener listener = new StyleListener();
    bold.addItemListener(listener);
    italic.addItemListener(listener);
    font = new JSlider(JSlider.HORIZONTAL, 12, 36, 12);
    font.setMajorTickSpacing(12);
    font.setMinorTickSpacing(2);
    font.setPaintTicks(true);
    font.setPaintLabels(true);
    font.setAlignmentX(Component.LEFT_ALIGNMENT);
    SliderListener slideListener = new SliderListener();
    font.addChangeListener(slideListener);
    add(saying);
    add(bold);
    add(italic);
    add(font);
    setBackground(Color.gray);
    setPreferredSize(new Dimension(400, 150));
  }

  private class StyleListener implements ItemListener {
    public void itemStateChanged(ItemEvent event) {
      int style = Font.PLAIN;
      if (bold.isSelected()) {
        style += Font.BOLD;
      }
      if (italic.isSelected()) {
        style += Font.ITALIC;
      }
      saying.setFont(new Font("Helvetica", style, font.getValue()));
    }
  }

  private class SliderListener implements ChangeListener {
    private int fontSize;
    public void stateChanged(ChangeEvent event) {
      fontSize = font.getValue();
      saying.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
    }
  }
}