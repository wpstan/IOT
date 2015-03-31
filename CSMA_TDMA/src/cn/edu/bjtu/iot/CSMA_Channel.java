package cn.edu.bjtu.iot;

public class CSMA_Channel {

	public static int speed = 10;// 模拟信道传输速率
	public static String occupyChannelThreadName = "";// 占用信道的线程名字
	public static long emptyBefore = System.currentTimeMillis();
	public static long totalEmpytTime;

	public static void emptyChannel() {
		emptyBefore = System.currentTimeMillis();
		occupyChannelThreadName = "";
	}

	public static void setChannelName(String name) {
		occupyChannelThreadName = name;
		totalEmpytTime += ((System.currentTimeMillis() - emptyBefore));
//		System.out.println("总的信道空闲时间:" + totalEmpytTime);
	}

}
