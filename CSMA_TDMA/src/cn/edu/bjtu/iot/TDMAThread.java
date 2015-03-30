package cn.edu.bjtu.iot;

public class TDMAThread extends Thread {

	private final static int SLOT_A = 1024;
	private final static int SLOT_B = 1025;
	private int currentTimeSlot = SLOT_A; // 当前时间片轮转到的主机
	TDMA A = new TDMA(100, "TDMA_A");
	TDMA B = new TDMA(80, "TDMA_B");

	public void run() {
		super.run();
		long before = System.currentTimeMillis();
		while (A.getRestDataCapacity() != 0 || B.getRestDataCapacity() != 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (currentTimeSlot == SLOT_A) {
				currentTimeSlot = SLOT_B;
				A.sendATimeSlot();
			} else if (currentTimeSlot == SLOT_B) {
				currentTimeSlot = SLOT_A;
				B.sendATimeSlot();
			}
		}
		long after = System.currentTimeMillis();
		long temp = 180 * 1000 / (after - before);
		System.out.println("吞吐量：" + temp);
		System.out.println("延迟:" + (after - before) / 1000);
	}
}
