import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Danny Nguyen
 * May 8, 2019
 * See GradeMeDriver for program description.
*/

public class Menus {
  final private Scanner input = new Scanner(System.in);

  public void executeProgram() {
    ArrayList<GradeCategory> courseCategories = new ArrayList<GradeCategory>();
    int mainMenuOption;
    displayMainMenu(0);
    mainMenuOption = integerValidation(3);
    System.out.println();
    while (mainMenuOption != -1) {
      switch (mainMenuOption) {
      case 1:
        displayCourseCategories(courseCategories);
        break;
      case 2:
        addNewCourseCategory(courseCategories);
        break;
      case 3:
        editExistingCourseCategories(courseCategories);
        break;
      case 4:
        removeCourseCategory(courseCategories);
        break;
      case 5:
        calculateCourseGrade(courseCategories);
        break;
      }
      mainMenuOption = integerValidation(3);
      System.out.println();
    }
    System.out.println("Program terminated. See you next time!");
  }

  private char charValidation() {
    String userInput;
    char choice = 'z';
    while (choice != 'y' || choice != 'n') {
      try {
        userInput = input.next();
        if (userInput.length() == 1) {
          choice = userInput.charAt(0);
        }
        if (choice == 'y' || choice == 'n')
          break;
        throw new InputMismatchException();
      } catch (InputMismatchException e) {
        input.nextLine();
        System.out.println("Invalid input. Character must be y or n.\n");
        System.out.print("Choice: ");
      }
    }
    return choice;
  }

  private int integerValidation(int variation) {
    int intInput = 0;
    switch (variation) {
    case 1:
      while (intInput <= 0 || intInput > 100) {
        try {
          intInput = input.nextInt();
          if (intInput <= 0 || intInput > 100)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Value must be an integer between 1 - 100.\n");
          System.out.print("Weight(%): ");
        }
      }
      break;
    case 2:
      while (intInput <= 0) {
        try {
          intInput = input.nextInt();
          if (intInput <= 0)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Value must be an integer higher than 0.\n");
          System.out.print("Number of Grades: ");
        }
      }
      break;
    case 3:
      while (intInput <= 0 || intInput > 5) {
        try {
          intInput = input.nextInt();
          if (intInput == -1)
            break;
          if (intInput <= 0 || intInput > 5)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Choice does not exist.\n");
        }
      }
      break;
    case 4:
      while (intInput <= 0 || intInput > 5) {
        try {
          intInput = input.nextInt();
          if (intInput <= 0 || intInput > 5)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Choice does not exist.\n");
        }
      }
      break;
    }
    return intInput;
  }

  private int intSizeValidation(int size) {
    int intInput = 0;
    while (intInput <= 0 || intInput > size) {
      try {
        intInput = input.nextInt();
        if (intInput <= 0 || intInput > size)
          throw new InputMismatchException();
      } catch (InputMismatchException e) {
        input.nextLine();
        System.out.println("Invalid input. Value must be between 1-" + size + ".\n");
      }
    }
    return intInput;
  }

  private double doubleValidation(int variation, int index) {
    double doubleInput = -1;
    switch (variation) {
    case 1:
      while (doubleInput < 0 || doubleInput > 100) {
        try {
          doubleInput = input.nextDouble();
          if (doubleInput < 0 || doubleInput > 100)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Value must be an integer between 0 - 100.\n");
          System.out.print("Grade(" + index + "): ");
        }
      }
      break;
    case 2:
      while (doubleInput < 0 || doubleInput > 100) {
        try {
          doubleInput = input.nextDouble();
          if (doubleInput < 0 || doubleInput > 100)
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
          input.nextLine();
          System.out.println("Invalid input. Value must be an integer between 0 - 100.\n");
          System.out.print("Grade: ");
        }
      }
      break;
    }
    return doubleInput;
  }

  public void displayMainMenu(int variation) {
    if (variation == 1) {
      System.out.println();
    }
    System.out.println("_____________________________");
    System.out.println("|         Grade Me!          |");
    System.out.println("|---------------------------|");
    System.out.println("|1. Display Categories      |");
    System.out.println("|2. Add New Category        |");
    System.out.println("|3. Edit Existing Categories|");
    System.out.println("|4. Remove Category         |");
    System.out.println("|5. Calculate Course Grade  |");
    System.out.println("|-1 Exit                    |");
    System.out.println("|___________________________|\n");
  }

  public void displayCourseCategories(ArrayList<GradeCategory> categories) {
    if (categories.size() == 0) {
      System.out.println("No categories exist.");
    } else {
      System.out.println("____________________________");
      System.out.println("|     Grade Categories     |");
      System.out.println("|--------------------------|");
      int categoryCount = 0;
      int totalWeight = 0;
      for (GradeCategory category : categories) {
        categoryCount++;
        totalWeight += category.getWeight();
        System.out.print(categoryCount + " | " + category + "\n");
      }
      System.out.println("|--------------------------|");
      if (totalWeight < 10) {
        System.out.println("|Total Grade Weight: [ " + totalWeight + "%] |");
      } else if (10 <= totalWeight && totalWeight < 100) {
        System.out.println("|Total Grade Weight: [ " + totalWeight + "%]|");
      } else if (totalWeight == 100) {
        System.out.println("|Total Grade Weight: [100%]|");
      }
      System.out.println("|__________________________|");
    }
    displayMainMenu(1);
  }

  public void addNewCourseCategory(ArrayList<GradeCategory> categories) {
    System.out.println("_____________________");
    System.out.println("| Add New Category  |");
    System.out.println("|-------------------|");
    System.out.println("| Type:             |");
    System.out.println("| Weight(%):        |");
    System.out.println("| Number of Grades: |");
    System.out.println("| Grades:           |");
    System.out.println("|___________________|\n");
    int existingWeight = 0;
    for (GradeCategory category : categories) {
      existingWeight += category.getWeight();
    }
    if (existingWeight == 100) {
      System.out.println("Edit or remove a category. Existing grade weight is already 100%.");
    } else {
      String type;
      input.nextLine();
      System.out.print("Type: ");
      type = input.nextLine();
      for (int i = 0; i < categories.size(); i++) {
        while (categories.get(i).getType().equals(type)) {
          System.out.println("Invalid input. Category already exists.\n");
          System.out.print("Type: ");
          type = input.nextLine();
          i = 0;
        }
      }
      int weight;
      System.out.println("\nExisting Grade Weight: " + existingWeight);
      System.out.println("Allocatable Weight: " + (100 - existingWeight) + "%");
      System.out.print("Weight(%): ");
      weight = integerValidation(1);
      while ((existingWeight + weight) > 100) {
        System.out.println("Invalid input. Grade weight will exceed 100%.\n");
        System.out.println("Existing Grade Weight: " + existingWeight + "%");
        System.out.println("Allocatable Weight: " + (100 - existingWeight) + "%");
        System.out.print("Weight(%): ");
        weight = integerValidation(1);
      }
      int num;
      System.out.print("\nNumber of Grades: ");
      num = integerValidation(2);
      ArrayList<Double> grades = new ArrayList<Double>(); // Grades
      double value;
      for (int i = 0; i < num; i++) {
        System.out.print("\nGrade(" + (i + 1) + "): ");
        value = doubleValidation(1, (i + 1));
        grades.add(value);
      }
      categories.add(new GradeCategory(type, weight, num, grades));
      System.out.println("\nNew category added.");
      System.out.println("\n" + categories.get(categories.size() - 1));
    }
    displayMainMenu(1);
  }

  public void editExistingCourseCategories(ArrayList<GradeCategory> categories) {
    if (categories.size() == 0) {
      System.out.println("No categories exist.");
    } else {
      System.out.println("________________________");
      System.out.println("| Existing Categories  |");
      System.out.println("|----------------------|");
      for (GradeCategory category : categories) {
        System.out.println("|" + category.toString());
      }
      System.out.println("|______________________|\n");
      String type;
      System.out.print("Category: ");
      type = input.next();
      int categoryIndex = -1;
      for (int i = 0; i < categories.size(); i++) {
        if ((categories.get(i).getType()).equals(type)) {
          categoryIndex = i;
          break;
        }
      }
      if (categoryIndex != -1) {
        System.out.println("\n___________________________________");
        System.out.println("|Edit Values in Existing Category:|");
        System.out.println("|" + categories.get(categoryIndex).toString());
        System.out.println("|---------------------------------|");
        System.out.println("|1. Change Category Type          |");
        System.out.println("|2. Change Weight Value           |");
        System.out.println("|3. Add Additional Grades         |");
        System.out.println("|4. Edit Existing Grades          |");
        System.out.println("|5. Remove Existing Grades        |");
        System.out.println("|_________________________________|\n");
        int optionChoice;
        optionChoice = integerValidation(4);
        System.out.println();
        switch (optionChoice) {
        case 1:
          changeCategoryType(categories, categoryIndex, type);
          break;
        case 2:
          changeWeightValue(categories, categoryIndex);
          break;
        case 3:
          addAdditionalGrades(categories, categoryIndex);
          break;
        case 4:
          editExistingGrades(categories, categoryIndex);
          break;
        case 5:
          removeExistingGrades(categories, categoryIndex);
          break;
        }
      } else {
        System.out.println("Category \"" + type + "\" does not exist.");
      }
    }
    displayMainMenu(1);
  }

  public void removeCourseCategory(ArrayList<GradeCategory> categories) {
    if (categories.size() == 0) {
      System.out.println("No categories exist.");
    } else {
      System.out.println("__________________________");
      System.out.println("|Remove Existing Category|");
      System.out.println("|------------------------|");
      for (GradeCategory category : categories) {
        System.out.println("|" + category.toString());
      }
      System.out.println("|________________________|\n");
      String type;
      System.out.print("Category: ");
      type = input.next();
      boolean categoryFound = false;
      for (int i = 0; i < categories.size(); i++) {
        if ((categories.get(i).getType()).equals(type)) {
          categoryFound = true;
          categories.remove(i);
          System.out.println("\nCategory \"" + type + "\" removed.");
          break;
        }
      }
      if (categoryFound == false) {
        System.out.println("Category \"" + type + "\" does not exist.");
      }
    }
    displayMainMenu(1);
  }

  public void calculateCourseGrade(ArrayList<GradeCategory> categories) {
    char fillerCategory = useFillerCategory(categories); 
    double courseGrade = 0;
    System.out.println("_________________________________________________");
    System.out.println("|            Calculate Course Grade             |");
    System.out.println("|-----------------------------------------------|");
    System.out.println("|T: Total | Num: Number of Grades | Avg: Average|\n");
    for (GradeCategory category : categories) {
      double gradeTotal = 0; // Grade total
      for (double grade : category.getGrades()) {
        gradeTotal += grade;
      }
      System.out.println("|(" + category.getQuantity() + ") " + category.getType() + ": " + (category.categoryGrades));
      System.out.printf("|T: %.2f | Avg: %.2f | Weight: " + category.getWeight(), gradeTotal,
          (gradeTotal / category.getQuantity()));
      System.out.print("%\n");
      System.out.printf("|Weighted Contribution: %.2f%n%n",
          (gradeTotal / category.getQuantity() * (category.categoryWeight / 100.0)));
      courseGrade += (gradeTotal / category.getQuantity() * (category.categoryWeight / 100.0));
    }
    if (fillerCategory == 'y') {
      categories.remove(categories.size() - 1);
    }
    System.out.println("|_______________________________________________|");
    System.out.println("|             Overall Course Grade:             |");
    System.out.printf("|                    %.2f", +courseGrade);
    System.out.println("\n|_______________________________________________|");
    displayMainMenu(1);
  }

  private void changeCategoryType(ArrayList<GradeCategory> categories, int categoryIndex, String type) {
    System.out.println("______________________");
    System.out.println("|Change Category Type|");
    System.out.println("|--------------------|");
    System.out.println("|Current: " + categories.get(categoryIndex).getType());
    System.out.println("|____________________|\n");
    System.out.print("Type: ");
    input.nextLine();
    type = input.nextLine();
    for (int i = 0; i < categories.size(); i++) {
      while (categories.get(i).getType().equals(type)) {
        System.out.println("Category \"" + type + "\" already exists.\n");
        System.out.print("Type: ");
        type = input.nextLine();
        i = 0;
      }
    }
    System.out.println("\n\"" + categories.get(categoryIndex).getType() + "\" changed to \"" + type + "\".");
    categories.get(categoryIndex).setType(type);
  }

  private void changeWeightValue(ArrayList<GradeCategory> categories, int categoryIndex) {
    System.out.println("_____________________");
    System.out.println("|Change Weight Value|");
    System.out.println("|-------------------|");
    System.out.println("|Current: " + categories.get(categoryIndex).getWeight() + "%");
    System.out.println("|___________________|\n");
    int existingWeight = 0;
    for (GradeCategory category : categories) {
      existingWeight += category.getWeight();
    }
    int weight = 0;
    System.out.println("Existing Grade Weight: " + existingWeight + "%");
    System.out.println("Allocatable Weight: " + (100 - existingWeight) + "%");
    System.out.print("Weight(%): ");
    weight = integerValidation(1);
    while ((existingWeight - categories.get(categoryIndex).getWeight() + weight) > 100) {
      System.out.println("Invalid input. Grade weight will exceed 100%.\n");
      System.out.println("Existing Grade Weight: " + existingWeight + "%");
      System.out.println("Allocatable Weight: " + (100 - existingWeight) + "%");
      System.out.print("Weight(%): ");
      weight = integerValidation(1);
    }
    System.out.println("\n\"" + categories.get(categoryIndex).getType() + "\" grade weight of "
        + categories.get(categoryIndex).getWeight() + "%\" changed to \"" + weight + "%\".");
    categories.get(categoryIndex).setWeight(weight);
  }

  private void addAdditionalGrades(ArrayList<GradeCategory> categories, int categoryIndex) {
    System.out.println("_______________________________");
    System.out.println("|    Add Additional Grades    |");
    System.out.println("|-----------------------------|");
    System.out.println("|Current: " + (categories.get(categoryIndex).getGrades()));
    System.out.println("|_____________________________|\n");
    int num;
    System.out.print("Number of Grades: ");
    num = integerValidation(2);
    double value;
    for (int i = 0; i < num; i++) {
      System.out.print("\nGrade(" + (i + 1) + "): ");
      value = doubleValidation(1, (i + 1));
      categories.get(categoryIndex).getGrades().add(value);
    }
    categories.get(categoryIndex).setQuantity(categories.get(categoryIndex).getQuantity() + num);
    System.out.println("\n\"" + categories.get(categoryIndex).getType() + "\" grades updated.");
    System.out.println("\n" + categories.get(categoryIndex));
  }

  private void editExistingGrades(ArrayList<GradeCategory> categories, int categoryIndex) {
    System.out.println("____________________________");
    System.out.println("|   Edit Existing Grades   |");
    System.out.println("|" + (categories.get(categoryIndex).getGrades()));
    System.out.println("|__________________________|\n");
    int num;
    System.out.print("Number of Grades to Edit: ");
    num = intSizeValidation(categories.get(categoryIndex).getQuantity());
    int index = 0;
    System.out.println();
    System.out.print("[ ");
    for (double values : categories.get(categoryIndex).getGrades()) {
      index++;
      System.out.print("(" + index + ") " + values + " ");
    }
    System.out.println("]");
    double value = 0;
    for (int i = 0; i < num; i++) {
      System.out.print("\nGrade Index to Edit: ");
      index = intSizeValidation(categories.get(categoryIndex).getQuantity());
      System.out.print("Grade: ");
      value = doubleValidation(2, -1);
      categories.get(categoryIndex).categoryGrades.set((index - 1), value);
    }
    System.out.println("\n\"" + categories.get(categoryIndex).getType() + "\" grades updated.");
    System.out.println("\n" + categories.get(categoryIndex));
  }

  private void removeExistingGrades(ArrayList<GradeCategory> categories, int categoryIndex) {
    System.out.println("____________________________");
    System.out.println("|  Remove Existing Grades  |");
    System.out.println("|" + categories.get(categoryIndex).getGrades());
    System.out.println("|__________________________|\n");
    int num;
    System.out.print("Number of Grades to Remove: ");
    num = intSizeValidation(categories.get(categoryIndex).getQuantity());
    int index = 0;
    for (int i = 0; i < num; i++) {
      System.out.println();
      System.out.print("[ ");
      for (double values : categories.get(categoryIndex).getGrades()) {
        index++;
        System.out.print("(" + index + ") " + values + " ");
      }
      System.out.println("]");
      index = 0;
      System.out.print("\nGrade Index to Remove: ");
      int removeIndex = intSizeValidation(categories.get(categoryIndex).getQuantity());
      categories.get(categoryIndex).getGrades().remove(removeIndex - 1);
    }
    categories.get(categoryIndex).setQuantity(categories.get(categoryIndex).getQuantity() - num);
    System.out.println("\n\"" + categories.get(categoryIndex).getType() + "\" grades updated.");
    System.out.println("\n" + categories.get(categoryIndex));
    if (categories.get(categoryIndex).getQuantity() == 0) {
      System.out
          .println("\n\"" + categories.get(categoryIndex).getType() + "\" was removed due to containing zero grades.");
      categories.remove(categoryIndex);
    }
  }

  private char useFillerCategory(ArrayList<GradeCategory> categories) {
    int existingWeight = 0;
    for (GradeCategory category : categories) {
      existingWeight += category.getWeight();
    }
    char fillerChoice = 'z';
    if (existingWeight < 100) {
      System.out.println("Total Grade Weight: [ " + existingWeight + "%]");
      System.out.print("Grade weight is not 100%. Do you want to use a temporary filler category? (y/n)\nChoice: ");
      fillerChoice = charValidation();
      switch (fillerChoice) {
      case 'y':
        int fillerWeight = 0;
        fillerWeight = 100 - existingWeight;
        ArrayList<Double> fillerGrade = new ArrayList<Double>();
        fillerGrade.add(100.0);
        categories.add(new GradeCategory("Filler", fillerWeight, 1, fillerGrade));
        System.out.println("\nTemporary filler category added.\n");
        break;
      case 'n':
        break;
      }
    }
    return fillerChoice;
  }
}