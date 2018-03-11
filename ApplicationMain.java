import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class ApplicationMain {
	private int N = 5;
	private ArrayList<Thread> threadsList = new ArrayList<Thread>();
	private Stack stack = new Stack();
	private Semaphore fullSemapfore = new Semaphore(N);
	private Semaphore freeSemapfore = new Semaphore(0);
	
	

	public static void main(String[] args) {
		new ApplicationMain().start();

	}

	private void start() {
		Thread producer = new ProducerThread(stack, fullSemapfore, freeSemapfore);
		Thread consumer = new ConsumerThread(stack, fullSemapfore, freeSemapfore);
		
		threadsList.add(producer);
		threadsList.add(consumer);
		
		startThreads();
		joinThreads();

	}

	private void startThreads() {
		for (Thread thread : threadsList) {
			thread.start();
		}
	}
	
	private void joinThreads() {
		
		for (Thread thread : threadsList) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
