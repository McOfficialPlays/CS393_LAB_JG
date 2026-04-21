package lab14;
//i noticed it is not working correclty when not syncronized
public class CounterNotSynchronized {
    public static void main(String[] args) {
        Counter4 counter = new Counter4();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new IncrementTask4(counter));
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter = " + counter.getCount());
    }
}

class Counter4 {
    private int count = 0;

    // not synchronized
    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class IncrementTask4 implements Runnable {
    private Counter4 counter;

    public IncrementTask4(Counter4 counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}