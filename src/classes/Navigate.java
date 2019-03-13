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
 * The Navigate class is responsible for traversing string paths in our file
 * system.
 */
public class Navigate {

  /**
   * Navigates to the folder represented by the given path and returns it
   *
   * @param d Our main directory, which contains a pointer to the root and cwd
   * @param path String representation of a folder
   * @return A pointer to the destination Folder.
   */
  public static File navigateTo(FileSystem d, String path) {

    // These gets rid of the edge cases
    if (path.length() == 0) {
      return d.getCWD();
    } else if (path.equals("/")) {
      return d.getRoot();
    }

    File cwd;
    String newPath = path;

    if (PathChecker.checkPath(d, path)) {
      if (path.substring(0, 1).equals("/")) {
        cwd = d.getRoot();
        newPath = path.substring(1, path.length());
      } else {
        cwd = d.getCWD();
      }

      // Navigate to the path
      String[] folderList = newPath.split("/");
      // Iterate through each folder
      for (String folderName : folderList) {
        // The back command
        if (folderName.equals("..")) {
          if (!cwd.getName().equals("/")) {
            cwd = cwd.getParent();
          }
        } else {
          // Not a back command, so just move to that folder
          cwd = ((Folder) cwd).getFile(folderName);
        }
      }
    } else {
      // path is invalid. Return the current working directory.
      return d.getCWD();
    }

    // Return the new working directory
    return cwd;
  }

  public static void main(String[] args) {
    FileSystem d = FileSystem.getFS();
    Folder root = d.getRoot();
    Folder d1 = new Folder("d1");
    Folder d2 = new Folder("d2");
    Folder d3 = new Folder("d3");

    root.addFile(d1);
    d1.addFile(d2);
    d2.addFile(d3);

    d1.setParent(root);
    d2.setParent(d1);
    d3.setParent(d2);

    d.setCWD(d3);

    File newFolder = navigateTo(d, "../..");
    System.out.println(newFolder.getName());
  }
}