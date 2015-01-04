package threadTest.playDice.exception;

import threadTest.playDice.Dice;
import threadTest.playDice.RecordDiceThread;

public class SubRecordDiceThread extends  RecordDiceThread{

	public SubRecordDiceThread(Dice d) {
		this.dice = d;
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			dice.getNumber();
		}
	}
}
