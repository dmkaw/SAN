package domek.philies;

import java.util.Random;

public class Philosopher implements Runnable {

	Random rnd = new Random();
	final String name;
	boolean l;
	boolean r;
	final Fork left;
	final Fork right;

	public Philosopher(String name, Fork left, Fork right) {
		this.name = name;
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		for (;;) {
			try {
				Thread.sleep(rnd.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			l = left.used.tryAcquire();
			r = right.used.tryAcquire();
			if (l && r) {
				System.out.println("Philosopher " + name + " is eating.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Philosopher " + name + " is finishing.");
			}
			if (l) {
				left.used.release();
				l = false;
			}
			if (r) {
				right.used.release();
				r = false;
			}
		}
	}
}
