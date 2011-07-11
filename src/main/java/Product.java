import java.util.Arrays;
import java.util.Collections;

public class Product implements Comparable<Product>, Fit<Product>{
    private final String name;
    private final int price;
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        if (name != null ? !name.equals(product.name) : product.name != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int compareTo(Product product) {
        return Integer.valueOf(price).compareTo(product.price);
    }

    public Product getFittest(Product other) {
        return Collections.min(Arrays.asList(this, other));
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

