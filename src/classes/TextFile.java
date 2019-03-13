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
 * The TextFile subclass represents a text file in some directory. It contains
 * text which may be appended onto or overwritten.
 */
public class TextFile extends File {
  /*
   * The string variable "text" will represent our text file. We can append to
   * it and use newline characters to denote the end of each line.
   */
  /**
   * text The text inside the text file.
   */
  private String text;

  /**
   * Constructor
   *
   * @param name The name of the file.
   */
  public TextFile(String name) {
    super(name);
    this.type = "TEXTFILE";
    this.text = "";
  }

  /**
   * Overwrites text with newText
   *
   * @param newText Text to override
   */
  public void overwrite(String newText) {
    this.text = "\n"+newText;
  }

  /**
   * Appends newText to Text
   *
   * @return Contents of the TextFile
   */
  public String getText() {
    return this.text;
  }

  /**
   * Add a line to the text file.
   *
   * @param newText Contents to append.
   */
  public void appendLine(String newText) {
    this.text += newText;
  }

  /**
   * Prints the text
   */
  public void printText() {
    Printer.print(this.text);
  }
}
