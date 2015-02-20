package domek.philies;

import java.util.concurrent.Semaphore;

public class Fork {
	Semaphore used = new Semaphore(1, true);
}
