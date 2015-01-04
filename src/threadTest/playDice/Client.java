package threadTest.playDice;

/**
 * 实现功能：模拟掷骰子过程，一个人（线程）掷，一个人（线程）记录，当连续三次为 6 时，（线程）停止。
 * @author weiqz
 *
 */
public class Client {
	
	public static void main(String[] args) {
		Dice dice = new Dice();// 骰子类
		Thread p = new Thread(new PlayDiceThread(dice));// 掷骰子类
		Thread r = new Thread(new RecordDiceThread(dice));// 记录骰子类

		p.start();
		r.start();
		

	}
}
