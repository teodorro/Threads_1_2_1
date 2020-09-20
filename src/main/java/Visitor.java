public class Visitor implements Runnable {
    private String name;
    private Restaurant restaurant;

    public Visitor(Restaurant restaurant, String name) {
        this.restaurant = restaurant;
        this.name = name;
    }

    @Override
    public void run() {
        restaurant.enter();
        Waiter waiter = restaurant.getWaiter();

        Thread thread = new Thread(waiter);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restaurant.addWaiter(waiter);
        System.out.println(name + " started to eat");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " went home");
    }
}