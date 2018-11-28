package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to load the properties from the configuration file and uses them in the
 * application.
 */
public class PropertiesLoader {

  /**
   * The configuration file name.
   */
  String fileName;


  /**
   * Instantiates a new Properties loader with a file name.
   */
  public PropertiesLoader() {
    this.fileName = "properties/application.properties";
  }

  /**
   * Gets value against a key from configuration file.
   *
   * @param key the key
   * @return the value
   */
  public String getValue(String key) {
    Properties prop = new Properties();
    InputStream input = null;

    try {
      input = new FileInputStream(fileName);
      prop.load(input);
      return prop.getProperty(key);
    } catch (Exception e) {
      throw new IllegalArgumentException("The property File Doesnot exist");
    } finally {
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
