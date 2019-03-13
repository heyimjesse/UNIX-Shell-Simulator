package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import classes.Command;
import classes.FileSystem;
import classes.TextFile;

/**
 * The Get class is responsible for getting a file from an URL and adding it
 * to the current working directory.
 */
public class Get extends Command {
  /**
   * Get the file at the URL and creates a file in our current working
   * directory with the same contents.
   * 
   * @param fs The file system we are working with.
   * @param pList The URL.
   * @return The displayed information on the terminal.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> pList) {
    // pList should only contain one URL
    if (pList.size() != 1) {
      System.out.println("Invalid URL");
      return "";
    }
    String urlString = pList.get(0);
    try {
      URL url = new URL(urlString);
      BufferedReader br = new BufferedReader(
          new InputStreamReader(url.openStream()));
      
      String newFileContents = "";
      String inputLine;
      /*
       * We read the file at the URL line by line and add it to a string. This
       * string will be the contents of the new text file we are adding.
       */
      while ((inputLine = br.readLine()) != null) {
        newFileContents = newFileContents + inputLine + "\n";
      }
      br.close();
      
      /*
       * We create a new file and add it to current working directory
       */
      int splitMarker = urlString.lastIndexOf("/");
      String fileName = urlString.substring(splitMarker + 1);
      
      TextFile newFile = new TextFile(fileName);
      newFile.overwrite(newFileContents);
      FileSystem.getFS().getCWD().addFile(newFile);
      newFile.setParent(FileSystem.getFS().getCWD());
      
    } catch (MalformedURLException e) {
      System.out.println("URL does not support text representation.");
    } catch (IOException e) {
      System.out.println("URL does not support text representation.");
    }
    // we return empty string since we don't display anything on console
    return "";
  }
  
  public static void main(String[] args) {
    List<String> l = new ArrayList<String>();
    l.add("https://www.utsc.utoronto.ca/~nick/cscB36/185/lecture.txt");
    Get get = new Get();
    get.runCommand(FileSystem.getFS(), l);
    System.out.println(
        ((TextFile) FileSystem.getFS().getCWD().getFile(
            "lecture.txt")).getText());
  }
}
