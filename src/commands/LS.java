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
package commands;

import java.util.List;
import java.util.ArrayList;

import classes.Command;
import classes.File;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;
import classes.TextFile;


/**
 * The LS class is responsible for executing the ls command, which lists all
 * the files in the current working directory.
 */
public class LS extends Command {

  /**
   * If no paths are given, prints the contents of the current folder.
   * Otherwise, for each path p, - if p specifies a text file, print p - if p
   * specifies a folder, print the contents of that folder - if path doesn't
   * exist, prints an error message
   * If "-R", then returns the contents under specified paths recursively,
   *  - or all contents recursively if no path specified.
   *
   * @param fs The file system we are working with
   * @param pathList An empty list or list of paths
   * @return The string representation of the insides of the folder.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> pathList) {
    String retString = "";
    // if the list is empty we just return contents of cwd
    if (pathList.size() == 0) {
      retString = fs.getCWD().getContentsString();
    } 
    // If recursion specified with no paths given
    else if ((pathList.size() == 1) && (pathList.get(0) == "-R")) {
      retString = recursiveLSHelper(fs, fs.getCWD()) + "\n";
    }
    // If given a list of paths and recursion not specified
    else if (pathList.get(0) != "-R") {
      for (String path : pathList) {
        // if path does not exist we print an error
        if (!PathChecker.checkPath(fs, path)) {
          System.out.println(path + " does not exist");
          continue;
        }
        File dFile = Navigate.navigateTo(fs, path);
        if (dFile.getType().equals("TEXTFILE")) {
          retString = retString + dFile.getName() + "\n\n";
        } else if (dFile.getType().equals("FOLDER")) {
          retString = retString + ((Folder) dFile).getContentsString() + "\n";
        }
      }
    }
    // If recursion specified
    else {
      // Loop through every path
      for (String path : pathList.subList(1, pathList.size())) {
        retString = retString + 
            recursiveLSHelper(fs, Navigate.navigateTo(fs, path));
      }
    }
    if (retString.length() > 0) {
      retString = retString.substring(0, retString.length() - 1);
    }
    return retString.trim();
  }
  
  /**
   * Returns the LS string of file subtree rooted at file.
   * 
   * @param fs The FileSystem to be worked in
   * @param file The file the subtree is rooted at
   * @return String representation of every file rooted at file
   */
  private static String recursiveLSHelper(FileSystem fs, File file) {
    String result = "";
    // If file is text or empty folder, result is just name of file
    if (file.type.equals("TEXTFILE")) {
      result = file.getName();
    }
    else if (((Folder) file).isEmpty()) {
      result = file.getName();
    }
    // If file is non-empty folder
    else {
      // Traverse to next file after file
      try {
        File curr = file.getNextFile();
        // While curr is under subtree rooted at file
        while (((Folder) file).hasFileRecursive(curr.getName())) {
          if (curr.type == "TEXTFILE") {
            result = result + curr.getName() + "\n\n";
          } 
          else {
            result = result + curr.getName() + "\n";
          }
          // Go to next file
          curr = curr.getNextFile();
        }
      }
      // Catch exception at end of file traversal
      catch (NullPointerException e) {}
    }
    return result;
  }
  
  public static void main(String[] args) {
  
  	Folder music = new Folder("Music");
  	Folder documents = new Folder("Documents");
  	Folder work = new Folder("Work");
  	Folder memes = new Folder("Memes");
  	TextFile meme1 = new TextFile("Meme1");
  	TextFile meme2 = new TextFile("Meme2");
  	TextFile works = new TextFile("Works");
  	TextFile song = new TextFile("song");
  	
  	FileSystem fs = FileSystem.getFS();
  	Folder root = fs.getRoot();
    fs.setCWD(root);
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
  	LS ls = new LS();
  	//System.out.println(LS.recursiveLSHelper(fs, root));
  	List<String> arg = new ArrayList<String>();
  	//System.out.println(ls.runCommand(fs, arg));
  	arg.add("-R");
  	System.out.println(ls.runCommand(fs, arg));
  }
}
