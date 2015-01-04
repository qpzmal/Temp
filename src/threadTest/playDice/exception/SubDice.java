package threadTest.playDice.exception;

import java.util.Random;

import threadTest.playDice.Dice;

/**
 * 骰子类
 * @author weiqz
 *
 */
public class SubDice extends Dice {
	private int number = 0;
	private int count = 0;

	public synchronized void setNumber() throws Exception {
		if (count == 3) {
			Thread.currentThread().interrupt();
			return;
		}
		
		Random r = new Random();
		this.number = r.nextInt(6) + 1;

		if(number == 1){
			throw new Exception("aaaaaaaaaaaa");
		}
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

}
