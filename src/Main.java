import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  static ArrayList<Product> productList = new ArrayList<>();
  static ArrayList<String> categoryList = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while(true) {
      showMenu();
      byte choice = scanner.nextByte();

      switch(choice) {
        case 1:
          addProduct(scanner);
          break;
        case 2:
          removeProduct(scanner);
          break;
        case 3:
          addCategory(scanner);
          break;
        case 4:
          removeCategory(scanner);
          break;
        case 0:
          System.out.println("Leaving the program...");
          scanner.close();
          System.exit(0);
        default:
          System.out.println("\nInvalid option. Please try again.");
      }
    }
  }

  private static void showMenu() {
    System.out.println("\n=== Menu ===");
    System.out.println("1. Add Product");
    System.out.println("2. Remove Product");
    System.out.println("3. Add Categories");
    System.out.println("0. Exit");
    System.out.print("\n> Choose: ");
  }

  private static void addProduct(Scanner scanner) {
    scanner.nextLine();

    System.out.print("\n> Product name: ");
    String productName = scanner.nextLine();

    System.out.print("> Product price (Ex: 19,99): ");
    float productPrice = scanner.nextFloat();

    if(categoryList.isEmpty()) {
      System.out.println("\n- Add a category to continue");
      showMenu();
    }

    System.out.println("\n=== Categories ===");
    System.out.println("0. Add another Category");
    for(int i = 1; i <= categoryList.size(); i++) {
      System.out.printf("%s. %s%n", i, categoryList.get(i - 1));
    }
    System.out.print("\n> Choose: ");
    int productCategory = scanner.nextInt();

    if(productCategory == 0) {
      addCategory(scanner);
    }

    System.out.print("\n> Discount (0-100): ");
    int productDiscount = scanner.nextInt();

    Product newProduct = new Product(productName, productPrice, categoryList.get(productCategory - 1), productDiscount);
    productList.add(newProduct);
    System.out.println("\nProduct added successfully!");
    System.out.printf(
      "Name: %s | Price: %s | Price with Discount: %s | Category: %s | Discount: %d%%\n",
      newProduct.getName(),
      newProduct.getFormatedPrice(),
      newProduct.getFormatedPriceWithDiscount(),
      newProduct.getCategory(),
      newProduct.getDiscountPercentage()
    );
  }

  private static void removeProduct(Scanner scanner) {
    scanner.nextLine();

    if(productList.isEmpty()) {
      System.out.println("\n- There are no products");
    } else {
      System.out.println("\n=== Products ===");
      System.out.println("0. Back");
      for(int i = 1; i <= productList.size(); i++) {
        Product product = productList.get(i - 1);
        System.out.printf(
          "%s. Name: %s | Price: %s | Price with Discount: %s | Category: %s | Discount: %d%%\n",
          i,
          product.getName(),
          product.getFormatedPrice(),
          product.getFormatedPriceWithDiscount(),
          product.getCategory(),
          product.getDiscountPercentage()
        );
      }

      System.out.print("\n> Choose: ");
      int productChoose = scanner.nextInt();

      if(productChoose != 0) {
        Product product = productList.get(productChoose - 1);
        System.out.println("\nProduct removed successfully!");
        System.out.printf(
          "Name: %s | Price: %s | Price with Discount: %s | Category: %s | Discount: %d%%\n",
          product.getName(),
          product.getFormatedPrice(),
          product.getPriceWithDiscount(),
          product.getCategory(),
          product.getDiscountPercentage()
        );

        productList.remove(productChoose - 1);
      }
    }
  }

  private static void addCategory(Scanner scanner) {
    scanner.nextLine();

    String productCategory;
    while(true) {
      System.out.println("\n=== Categories ===");
      System.out.println("0. Back");
      for(int i = 1; i <= categoryList.size(); i++) {
        System.out.printf("- %s%n", categoryList.get(i - 1));
      }
      System.out.print("\n> Category: ");
      productCategory = scanner.nextLine();

      if(Objects.equals(productCategory, "0")) {
        break;
      } else {
        categoryList.add(productCategory);
      }
    }
  }

  private static void removeCategory(Scanner scanner) {
    scanner.nextLine();

    System.out.println("\n=== Categories ===");
  }
}