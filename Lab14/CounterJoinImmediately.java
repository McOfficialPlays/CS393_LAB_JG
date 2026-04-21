package lab14;
public class CounterJoinImmediately {
    public static void main(String[] args) {
        Counter2 counter = new Counter2();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new IncrementTask2(counter));
            t.start();

            try {
                t.join(); // join immediately after start
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter = " + counter.getCount());
    }
}

class Counter2 {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class IncrementTask2 implements Runnable {
    private Counter2 counter;

    public IncrementTask2(Counter2 counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}