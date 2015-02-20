package domek.philies;

public class Test {

	public static void main(String[] args) {
		Fork f1 = new Fork();
		Fork f2 = new Fork();
		Fork f3 = new Fork();
		Fork f4 = new Fork();
		Fork f5 = new Fork();
		
		Thread t1 = new Thread(new Philosopher(String.valueOf(1), f1, f2));
		Thread t2 = new Thread(new Philosopher(String.valueOf(2), f2, f3));
		Thread t3 = new Thread(new Philosopher(String.valueOf(3), f3, f4));
		Thread t4 = new Thread(new Philosopher(String.valueOf(4), f4, f5));
		Thread t5 = new Thread(new Philosopher(String.valueOf(5), f5, f1));
				
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}
