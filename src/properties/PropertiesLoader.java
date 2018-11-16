package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

  String fileName = "properties/application.properties";

  public String getValue(String key)
  {
    Properties prop = new Properties();
    InputStream input = null;

    try {
      input = new FileInputStream(fileName);
      prop.load(input);
      return prop.getProperty(key);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("The property File Doesnot exist");
    }
    finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
