package cn.edu.bjtu.iot;

public class CSMA_Channel {

	public static int speed = 10;// 模拟信道传输速率
	public static String occupyChannelThreadName = "";// 占用信道的线程名字

	public static void emptyChannel() {
		occupyChannelThreadName = "";
	}

}
