package threadTest.playDice.exception;

import java.util.Random;

import threadTest.playDice.Dice;

/**
 * 骰子类
 * @author weiqz
 *
 */
public class SubDice extends Dice {

	public synchronized void setNumber() throws Exception {
		if (count == 3) {
			Thread.currentThread().interrupt();
			return;
		}
		
		Random r = new Random();
		this.number = r.nextInt(6) + 1;

		System.out.println("线程 "+Thread.currentThread().getName()+" 骰子数："+this.number);
		if(number == 1){
			this.notify();
//            Thread.currentThread().interrupt();
			throw new Exception("aaaaaaaaaaaa");
		}
		this.notify();
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
