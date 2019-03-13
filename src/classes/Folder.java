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

import java.util.List;
import java.util.ArrayList;

/**
 * The Folder subclass represents a folder in some directory. A folder can
 * contain objects which are either text files or other folders. We can check
 * what the parent folder is, what objects are in the folder, and also add
 * files to the folder.
 */
public class Folder extends File {
  /*
   * The Folder class has instance variables "contents" (which is a list of
   * either text files or other folders), and "type" (which identifies the
   * folder as a folder).
   */
  /**
   * contents A list representing the contents of the folder.
   */
  private List<File> contents = new ArrayList<File>();

  /**
   * Constructor
   *
   * @param fileName Name of the folder.
   */
  public Folder(String fileName) {
    super(fileName);
    this.type = "FOLDER";
  }

  /**
   * Checks if the folder contains a file.
   *
   * @param fileName name of the file we are looking for
   * @return True of False depending on whether file exists in the folder
   */
  public boolean hasFile(String fileName) {
    /*
     * We loop through every file in the contents of the folder and check their
     * file name.
     */
    for (File file : this.contents) {
      if (file.getName().equals(fileName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes a specified file if exists
   * @param f Any file
   * @return The file
   */
  public File removeFile(File f) {
    if(this.contents.contains(f)) {
      this.contents.remove(f);
      return f;
    } else {
      return null;
    }
  }

  /**
   * Returns true iff file of name fileName is somewhere in the subtree 
   * rooted at file.
   * @param fileName Name of file to be looking for
   * @return True iff fileName is under tree of file
   */
  public boolean hasFileRecursive(String fileName) {
    // Loop through every item under this
    for (File file : this.contents) {
      // If file with fileName found directly inside contents, return true
      if (file.getName().equals(fileName)) {
        return true;
      }
      // If not found directly underneath, recur on folders inside this
      else if (file.type == "Folder") {
        // hasFile if any subfile has fileName
        if (((Folder) file).hasFileRecursive(fileName)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the contents of the folder.
   *
   * @return The contents of the folder.
   */
  public List<File> getContents() {
    return this.contents;
  }
  
  /**
   * Returns the contents of the folder in string representation.
   * 
   * @return A string representing folder's contents.
   */
  public String getContentsString() {
    String retString = "";
    for (File file : this.getContents()) {
      retString = retString + file.getName() + "\n";
    }
    return retString;
  }

  /**
   * Returns the file with the given filename, if it exists
   *
   * @param fileName Name of the file we want
   * @return The file with given filename if it exists, null otherwise
   */
  public File getFile(String fileName) {
    for (File file : this.contents) {
      if (file.getName().equals(fileName)) {
        return file;
      }
    }
    return null;
  }

  /**
   * Adds a file to the folder.
   *
   * @param file The file we are adding to the folder.
   */
  public void addFile(File file) {
    this.contents.add(file); // We simply add the file to the end of the list.
  }

  /**
   * Returns whether the Folder has any contents.
   * 
   * @return True if folder is empty, False otherwise.
   */
  public boolean isEmpty() {
	  return this.contents.isEmpty();
  }
  
  /**
   * Prints out the contents of the folder.
   */
  public void printContents() {
    for (File file : this.contents) {
      System.out.println(file.getName());
    }
  }


  /*
   * main function is just for testing purposes. Will be removed eventually.
   */
  public static void main(String[] args) {
    Folder root = new Folder("/");
    Folder music = new Folder("Music");
    Folder documents = new Folder("Documents");
    Folder work = new Folder("Work");
    Folder memes = new Folder("Memes");

    root.addFile(documents);
    root.addFile(music);
    documents.addFile(work);
    work.addFile(memes);

    documents.setParent(root);
    music.setParent(root);
    work.setParent(documents);
    memes.setParent(work);

    /*
     *          Our directory looks like this:
     *
     *                   root
     *                   /  \
     *           Documents   Music
     *               /
     *           Work
     *             /
     *         Memes
     */

    String s1 = "abc";
    System.out.println(s1);
    String s2 = s1.substring(0, s1.length() - 1);
    System.out.println(s2);
    System.out.println(root.hasFileRecursive("Work"));
  }
}
