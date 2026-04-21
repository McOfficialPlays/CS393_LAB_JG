package lab14;

public class SumUsingThread {
    public static void main(String[] args) {
        SumThread t = new SumThread();

        t.start();

        try {
            t.join(); // wait for thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum from 1 to 100 = " + t.getSum());
    }
}

class SumThread extends Thread {
    private int sum = 0;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }

    public int getSum() {
        return sum;
    }
}