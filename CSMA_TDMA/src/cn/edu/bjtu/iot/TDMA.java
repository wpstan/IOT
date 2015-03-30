package cn.edu.bjtu.iot;

public class TDMA {

	private int dataCapacity;// 模拟传输的数据量
	private int restDataCapacity;// 剩余需要传输的数据量
	private String threadName; // 线程名字

	public TDMA(int dataCapacity, String threadName) {
		this.setDataCapacity(dataCapacity);
		this.setRestDataCapacity(dataCapacity);
		this.setThreadName(threadName);
	}

	// 传输了一个时间片
	public void sendATimeSlot() {
		System.out.print("时间片轮转到了 " + threadName);
		restDataCapacity = restDataCapacity - 10;
		if (restDataCapacity < 0) {
			restDataCapacity = 0;
		}
		if (restDataCapacity == 0) {
			System.out.println(" ,传输完成!");
		} else {
			System.out.println(" ,传输了：" + (dataCapacity - restDataCapacity));
		}
	}

	public int getDataCapacity() {
		return dataCapacity;
	}

	public void setDataCapacity(int dataCapacity) {
		this.dataCapacity = dataCapacity;
	}

	public int getRestDataCapacity() {
		return restDataCapacity;
	}

	public void setRestDataCapacity(int restDataCapacity) {
		this.restDataCapacity = restDataCapacity;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
