import java.util.ArrayList;

/* Danny Nguyen
 * May 8, 2019
 * See GradeMeDriver for program description.
*/

public class GradeCategory {
  String categoryType;
  int categoryWeight, categoryQuantity;
  ArrayList<Double> categoryGrades;

  public GradeCategory(String categoryType, int categoryWeight, int gradeQuantity, ArrayList<Double> categoryGrades) {
    this.categoryType = categoryType;
    this.categoryWeight = categoryWeight;
    this.categoryQuantity = gradeQuantity;
    this.categoryGrades = categoryGrades;
  }

  public String getType() {
    return categoryType;
  }

  public int getWeight() {
    return categoryWeight;
  }

  public int getQuantity() {
    return categoryQuantity;
  }

  public ArrayList<Double> getGrades() {
    return categoryGrades;
  }

  public void setType(String categoryType) {
    this.categoryType = categoryType;
  }

  public void setWeight(int categoryWeight) {
    this.categoryWeight = categoryWeight;
  }

  public void setQuantity(int categoryQuantity) {
    this.categoryQuantity = categoryQuantity;
  }

  public void setGrades(ArrayList<Double> categoryGrades) {
    this.categoryGrades = categoryGrades;
  }

  @Override
  public String toString() {
    return "(" + getQuantity() + ") " + getType() + " [" + getWeight() + "%]: " + getGrades();
  }
}
