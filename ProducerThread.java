import java.util.Stack;
import java.util.concurrent.Semaphore;

public class ProducerThread extends Thread {

	private Semaphore fullSemapfore;
	private Semaphore freeSemapfore;
	private Stack<Product> stack;
	private Product newProduct;

	public ProducerThread(Stack stack, Semaphore fullSemapfore, Semaphore freeSemapfore) {
		this.stack = stack;
		this.fullSemapfore = fullSemapfore;
		this.freeSemapfore = freeSemapfore;
	}

	@Override
	public void run() {
		while(true) {
			produceProduct();
			try {
				freeSemapfore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pushProduct();
			fullSemapfore.release();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void pushProduct() {
		stack.push(newProduct);
	}

	public void produceProduct() {
		newProduct = new Product();
		System.out.println("Product produced");
	}

}
