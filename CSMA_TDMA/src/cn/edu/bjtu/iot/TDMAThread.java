package cn.edu.bjtu.iot;

public class TDMAThread extends Thread {

	private final static int SLOT_A = 1024;
	private final static int SLOT_B = 1025;
	private final static int SLOT_C = 1026;
	private final static int SLOT_D = 1027;
	private int currentTimeSlot = SLOT_A; // 当前时间片轮转到的主机
	TDMA A = new TDMA(100, "TDMA_A");
	TDMA B = new TDMA(80, "TDMA_B");
	TDMA C = new TDMA(90, "TDMA_C");
	TDMA D = new TDMA(70, "TDMA_D");

	public void run() {
		super.run();
		long before = System.currentTimeMillis();
		while (A.getRestDataCapacity() != 0 || B.getRestDataCapacity() != 0
				|| C.getRestDataCapacity() != 0 || D.getRestDataCapacity() != 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (currentTimeSlot) {
			case SLOT_A:
				currentTimeSlot = SLOT_B;
				A.sendATimeSlot();
				break;
			case SLOT_B:
				currentTimeSlot = SLOT_C;
				B.sendATimeSlot();
				break;
			case SLOT_C:
				currentTimeSlot = SLOT_D;
				C.sendATimeSlot();
				break;
			case SLOT_D:
				currentTimeSlot = SLOT_A;
				D.sendATimeSlot();
				break;
			}
		}
		long after = System.currentTimeMillis();
		long temp = 3400 * 1000 / (after - before);
		System.out.println("吞吐量：" + temp);
		System.out.println("延迟:" + (after - before) / 1000);
	}
}
