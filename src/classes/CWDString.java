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
 * CWDString class is responsible for giving a string representation of the
 * current working directory in a file system.
 */
public class CWDString {

  /**
   * Returns a string representation of the current working directory of the
   * given file system.
   *
   * @param fs A FileSystem object.
   * @return A String representation of the current working directory.
   */
  public static String getString(FileSystem fs) {
    Folder currFolder = fs.getCWD();
    if (currFolder.getName().equals("/")) {
      return "/";
    } else {
      String cwdString = currFolder.getName();
      currFolder = currFolder.getParent();
      while (!currFolder.getName().equals("/")) {
        cwdString = currFolder.getName() + "/" + cwdString;
        currFolder = currFolder.getParent();
      }
      return "/" + cwdString;
    }
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
    String p = CWDString.getString(d);
    System.out.println(p);


  }
}
