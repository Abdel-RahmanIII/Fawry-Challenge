package ecommerceSystem;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // ====== Setup Products ======
        Product cheese = new Product("Cheese", 100, 10);
        cheese.setExpirable(LocalDate.now().plusDays(3));
        cheese.setShippable(400); // grams

        Product biscuits = new Product("Biscuits", 150, 5);
        biscuits.setExpirable(LocalDate.now().plusDays(1));
        biscuits.setShippable(700);

        Product tv = new Product("Smart TV 4K 65inch SuperWideScreen", 1000, 2);
        tv.setShippable(10000); // 10 kg

        Product scratchCard = new Product("Mobile Scratch Card", 50, 100); // non-expirable, non-shippable

        Product expiredMilk = new Product("Milk", 80, 10);
        expiredMilk.setExpirable(LocalDate.now().minusDays(1)); // expired

        Product limitedStock = new Product("Limited Edition Phone", 2000, 1);

        Checkout checkout = new Checkout();

        // ====== Test Case 1: Successful Checkout ======
        System.out.println("=== Successful Checkout ===");
        try {
            Customer customer1 = new Customer(1000);
            customer1.addToCart(cheese, 2);
            customer1.addToCart(biscuits, 1);
            customer1.addToCart(scratchCard, 3);
            checkout.checkout(customer1);
            System.out.println("Remaining balance: " + customer1.getBalance() + "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        // ====== Test Case 2: Empty Cart ======
        System.out.println("=== Empty Cart ===");
        try {
            Customer customer2 = new Customer(500);
            checkout.checkout(customer2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        // ====== Test Case 3: Insufficient Balance ======
        System.out.println("=== Insufficient Balance ===");
        try {
            Customer customer3 = new Customer(50); // too low
            customer3.addToCart(tv, 1);
            checkout.checkout(customer3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        // ====== Test Case 4: Expired Product ======
        System.out.println("=== Expired Product ===");
        try {
            Customer customer4 = new Customer(500);
            customer4.addToCart(expiredMilk, 1);
            checkout.checkout(customer4);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        // ====== Test Case 5: Out of Stock ======
        System.out.println("=== Out of Stock ===");
        try {
            Customer customer5 = new Customer(5000);
            customer5.addToCart(limitedStock, 2); // only 1 in stock
            checkout.checkout(customer5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        // ====== Test Case 6: Long Name Truncation & No Shipping ======
        System.out.println("=== Long Name Truncation & Non-Shippable ===");
        try {
            Customer customer6 = new Customer(3000);
            customer6.addToCart(scratchCard, 1); // no shipping
            customer6.addToCart(tv, 1); // long name, shippable
            checkout.checkout(customer6);
            System.out.println("Remaining balance: " + customer6.getBalance() + "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }
}
