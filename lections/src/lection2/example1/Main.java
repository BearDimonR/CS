package example1;

public class Main {

	public static void main(String[] args) throws Exception {

		MyThread omt = new MyThread();
		System.out.println("Демон "+omt.thread.isDaemon());

		omt.thread.start();

		Thread.sleep(100);
		omt.thread.interrupt();
		omt.cancel=true;

		System.out.println("end of main");
	}

}
