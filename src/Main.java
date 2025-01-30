import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static utils.InputUtils.*;

public class Main {
  static ArrayList<Product> productList = new ArrayList<>();
  static ArrayList<String> categoryList = new ArrayList<>();

  static {
    categoryList.add("Electronics");
    categoryList.add("Books");
    categoryList.add("Clothing");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while(true) {
      showMenu();
      int choice = getValidInteger(scanner, "Choose", 0, 4, true);

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
      }
    }
  }

  private static void showMenu() {
    System.out.println("\n=== Menu ===");
    System.out.println("1. Add Product");
    System.out.println("2. Remove Product");
    System.out.println("3. Add Category");
    System.out.println("4. Remove Category");
    System.out.println("0. Exit");
  }

  private static void addProduct(Scanner scanner) {
    String productName = getValidString(scanner, "Product name", true);
    float productPrice = getValidFloat(scanner, "Product price (Ex: 19.50)", false);

    if(categoryList.isEmpty()) {
      System.out.println("\n- Add a category to continue");
    } else {
      System.out.println("\n=== Categories ===");
      for(int i = 1; i <= categoryList.size(); i++) {
        System.out.printf("%s. %s%n", i, categoryList.get(i - 1));
      }

      int productCategory = getValidInteger(scanner, "Choose", 1, categoryList.size(), true);
      int productDiscount = getValidInteger(scanner, "Percentage Discount (0-99)", 0, 99, false);

      Product newProduct = new Product(productName, productPrice, categoryList.get(productCategory - 1), productDiscount);
      productList.add(newProduct);
      System.out.println("\nProduct added successfully!");
      System.out.println(newProduct.getDataProduct());
    }
  }

  private static void removeProduct(Scanner scanner) {
    if(productList.isEmpty()) {
      System.out.println("\n- There are no products");
    } else {
      System.out.println("\n=== Products ===");
      System.out.println("0. Back");
      for(int i = 1; i <= productList.size(); i++) {
        Product product = productList.get(i - 1);
        System.out.printf("%d. %s%n", i, product.getDataProduct());
      }

      int productChoose = getValidInteger(scanner, "Choose", 0, productList.size(), true);

      if(productChoose != 0) {
        Product product = productList.get(productChoose - 1);
        System.out.println("\nProduct removed successfully!");
        System.out.println(product.getDataProduct());

        productList.remove(product);
      }
    }
  }

  private static void addCategory(Scanner scanner) {
    while(true) {
      System.out.println("\n=== Categories ===");
      System.out.println("0. Back");
      for(int i = 1; i <= categoryList.size(); i++) {
        System.out.printf("- %s%n", categoryList.get(i - 1));
      }

      String productCategory = getValidString(scanner, "Category", true);

      if(Objects.equals(productCategory, "0")) {
        break;
      } else if(categoryList.contains(productCategory)) {
        System.out.println("\n- This category already exists");
      } else {
        categoryList.add(productCategory);
      }
    }
  }

  private static void removeCategory(Scanner scanner) {
    while(true) {
      System.out.println("\n=== Categories ===");
      System.out.println("0. Back");

      for (int i = 1; i <= categoryList.size(); i++) {
        System.out.printf("%d. %s%n", i, categoryList.get(i - 1));
      }

      int productCategory = getValidInteger(scanner, "Category", 0, categoryList.size(), true);

      if(productCategory == 0) {
        break;
      } else {
        removeProductsByCategory(categoryList.get(productCategory - 1), scanner);
      }
    }
  }

  private static void removeProductsByCategory(String category, Scanner scanner) {
    ArrayList<Product> productsCategories = new ArrayList<>();

    for (Product product : productList) {
      String productCategory = product.getCategory();

      if (productCategory.equals(category)) {
        productsCategories.add(product);
      }
    }

    if(!productsCategories.isEmpty()) {
      System.out.println("\nSome products have the category selected.");
      System.out.println("Are you sure you want to remove the category and the following products?");
      for(Product product : productsCategories) {
        System.out.printf("- %s\n", product.getName());
      }
      System.out.println("\n1. Yes");
      System.out.println("2. No");

      int confirmChoose = getValidInteger(scanner, "Choose", 1, 2, true);

      if(confirmChoose == 1) {
        for(Product product : productsCategories) {
          productList.remove(product);
        }
        categoryList.remove(category);

        System.out.println("\nProducts and category removed successfully!");
      }
    } else {
      System.out.println("\nCategory removed successfully!");
      categoryList.remove(category);
    }
  }
}