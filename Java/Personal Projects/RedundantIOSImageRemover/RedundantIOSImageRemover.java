import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class RedundantIOSImageRemover {
  public static void main(String args[]) {
    ArrayList<File> imageFiles = new ArrayList<File>();
    HashSet<String> originalImages = new HashSet<String>();
    HashSet<String> editedImages = new HashSet<String>();
    try { // Find file directory
      File directory = new File("ENTER DIRECTORY PATH HERE");
      File[] directoryFiles = directory.listFiles();
      // Create subset of directory with only image files (IMG_*)
      for (File file : directoryFiles) {
        if (file.getName().substring(0, 4).equals("IMG_")) {
          imageFiles.add(file);
          if (!file.getName().contains("E")) { // Original images
            originalImages.add(file.getName().substring(4, 8));
          } else { // Edited images
            editedImages.add(file.getName().substring(5, 9));
          }
        }
      }
      // Intersection of original and edited images 
      originalImages.retainAll(editedImages);
      // Remove original image if edited image exists
      for (File file : imageFiles) {
        if (originalImages.contains(file.getName().substring(4, 8))) {
          file.delete();
        }
      }
    } catch (Exception e) {
      System.out.print("An error occurred.");
    }
  }
}