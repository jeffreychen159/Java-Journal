package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for JSON parsing
 */
public class JsonUtils {

  /**
   * converts a JSON record into a JSON node
   *
   * @param record A JSON record
   * @param file file to write to
   */
  public static void writeRecord(Object record, File file) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(file, record);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * reads a bujo object from a file
   *
   * @param file the file to read from
   * @return a bujo object
   */
  public static Bujo readBujo(File file) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(file, Bujo.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
