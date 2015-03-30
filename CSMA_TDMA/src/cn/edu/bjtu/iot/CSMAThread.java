package cn.edu.bjtu.iot;

import java.util.Random;

public class CSMAThread extends Thread {
	private IStatusCallBack callBack;// 传输成功回调
	private int dataCapacity;// 模拟传输的数据量
	private int restDataCapacity;// 剩余需要传输的数据量
	private String threadName; // 线程名字

	public CSMAThread(int dataCapacity, String threadName,
			IStatusCallBack callBack) {
		this.dataCapacity = dataCapacity;
		this.restDataCapacity = dataCapacity;
		this.threadName = threadName;
		this.callBack = callBack;
	}

	// 二进制指数退避算法相关成员变量
	private int backCount = 0; // (回退次数)发生冲突的次数

	public void run() {
		super.run();
		while (restDataCapacity != 0) {
			// 尝试次数大于16次,则发送失败
			if (backCount == 16) {
				System.out.println("发送失败");
			}
			// 判断信道是否正在被使用
			if (!CSMA_Channel.occupyChannelThreadName.equals(threadName)
					&& !"".equals(CSMA_Channel.occupyChannelThreadName)) {
				// 信道被占用,计算等待时延
				backCount++;
				int sleepTime = getSleepTime();
				System.out.println(threadName + ": 第" + backCount
						+ "次尝试，发生冲突,睡眠 " + sleepTime + "s");
				try {
					sleep(sleepTime * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				CSMA_Channel.occupyChannelThreadName = threadName;
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 模拟传输剩余数据
				restDataCapacity = restDataCapacity - CSMA_Channel.speed;
				System.out.println(threadName + " 传输了 "
						+ (dataCapacity - restDataCapacity));
				if (restDataCapacity == 0) {
					System.out.println(threadName + " 传输完毕!");
					// 传输完成
					CSMA_Channel.emptyChannel();
					callBack.onTransferSuccess(threadName);
				}
			}
		}
	}

	// 计算等待时延
	private int getSleepTime() {
		Random r = new Random();
		return r.nextInt((int) Math.pow(2, backCount));
	}
}
