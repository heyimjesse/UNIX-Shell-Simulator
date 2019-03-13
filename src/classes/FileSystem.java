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
 * The File System class will keep track of the root directory, as well as the
 * current working directory.
 */
public class FileSystem {
  /*
   * In the file system, root is the "highest" level, and we can't go above it.
   * The instance variables will keep track of the root folder, the folder of
   * the current working directory, and a string representation of the current
   * working directory.
   */
  /**
   * root The "root" of the file system. currentWorkingDir The folder which is
   * the current working directory.
   */
  private Folder root = new Folder("/");
  private Folder currentWorkingDir = root;
  private static FileSystem fs;

  // An empty constructor is all we need.
  private FileSystem() {
  }
  
  /**
   * Get the instance of file system.
   * 
   * @return The one file system instance.
   */
  public static FileSystem getFS() {
    if (fs == null) {
      fs = new FileSystem();
    }
    return fs;
  }

  /**
   * Returns the root folder of the directory.
   *
   * @return The root folder.
   */
  public Folder getRoot() {
    return this.root;
  }

  /**
   * This resets the current working directory to the root directory
   */
  public void resetCWD() {
    this.currentWorkingDir = this.root;
  }

  /**
   * Sets the current working directory path to given folder.
   *
   * @param folder The folder which will be our new working directory.
   */
  public void setCWD(Folder folder) {
    this.currentWorkingDir = folder;
  }

  /**
   * Returns the pointer to the current working directory.
   *
   * @return A pointer to the current working directory.
   */
  public Folder getCWD() {
    return this.currentWorkingDir;
  }
  
  /**
   * Resets the file system to the starting state.
   */
  public void resetFS() {
    Folder newRoot = new Folder("/");
    this.root = newRoot;
    this.currentWorkingDir = newRoot;
  }
}
