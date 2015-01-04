package threadTest.playDice;

public class RecordDiceThread implements Runnable {
	private Dice dice = null;

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
