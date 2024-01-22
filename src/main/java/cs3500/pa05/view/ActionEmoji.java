package cs3500.pa05.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Emoji
 */
public enum ActionEmoji {
  RESTING(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xB4}),
  EXERCISE(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x9A, (byte) 0xB4}),
  BIRTHDAY(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0x82}),
  ART(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0xA8}),
  PARTY(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0x89}),
  WORKING(new byte[] {(byte) 0xF0, (byte) 0x9F, (byte) 0x92, (byte) 0xBB});


  public final byte[] emojiByte;

  ActionEmoji(byte[] emojiByte) {
    this.emojiByte = emojiByte;
  }

  public String convertEmoji() {
    return new String(this.emojiByte, Charset.forName("UTF-8"));
  }

  /**
   * gets all of the emojis in the emoji as a string
   *
   * @return List of string emojis
   */
  public static List<String> getAllEmojis() {
    List<String> actionEmoji = new ArrayList<>();
    for (ActionEmoji a : ActionEmoji.values()) {
      actionEmoji.add(a.convertEmoji());
    }
    return actionEmoji;
  }
}
