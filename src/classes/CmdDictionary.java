package classes;

import commands.CP;
import java.util.Map;
import java.util.TreeMap;
import commands.CD;
// import commands.CP;
import commands.Cat;
import commands.Echo;
import commands.Find;
import commands.Get;
import commands.History;
import commands.LS;
import commands.MKDir;
import commands.MV;
import commands.Man;
import commands.PWD;
import commands.PopD;
import commands.PushD;
import commands.Tree;

/**
 * The CmdDictionary class will allow commands to be accessed from string
 * representation of themselves. For example it will provide access to the
 * PWD command from the string "pwd".
 */
public class CmdDictionary {
  /**
   * cmdMap A TreeMap used to store commands and link them to strings.
   */
  Map<String, Command> cmdMap = new TreeMap<String, Command>();
  
  /**
   * Constructor in which we add all command objects to the TreeMap.
   */
  public CmdDictionary() {
    /*
     * Put new commands inside of this constructor as we add them. Right
     * now we are missing Echo and Cat because those need to be changed.
     */
    cmdMap.put("cp", new CP());
    cmdMap.put("find", new Find());
    cmdMap.put("cd", new CD());
    cmdMap.put("echo", new Echo());
    cmdMap.put("cat", new Cat());
    cmdMap.put("history", new History());
    cmdMap.put("ls", new LS());
    cmdMap.put("man", new Man());
    cmdMap.put("mkdir", new MKDir());
    cmdMap.put("mv", new MV());
    cmdMap.put("popd", new PopD());
    cmdMap.put("pushd", new PushD());
    cmdMap.put("pwd", new PWD());
    cmdMap.put("get", new Get());
    cmdMap.put("tree", new Tree());
  }
  
  /**
   * Returns the command corresponding to the String. Since usage of this
   * class will be monitored we can assume only appropriate strings are
   * passed as arguments.
   * 
   * @param cmdString String representation of a command.
   * @return The corresponding command.
   */
  public Command getCommand(String cmdString) {
    return cmdMap.get(cmdString);
  }
}

