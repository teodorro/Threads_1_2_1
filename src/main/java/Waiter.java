public class Waiter implements Runnable {
    private String name;
    private Restaurant restaurant;

    public Waiter(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        System.out.println(name + " got an order");
        sleep();
        restaurant.tellCook();
        System.out.println(name + " is bringing the order");
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
