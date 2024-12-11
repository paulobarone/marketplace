package utils;

import java.util.Scanner;

import static utils.parse.parseFloat;
import static utils.parse.parseInt;

public class InputUtils {
  public static int getValidInteger(Scanner scanner, String inputLocale, int min, int max, boolean skipLine) {
    int input = -1;

    while (true) {
      if (skipLine) System.out.print("\n");
      System.out.printf("> %s: ", inputLocale);
      String value = scanner.nextLine();

      if(!value.isEmpty()) {
        int parseToInt = parseInt(value);

        if(parseToInt >= min && parseToInt <= max) {
          input = parseToInt;
          break;
        } else {
          System.out.println("\n- Invalid input. Please try again.");
          if (!skipLine) System.out.print("\n");
        }
      } else {
        System.out.println("\n- Invalid input. Please try again.");
        if (!skipLine) System.out.print("\n");
      }
    }

    return input;
  }

  public static float getValidFloat(Scanner scanner, String inputLocale, boolean skipLine) {
    float input = -1;

    while (true) {
      if (skipLine) System.out.print("\n");
      System.out.printf("> %s: ", inputLocale);
      String value = scanner.nextLine();

      if(!value.isEmpty()) {
        float parseToFloat = parseFloat(value);

        if(parseToFloat > 0) {
          input = parseToFloat;
          break;
        } else {
          System.out.println("\n- Invalid input. Please try again.");
          if (!skipLine) System.out.print("\n");
        }
      } else {
        System.out.println("\n- Invalid input. Please try again.");
        if (!skipLine) System.out.print("\n");
      }
    }

    return input;
  }

  public static String getValidString(Scanner scanner, String inputLocale, boolean skipLine) {
    String input = "";

    while(true) {
      if (skipLine) System.out.print("\n");
      System.out.printf("> %s: ", inputLocale);
      input = scanner.nextLine();

      if (!input.isEmpty()) {
        break;
      } else {
        System.out.println("\n- Invalid input. Please try again.");
        if (!skipLine) System.out.print("\n");
      }
    }

    return input;
  }
}
