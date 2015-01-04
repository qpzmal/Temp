package threadTest.playDice;

/**
 * 实现功能：测试线程设置interrupt()方法后，循环时是否会继续执行。
 * @author weiqz
 *
 */
public class InterruptTest implements Runnable {
	private static int i = 0;
	public static void main(String[] args) {
		Thread t = new Thread(new InterruptTest());
		t.start();
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			i++;
			System.out.println("循环中~~~第 "+i+" 次");
			if (i==20){
				Thread.currentThread().interrupt();
				System.out.println("*************");
			}
		}
			System.out.println("WWWWWWWWW");
	}

}
