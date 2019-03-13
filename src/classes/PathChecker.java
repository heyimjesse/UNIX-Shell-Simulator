// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: zhang568
// UT Student #: 1000125170
// Author: Jesse Zhang
//
// Student2:
// UTORID user_name: chengmog
// UT Student #: 1004415769
// Author: Mogen Cheng
//
// Student3:
// UTORID user_name: debalke2
// UT Student #: 1004390081
// Author: Saron Debalkew
//
// Student4:
// UTORID user_name: luhaora1
// UT Student #: 1004043091
// Author: Haoran Lu
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package classes;

/**
 * The PathChecker is responsible for checking the validity of string paths.
 */
public class PathChecker {

  /**
   * Checks if the given path is valid.
   *
   * @param d Our main directory, which contains a pointer to root and cwd
   * @param path String representation of a folder
   * @return A boolean depending on whether path is valid
   */
  public static boolean checkPath(FileSystem d, String path) {
    if (path.length() == 0) {
      return true;
    } else if (path.equals("/")) {
      return true;
    }

    // Check if the path is relative or root
    if (path.substring(0, 1).equals("/")) {
      return checkHelper(path.substring(1, path.length()), d.getRoot());
    } else {
      return checkHelper(path, d.getCWD());
    }
  }

  /**
   * Helper method to be used with checkPath.
   *
   * @param path String representation of the path.
   * @param currentFolder The pointer to the current folder.
   * @return True or False depending on whether the path is valid.
   */
  private static boolean checkHelper(String path, File currentFolder) {
    String[] folderList = path.split("/");
    // Iterate through each folder and check if it exists
    for (String folderName : folderList) {
      // Check for .. (up one folder) keyword
      if (folderName.equals("..")) {
        // Move up one folder
        if (currentFolder.hasParent()) {
          currentFolder = ((Folder) currentFolder).getParent();
        }
      } else if (currentFolder.getType().equals("TEXTFILE")) {
        // Going further is only possible if currentFolder is a Folder
        return false;
      } else if (((Folder) currentFolder).hasFile(folderName)) {
        // We know the folder exists, so we move to it
        File nextFolder = ((Folder) currentFolder).getFile(folderName);
        currentFolder = nextFolder;
      } else {
        // The folder does not exist, and therefore should return false
        return false;
      }
    }
    // Since all folders have been navigated to without failure, the path
    // exists.
    return true;
  }

  public static void main(String[] args) {

    FileSystem d = FileSystem.getFS();
    Folder root = d.getRoot();
    Folder d1 = new Folder("d1");
    Folder d2 = new Folder("d2");
    Folder d3 = new Folder("d3");

    TextFile t1 = new TextFile("t1");

    root.addFile(d1);
    d1.addFile(d2);
    d2.addFile(d3);
    d2.addFile(t1);

    d1.setParent(root);
    d2.setParent(d1);
    d3.setParent(d2);
    t1.setParent(d2);

    d.setCWD(d1);

    // Our full file path for d3 looks like this: /d1/d2/d3

    boolean test = checkPath(d, "/d1/d2/d3"); // true
    System.out.println(test);

    boolean test2 = checkPath(d, "d2/d3"); // true
    System.out.println(test2);

    boolean test3 = checkPath(d, "/"); // true
    System.out.println(test3);

    boolean test4 = checkPath(d, "../../d1"); // true
    System.out.println(test4);

    boolean test5 = checkPath(d, "/../d1"); // true
    System.out.println(test5);

    boolean test6 = checkPath(d, "d2/t1"); // true
    System.out.println(test6);

    boolean test7 = checkPath(d, "/d1/d2/t1"); // true
    System.out.println(test7);
  }
}
