package threadTest.playDice;

public class PlayDiceThread implements Runnable {
	private Dice dice = null;

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
