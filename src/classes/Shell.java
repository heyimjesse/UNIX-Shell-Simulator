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

import commands.Cat;
import commands.History;
import commands.Man;
import commands.PopD;
import commands.PushD;
import java.util.List;
import commands.CD;
import commands.Cat;
import commands.LS;
import commands.MKDir;
import commands.PWD;
import commands.Echo;

/**
 * The Shell class gives the user access to commands. Depending on the user
 * input, the appropriate command is performed on the file system.
 */
public class Shell {

  /**
   * fileSystem A FileSystem object. scanner CustomScanner used to receive user
   * input.
   */
  private FileSystem fileSystem;
  private CustomScanner scanner = new CustomScanner();

  /**
   * Constructor
   *
   * @param fs The file system which the shell will operate on.
   */
  public Shell(FileSystem fs) {
    this.fileSystem = fs;
  }

  /**
   * The shell runs in an endless loop, taking in the users input and
   * performing appropriate commands if the input is valid. The shell stops
   * once the exit command is given.
   */
  public void start() {
    
    CmdDictionary cmdDict = new CmdDictionary();
    
    loop:
    while (true) {

      Object[] input = this.scanner.nextCommand();
      // Grab the command
      String cmd = (String) input[0];
      // Grab the list of arguments with "OUTFILE" specification removed
      List<String> pList = (List<String>)input[1];
      // Grab the list of original arguments
      List<String> oList = (List<String>)input[3];
      // Add to history
      History.add((String) input[2]);
      // Execute the appropriate command according to users input
      switch (cmd) {
        case "find":
          Printer.print(
              cmdDict.getCommand("find").runCommand(fileSystem,
                  pList));
          break;
      	case "cp":
      	  Printer.print(
      	      cmdDict.getCommand("cp").runCommand(fileSystem,
                  pList));
      	  break;
        case "mv":
          Printer.print(
              cmdDict.getCommand("mv").runCommand(fileSystem,
                  pList));
          break;
        case "history":
          Printer.print(
              cmdDict.getCommand("history").runCommand(fileSystem,
                  pList));
          break;
        case "cat":
          Printer.print(
              cmdDict.getCommand("cat").runCommand(fileSystem,
                  pList));
          break;
        case "echo":
          Printer.print(
              cmdDict.getCommand("echo").runCommand(
                  fileSystem, oList, (String)input[2]));
          break;
        case "man":
          Printer.print(
              cmdDict.getCommand("man").runCommand(fileSystem,
                  pList));
          break;
        case "pushd":
          Printer.print(
              cmdDict.getCommand("pushd").runCommand(fileSystem,
                  pList));
          break;
        case "popd":
          Printer.print(
              cmdDict.getCommand("popd").runCommand(fileSystem,
                  pList));
          break;
        case "mkdir":
          Printer.print(
              cmdDict.getCommand("mkdir").runCommand(fileSystem,
                  pList));
          break;
        case "cd":
          Printer.print(
              cmdDict.getCommand("cd").runCommand(fileSystem,
                  pList));
          break;
        case "ls":
          Printer.print(
              cmdDict.getCommand("ls").runCommand(fileSystem,
                  pList));;
          break;
        case "pwd":
          Printer.print(
              cmdDict.getCommand("pwd").runCommand(fileSystem,
                  pList));
          break;
        case "get":
          Printer.print(
              cmdDict.getCommand("get").runCommand(fileSystem,
                  pList));
          break;
        case "tree":
          Printer.print(
              cmdDict.getCommand("tree").runCommand(fileSystem,
                  pList));
          break;
        case "save":
          Printer.print(
              cmdDict.getCommand("save").runCommand(fileSystem,
                  pList));
          break;
        case "load":
          Printer.print(
              cmdDict.getCommand("load").runCommand(fileSystem,
                  pList));
          break;
        case "":
          break;
        case "exit":
          break loop;
        default:
          System.out.println("Invalid command.");
          break;
      }
      // Printer -> Either print output to console or to file
      Printer.endCapture(oList);
    }
  }
}

