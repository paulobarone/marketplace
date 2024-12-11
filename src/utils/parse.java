package utils;

public class parse {
  public static int parseInt(String str) {
    int number = -1;

    try {
      number = Integer.parseInt(str.trim());
    } catch (NumberFormatException nfe) {
      // Do nothing
    }

    return number;
  }

  public static float parseFloat(String str) {
    float number = -1;

    try {
      number = Float.parseFloat(str.trim());
    }
    catch (NumberFormatException nfe) {
      // Do nothing
    }

    return number;
  }
}
