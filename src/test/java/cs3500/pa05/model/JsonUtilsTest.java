package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {
  Bujo bujo = new Bujo("A Bujo", List.of(new Event("Event1", "text",
      DayOfWeek.MONDAY, 4, 120)),
      List.of(new Task("task1", "more text", false, DayOfWeek.FRIDAY)),
      2, 3, "asdfadsfa fasasdfadsf");
  File file = new File("jsonUtilsTest.bujo");

  @Test
  void testWriteRecord() {

    JsonUtils.writeRecord(bujo, file);
    assertTrue(file.exists());
    assertTrue(file.isFile());
    Bujo bujo2 = JsonUtils.readBujo(file);
    assertEquals(bujo, bujo2);
  }

  //  @Test
  //  void testExceptions() {
  //    assertThrows(RuntimeException.class, () -> JsonUtils.readBujo(new File("nonfile")));
  //    File brokenFile = new File("nonfile");
  //    brokenFile.setWritable(false);
  //
  //    assertThrows(RuntimeException.class,
  //        () -> JsonUtils.writeRecord(" ", brokenFile));
  //    try (MockedStatic<JsonUtils> brokenWriter = mockStatic(JsonUtils.class)) {
  //      File file1 = new File("nonfile");
  //
  //      brokenWriter.when(() ->JsonUtils.writeRecord(" ", file1))
  //              .thenThrow(new IllegalArgumentException("Given record cannot be serialized"));
  //      assertThrows(IllegalArgumentException.class,
  //          () -> JsonUtils.writeRecord(" ", file1));
  //    }
  //  }



}