package classes;

import java.util.ArrayList;
import java.util.List;

public class PathParser {

  /**
   * Separates a path from its path and name
   * @param path A string path
   * @return get(0) = directory, get(1) = filename
   */
  public static List<String> separateFileFromPath(String path) {
    int pathLen = path.length();
    // Remove all trailing forward slashes
    while (pathLen > 1 && path.substring(pathLen-2, pathLen).equals("//")) {
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
          if (i>1) {
            dest_path_folder = path.substring(0, i);
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

  public static void main(String[] args){
    List<String> paths0 = separateFileFromPath("a/b");
    System.out.println(paths0.get(0));
    System.out.println(paths0.get(1));
  }

}
