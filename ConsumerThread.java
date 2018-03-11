import java.util.Stack;
import java.util.concurrent.Semaphore;

public class ConsumerThread extends Thread {

	private Semaphore fullSemapfore;
	private Semaphore freeSemapfore;
	private Stack stack;
	private IProduct product;

	public ConsumerThread(Stack stack, Semaphore fullSemapfore, Semaphore freeSemapfore) {		
		this.stack = stack;
		this.fullSemapfore = fullSemapfore;
		this.freeSemapfore = freeSemapfore;
	}

	@Override
	public void run() {
		while(true) {
			try {
				fullSemapfore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			freeSemapfore.release();
			consumeProduct();
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void popProduct() {
		product = (IProduct) stack.pop();
	}

	public void consumeProduct() {
		System.out.println("Product consumed");
	}
	
	

}
