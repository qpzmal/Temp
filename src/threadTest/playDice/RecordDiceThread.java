package threadTest.playDice;

public class RecordDiceThread implements Runnable {
	protected Dice dice = null;

	public RecordDiceThread() {
	}
	public RecordDiceThread(Dice d) {
		this.dice = d;
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			dice.getNumber();
		}
	}
}
