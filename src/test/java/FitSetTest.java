import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class FitSetTest {
    @Test
    public void testAdd() throws Exception {
        Product product1 = new Product("a", 200);
        Product product2 = new Product("a", 100);
        Product product3 = new Product("b", 300);
        Product product4 = new Product("a", 50);

        Set<Product> cheapestProducts = new FitSet<Product>();
        cheapestProducts.add(product1);
        cheapestProducts.add(product2);
        cheapestProducts.add(product3);
        cheapestProducts.add(product4);

        assertTrue("contents " + cheapestProducts, cheapestProducts.size() == 2);
        assertTrue("contents " + cheapestProducts, cheapestProducts.equals(new HashSet<Product>(Arrays.asList(product4, product3))));


    }

    @Test
    public void testAddAll() throws Exception {
        final Random random = new Random();

        Set<Product> cheapestProducts = new FitSet<Product>();
        for (int i = 0; i < 500000; i++) {
            cheapestProducts.add(new Product(String.valueOf(random.nextInt()), random.nextInt()));
        }
    }
}
