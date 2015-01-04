package threadTest.playDice;

import java.util.Random;

/**
 * 骰子类
 * @author weiqz
 *
 */
public class Dice {
	protected int number = 0;
	protected int count = 0;

	public synchronized void setNumber() throws Exception {
		if (count == 3) {
			Thread.currentThread().interrupt();
			return;
		}
		
		Random r = new Random();
		this.number = r.nextInt(6) + 1;
//		System.out.println("线程 "+Thread.currentThread().getName()+" 骰子数："+this.number);

		this.notify();
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void getNumber() {
		System.out.println("线程 "+Thread.currentThread().getName()+" 骰子数："+this.number +"，六的次数：" + count);

		if (this.number == 6) {
			count++;
			if (count == 3) {
				Thread.currentThread().interrupt();
				this.notify();
				return;
			}
		} else {
			count = 0;
		}
		
		this.notify();
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		Dice dice = new Dice();
//		dice.setNumber();
//		System.out.println(dice.getNumber());
//	}
}
