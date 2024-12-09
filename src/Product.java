public class Product {
  private String name;
  private float price;
  private String category;

  public Product(String name, float price, String category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }

  public String getProductName() {
    return name;
  }

  public float getProductPrice() {
    return price;
  }

  public String getProductCategory() {
    return category;
  }
}
