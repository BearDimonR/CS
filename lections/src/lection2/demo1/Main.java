package lection2.demo1;

public class Main {

	public static void main(String[] args) throws Exception {

		final MyThread1 mt = new MyThread1();
		MyThread2 omt = new MyThread2();
		System.out.println("Демон "+mt.isDaemon());
		//Просимо стартувати потоки
		//класичний підхід
		mt.start();
		//другий підхід через інтерфейс
		//тут можна властивістю ... або гетером
		omt.thread.start();
		System.out.println("end of main");
	}

}
