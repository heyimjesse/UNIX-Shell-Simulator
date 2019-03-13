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

import commands.Echo;
import java.util.ArrayList;
import java.util.List;

/**
 * The Printer class is responsible for printing outputs. This class may be
 * changed in the future to satisfy different printing requirements. ie.
 * printing to a file, printing to a website, etc.
 */
public class Printer {

  private static String capturedOutput = "";

  /**
   * Prompts user of an error
   * @param output Text to show user
   */
  public static void writeError(String output) {
    System.out.println(output);
  }

  /**
   * Print takes any string and prints it to the console using the java
   * standard output.
   *
   * @param output String text to output
   */
  public static void print(String output) {
    capturedOutput += output;
  }

  /**
   * Print or output console contents to a file
   * @param args A list of arguments
   */
  public static void endCapture(List<String> args) {
    FileSystem fs = FileSystem.getFS(); // get filesystem
    // Obtain the index of ">>" or ">" which indicates console output
    int commandIndex = args.indexOf(">>");
    // Check ">" argument
    if (commandIndex == -1) {
      commandIndex = args.indexOf(">");
    }
    if (capturedOutput.equals("") && commandIndex == -1) {
      return; // there is nothing in the buffer to write/print
    } else if (capturedOutput.equals("") && commandIndex != 1) {
      Printer.writeError("Command did not return any input. File was "
          + "not created.");
      return;
    }
    // Check if the argument was found.
    if (commandIndex == -1) {
      // Argument not found, print to console
      Printer.writeError(capturedOutput);
    } else if (commandIndex == args.size()-1) {
      // Argument does not include a path to output file to
      Printer.writeError(capturedOutput +
          "OUTFILE: Invalid path parameters");
    } else {
      String commandType = args.get(commandIndex); // output parameter
      String outPath = args.get(commandIndex+1); // output path
      // Split outPath into path and file name
      List<String> splitPath = separateFileFromPath(outPath);
      String path = splitPath.get(0);
      // Check if path is valid
      if (!PathChecker.checkPath(fs, path)) {
        // Path is invalid
        Printer.writeError(capturedOutput + "\nOUTFILE: Output "
            + "path invalid");
        resetOutput();
        return;
      }
      // Path is valid, write the file
      writeFile(outPath, commandType, capturedOutput);
    }
    resetOutput(); // Reset output
  }

  /**
   * Creates a new file at path with data given the command type
   * @param path A path
   * @param cmd > or >>
   * @param data The string contents of the file intended to be written
   */
  private static void writeFile(String path, String cmd, String data) {
    // Path is valid. Create an args and rawInput to send to echo.
    ArrayList<String> fileParameters = new ArrayList<String>();
    // > or >>
    fileParameters.add(cmd);
    // Add the path to the arguments
    fileParameters.add(path);
    // "rawInput"
    String contents = "\"" + data + "\"";
    // Run the command to create file
    new Echo().runCommand(FileSystem.getFS(), fileParameters, contents);
  }

  /**
   * Resets the captured output
   */
  private static void resetOutput(){
    capturedOutput = "";
  }

  /**
   * Separates a path from its path and name
   * @param path A string path
   * @return get(0) = directory, get(1) = filename
   */
  private static List<String> separateFileFromPath(String path) {
    int pathLen = path.length();
    // Remove all trailing forward slashes
    while (pathLen > 1 && path.substring(pathLen-1, pathLen).equals("/")) {
      path = path.substring(0, pathLen-1);
      pathLen -=1;
    }
    // If the path contains /, look for end path name
    if (path.contains("/")) {
      String dest_name = "";
      String dest_path_folder = "/"; //defaults to root
      // Iterate through each character from the end
      for (int i=path.length(); i>0; i--) {
        // Take a single character
        String character = path.substring(i-1, i);
        if (character.equals("/")){
          dest_name = path.substring(i, path.length());
          if (i>2) {
            dest_path_folder = path.substring(0, i-1);
          }
          break;
        }
      }

      ArrayList<String> ret = new ArrayList<String>();
      ret.add(dest_path_folder);
      ret.add(dest_name);
      return ret;
    }
    // The path has just the file name
    ArrayList<String> ret = new ArrayList<String>();
    ret.add("");
    ret.add(path);
    return ret;
  }

}
