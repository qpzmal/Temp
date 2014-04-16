package threadTest;

public class TestThread {
//    private String[] str = new String[20];
	private Obj obj = null;
	TestThread(Obj obj) {
		this.obj = obj;
	}
	
    Thread pushThread = new Thread() {
    	public void run() {
    		setStr();
    	}
    	public void setStr() {
//	    	for (int i = 0; i< obj.str.length; i++) {
//	    		obj.str[i] = String.valueOf(i);
//	    		System.out.println("pushThread Test,str[i] is :" + obj.str[i]);
//	    	}
    		for (int i=0; i < 10; i++) {
	    		synchronized(obj) {
	    			obj.index++;
	    			obj.str[obj.index] = String.valueOf(obj.index);
	    			System.out.println("pushThread Test,str[index] is :" + obj.index);
	    		}
    		} 
    	}
    };
    Thread pullThread = new Thread() {
    	public void run() {
    		getStr();
    	}
    	public void getStr () {
//	    	for (int i = 0; i< obj.str.length; i++) {
//	    		System.out.println("pullThread Test,str[i] is :" + obj.str[i]);
//	    	}
    		for (int i=0; i < 10; i++) {
	    		synchronized(obj) {
	    			obj.str[obj.index] = null;
	    			obj.index--;
	    			System.out.println("pullThread Test,str[index] is :" + obj.index);
	    		}
    		}
    	}
    };
    public static void main (String[] args) throws InterruptedException {
    	TestThread test = new TestThread(new Obj(null, 0));
    	test.pushThread.start();
    	test.pullThread.start();
    }
}
class Obj {
	public String[] str =null;
	public int index = 0;
	Obj (String[] str1, int i) {
		if (str1 == null) {
			this.str = new String[20];
		} else {
			this.str = str1;
		}
		index = i;
	}
}