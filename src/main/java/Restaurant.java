import java.util.ArrayList;

public class Restaurant {
    private Object cook = new Object();
    private ArrayList<Waiter> waiters = new ArrayList<>();

    public void open(){
        waiters.add(new Waiter("waiter 1", this));
        waiters.add(new Waiter("waiter 2", this));
        waiters.add(new Waiter("waiter 3", this));

        new Thread(null, new Visitor(this, "visitor1"), "visitor1").start();
        new Thread(null, new Visitor(this, "visitor2"), "visitor2").start();
        new Thread(null, new Visitor(this, "visitor3"), "visitor3").start();
        new Thread(null, new Visitor(this, "visitor4"), "visitor4").start();
        new Thread(null, new Visitor(this, "visitor5"), "visitor5").start();
    }

    public synchronized Waiter getWaiter(){
        try {
            while (waiters.isEmpty()) {
                System.out.println("no free waiters");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return waiters.remove(0);
    }

    public void tellCook(){
        synchronized (cook){
            System.out.println("The Cook is making a dish");
            sleep();
            System.out.println("The Cook finished the dish");
            sleep();
        }
    }

    public synchronized void addWaiter(Waiter waiter) {
        waiters.add(waiter);
        notify();
    }

    private void sleep() {
        try {
            Thread.sleep(2600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
