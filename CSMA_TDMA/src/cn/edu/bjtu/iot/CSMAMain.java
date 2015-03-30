package cn.edu.bjtu.iot;

public class CSMAMain {

	public static void main(String[] args) {
		Thread csmaA = new CSMAThread(100, "Thread-A");
		Thread csmaB = new CSMAThread(80, "Thread-B");
		csmaA.start();
		csmaB.start();
	}

}
