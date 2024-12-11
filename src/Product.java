import java.text.NumberFormat;
import java.util.Locale;

public class Product {
  private String name;
  private float price;
  private String category;
  private int discountPercentage;

  public Product(String name, float price, String category, int discountPercentage) {
    this.name = name;
    this.price = price;
    this.category = category;
    this.discountPercentage = discountPercentage;
  }

  public String getName() {
    return name;
  }

  public float getPrice() {
    return price;
  }

  public String getFormatedPrice() {
    return formatedPrice(price);
  }

  public String getCategory() {
    return category;
  }

  public int getDiscountPercentage() {
    return discountPercentage;
  }

  public float getPriceWithDiscount() {
    float discountAmount = price * ((float) discountPercentage / 100);
    return price - discountAmount;
  }

  public String getFormatedPriceWithDiscount() {
    return formatedPrice(getPriceWithDiscount());
  }

  public String formatedPrice(float price) {
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    return currencyFormat.format(price);
  }

  public String getDataProduct() {
    String productName = this.name;
    String productFormatedPrice = this.getFormatedPrice();
    String productFormatedPriceWithDiscount = this.getFormatedPriceWithDiscount();
    String productCategory = this.category;
    int productDiscountPercentage = this.discountPercentage;

    return String.format(
      "Name: %s | Price: %s | Discounted price: %s | Category: %s | Discount: %d%%",
      productName,
      productFormatedPrice,
      productFormatedPriceWithDiscount,
      productCategory,
      productDiscountPercentage
    );
  }
}
