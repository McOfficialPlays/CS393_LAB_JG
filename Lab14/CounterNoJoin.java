package lab14;
//when not joining the count is incorrect but only by 389
public class CounterNoJoin {
    public static void main(String[] args) {
        Counter3 counter = new Counter3();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new IncrementTask3(counter));
            t.start();
        }

        // No join here
        System.out.println("Final counter = " + counter.getCount());
    }
}

class Counter3 {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class IncrementTask3 implements Runnable {
    private Counter3 counter;

    public IncrementTask3(Counter3 counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}