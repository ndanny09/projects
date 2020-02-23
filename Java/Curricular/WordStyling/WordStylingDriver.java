import javax.swing.JFrame;

/* Danny Nguyen
 * April 18, 2019
 * Program is a minimal preset text customizer that
 * has the ability to bold, italicize, or adjust
 * the font size.
 * 
 * Updated: February 23, 2020
 * Legacy code was preserved and not edited in any
 * way other than the title of the classes being renamed
 * from "StyleOptions" & "StyleOptionsPanel" to 
 * "WordStylingDriver" and "WordStylingPanel" for better
 * coherence. Unnecessary blank lines were also removed
 * for better readability.
 * 
 * Limitations:
 * - The text is preset in WordStylingPanel. Changes to
 * the text must be done before the program is run.
 * 
 * What I Learned:
 * In developing my familiarity with the usage of Java
 * swing, I was able to incorporate the features of style
 * and slider listeners as well as demonstrate interaction 
 * with string data types in a graphical way. In the event
 * that I were to update this project, I would expand the
 * ability for the user to input their own text while the 
 * program is running and have a refresh button in order to
 * "update" their text to the current set settings.
 */

public class WordStylingDriver {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Style Options");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new WordStylingPanel());
    frame.pack();
    frame.setVisible(true);
  }
}
