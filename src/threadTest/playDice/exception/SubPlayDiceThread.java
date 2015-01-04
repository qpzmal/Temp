package threadTest.playDice.exception;

import threadTest.playDice.Dice;
import threadTest.playDice.PlayDiceThread;

public class SubPlayDiceThread extends  PlayDiceThread{
	public SubPlayDiceThread(Dice d) {
		this.dice = d;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try{
				dice.setNumber();
			} catch(Exception e){
				try {
					Thread.currentThread().sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
