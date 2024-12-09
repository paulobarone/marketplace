import java.util.ArrayList;
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
//        case 3:
//          addDiscount(scanner);
//          break;
        case 4:
          addCategory(scanner);
          break;
//        case 5:
//          removeCategory(scanner);
//          break;
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
    System.out.println("3. Add Discount");
    System.out.println("4. Add Category");
    System.out.println("5. Remove Category");
    System.out.println("0. Exit");
    System.out.print("> Choose: ");
  }

  private static void addProduct(Scanner scanner) {
    scanner.nextLine();

    System.out.print("\n> Product name: ");
    String productName = scanner.nextLine();

    System.out.print("> Product price (Ex: 19,99): ");
    float productPrice = scanner.nextFloat();

    if(categoryList.isEmpty()) {
      System.out.println("\n- Add a category to continue");
      addCategory(scanner);
    }

    int productCategory;
    while(true) {
      System.out.println("\n=== Categories ===");
      System.out.println("0. Add other category");
      for(int i = 1; i <= categoryList.size(); i++) {
        System.out.printf("%s. %s%n", i, categoryList.get(i - 1));
      }
      System.out.print("\n> Choose: ");
      productCategory = scanner.nextInt();

      if(productCategory == 0) {
        addCategory(scanner);
      } else {
        break;
      }
    }

    Product newProduct = new Product(productName, productPrice, categoryList.get(productCategory - 1));
    productList.add(newProduct);
    System.out.println("\nProduct added successfully!");
    System.out.printf("Name: %s | Price: %s | Category: %s\n", newProduct.getProductName(), newProduct.getProductPrice(), newProduct.getProductCategory());
  }

  private static void removeProduct(Scanner scanner) {
    scanner.nextLine();

    if(productList.isEmpty()) {
      System.out.println("\n- There are no products");
    } else {
      System.out.println("\n=== Products ===");
      System.out.println("0. Back");
      for(int i = 1; i <= productList.size(); i++) {
        System.out.printf("%s. Name: %s | Price: %s | Category: %s%n", i, productList.get(i - 1).getProductName(), productList.get(i - 1).getProductPrice(), productList.get(i - 1).getProductCategory());
      }
      System.out.print("> Choose: ");
      int productChoose = scanner.nextInt();

      if(productChoose != 0) {
        System.out.println("\nProduct removed successfully!");
        System.out.printf("Name: %s | Price: %s | Category: %s\n", productList.get(productChoose - 1).getProductName(), productList.get(productChoose - 1).getProductPrice(), productList.get(productChoose - 1).getProductCategory());
        productList.remove(productChoose - 1);
      }
    }
  }

  private static void addCategory(Scanner scanner) {
    scanner.nextLine();

    System.out.print("\n> Category name: ");
    String categoryName = scanner.nextLine();

    if(categoryName.isBlank()) {
      showMenu();
    } else {
      categoryList.add(categoryName);
    }
  }
}