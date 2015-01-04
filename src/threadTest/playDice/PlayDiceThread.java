package threadTest.playDice;

public class PlayDiceThread implements Runnable {
	protected Dice dice = null;

	public PlayDiceThread() {
	}
	public PlayDiceThread(Dice d) {
		this.dice = d;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try{
				dice.setNumber();
			} catch(Exception e){
				
			}
		}
	}

}
