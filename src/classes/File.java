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
import classes.Folder;
import classes.TextFile;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 * The File superclass provides a base for the subclasses TextFile and Folder.
 */
public abstract class File {

  /**
   * fileName The name of the file. type The type of the file. Can be TEXTFILE
   * or FOLDER. parent The parent of the current file.
   */
  private String fileName;
  public String type;
  private Folder parent;

  public void rename(String name) {
    this.fileName = name;
  }
  /**
   * Constructor
   *
   * @param name Name of the file.
   */
  public File(String name) {
    this.fileName = name;
  }

  /**
   * Returns the name of the file
   *
   * @return The name of the file.
   */
  public String getName() {
    return this.fileName;
  }

  /**
   * Returns the type of the file.
   *
   * @return Returns TEXTFILE or FOLDER.
   */
  public String getType() {
    return this.type;
  }

  /**
   * Check if the file has a parent.
   *
   * @return True/False depending on whether it has a parent.
   */
  public boolean hasParent() {
    return (this.parent != null);
  }

  /**
   * Sets the parent of this folder to the given folder.
   *
   * @param folder The parent of the current folder.
   */
  public void setParent(Folder folder) {
    this.parent = folder;
  }

  /**
   * Returns the parent of the folder.
   *
   * @return The parent of the folder.
   */
  public Folder getParent() {
    return this.parent;
  }
  
  /**
   * Returns the File after the file this method is called on by in-order
   * traversal.
   * 
   * @return The File directly after the file this method is called on by
   * in-order traversal.
   */
  public File getNextFile() {
  File result;
  // If this is a non-empty folder, return the first file in folder
  if ((this.type == "FOLDER") && (!((Folder) this).isEmpty())) {
    result = ((Folder) this).getContents().get(0);
  }
  // Otherwise, go to parent if parent exists
  else {
	// Check that this is not root
    if (this.hasParent()) {
    // Set curr and parent of curr on this
    File curr = this;
    Folder parent = curr.getParent();
    // Get index of curr in its parent folder
    int currIndex = parent.getContents().indexOf(curr);
    // Get number of items in parent folder
    int parentContentSize = parent.getContents().size();
    // If curr is not last item in parent folder, return the item "after"
    // curr
    boolean notLastItem = currIndex < parentContentSize - 1;
    if (notLastItem) {
    // Get item of index one greater than curr
    result = parent.getContents().get(currIndex + 1);
      }
    // If curr is the last item in parent
    else {
      while (!notLastItem) {
        if (curr.hasParent()) {
          // Move curr and parent "up" a layer
          curr = parent;
          parent = curr.getParent();
          // Re-set currIndex and parentContentsize and guard
          currIndex = parent.getContents().indexOf(curr);
          parentContentSize = parent.getContents().size();
          notLastItem = currIndex < parentContentSize - 1;
            }
          // Throw exception if this is root
          else {
            throw new ArrayIndexOutOfBoundsException();
            }
          }
          // Once loop finishes, the parent will contain a new "branch"
          // Not solely containing curr, with another item "after" curr
          result = parent.getContents().get(currIndex + 1);
      }
    }
    // Throw exception if this is root (ie. this has no parent)
    else {
      throw new ArrayIndexOutOfBoundsException();
    }
  }
  return result;
  }
  
  public static void main(String[] args) {
    Folder root = new Folder("/");
    Folder music = new Folder("Music");
    Folder documents = new Folder("Documents");
    Folder work = new Folder("Work");
    Folder memes = new Folder("Memes");
    TextFile meme1 = new TextFile("Meme1");
    TextFile meme2 = new TextFile("Meme2");
    TextFile works = new TextFile("Works");
    TextFile song = new TextFile("song");

    root.addFile(documents);
    root.addFile(music);
    documents.addFile(work);
    work.addFile(memes);
    work.addFile(works);
    memes.addFile(meme1);
    memes.addFile(meme2);
    music.addFile(song);

    documents.setParent(root);
    music.setParent(root);
    work.setParent(documents);
    works.setParent(work);
    memes.setParent(work);
    meme1.setParent(memes);
    meme2.setParent(memes);
    song.setParent(music);

    /*
     *          Our directory looks like this:
     *
     *                   root
     *                   /  \
     *           Documents   Music
     *               /         \
     *           Work		    Song
     *             / \
     *         Memes  Works
     *         /    \
     *     Meme1    Meme2
     */

    //System.out.println(meme1.getNextFile().getName());
    //System.out.println(meme2.getNextFile().getName());
    //System.out.println(works.getNextFile().getName());
    //System.out.println(song.getNextFile().getName());
    try {
      File curr = root;
      while (true) {
        System.out.print(curr.getName());
        curr = curr.getNextFile();
      }
    }
    catch (NullPointerException e) {}
  }
}
