import java.util.ArrayList;
import java.util.HashSet;

public class Restaurant extends Sleepable {
    private Object cook = new Object();
    private ArrayList<Thread> visitors = new ArrayList<>();
    private HashSet<String> ordersFinished = new HashSet<>();

    public void open() {
        addCook();
        addWaiter("waiter 1");
        addWaiter("waiter 2");
        addWaiter("waiter 3");

        addVisitor("visitor1");
        addVisitor("visitor2");
        addVisitor("visitor3");
        addVisitor("visitor4");
        addVisitor("visitor5");
    }

    private void addCook() {
        System.out.println("The Cook is at work");
    }

    private void addWaiter(String waiterName) {
        new Thread(null, new Waiter(this, waiterName), waiterName).start();
        System.out.println(waiterName + " is at work");
    }

    private synchronized void addVisitor(String visitorName) {
        Thread visitor = new Thread(null, new Visitor(this, visitorName), visitorName);
        visitors.add(visitor);
        visitor.start();
        System.out.println(visitorName + " entered the restaurant");
        notify();
    }

    public synchronized Thread getVisitor() {
        try {
            while (visitors.size() == 0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return visitors.remove(0);

    }

    public synchronized boolean getOrder(String visitorName) {
        try {
            while (!ordersFinished.contains(visitorName)) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ordersFinished.remove(visitorName);
    }

    public synchronized void addOrder(String visitorName) {
        ordersFinished.add(visitorName);
        notifyAll();
    }

    public void tellCook() {
        synchronized (cook) {
            System.out.println("The Cook is making a dish");
            sleep();
            System.out.println("The Cook finished the dish");
            sleep();
        }
    }

}
