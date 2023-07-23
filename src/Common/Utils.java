package Common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;

public final class Utils {
  public static boolean IsNumAlpha(String str) {
    return str != null && str.matches("^[a-zA-Z0-9]*$");
  }

  public static void writeFile(String name, String content) throws Exception {
    File f = new File("./src/" + name + ".txt");
    if (f.exists())
      f.delete();
    f.createNewFile();
    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
    bw.write(content);
    bw.close();
  }

  public static String[] readFile(String filePath) {
    try {
      if (new File(filePath).isFile())
        return Jsoup.parse(new File(filePath), "utf-8").body().text().split("\\s+");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

}
