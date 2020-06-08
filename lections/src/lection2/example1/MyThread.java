package example1;

public class MyThread implements Runnable{
	public Thread thread;

	//volatile - потрібне компілятору при генеруванні байт коду, щоб компілятор не
	//зробив оптимізацію прибравши IF який ніколи не спрацює
	public volatile boolean cancel=false;

	public MyThread(){
		thread = new Thread(this);
	}

	@Override
	public void run() {
		//try{
		double d = 9999.0;
		for (int i = 0; i < 30; i++){

			/*	if (cancel)
					return; // тут може бути будь який завершуючий код
					//а можна так красиво
					//throw new InterruptedException();
				*/
			for (int j=0;j<1000000;j++){
				d=d*d/d;
			}
			System.out.println("Name = " + Thread.currentThread().getName() +
					", i = " + i);
		}
		//	}catch(InterruptedException ex){
		//	ex.printStackTrace();
		//	}
	}

}
