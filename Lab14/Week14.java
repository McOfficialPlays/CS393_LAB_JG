package lab14;

public class Week14{

    public static void main(String[] args) {
	SumTask task = new SumTask();
	Thread t = new Thread(task);
	t.start();
	try {
	    t.join();
	}catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println("Sum from 1 to 100 = "+ task.getSum());
    }

}
class SumTask implements Runnable{
    private int sum = 0;
    @Override
    public void run() {
	for(int i =1; i <=100; i++) {
	    sum += i;
	}
    }
    public int getSum() {
	return sum;
    }
}

