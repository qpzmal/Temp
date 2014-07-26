package threadTest;

/**
 * 测试wait方法后，notifyAll的时候，是从哪里开始执行
 * 结论：是从wait方法后开始执行！
 * @author ww
 *
 */
public class TestWait implements Runnable { 
    
   public static String s = "0";
    public   int i = 0;

    @Override
    public  void run() {
        for (; i< 10; i++) {
                if ( i != 5) {
                    System.out.println("thread is :"+Thread.currentThread().getName()+"; i is"+i);
                }else {
                    try {
                        synchronized (this) {

                            this.wait();   
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        }
    }
    
    public static void main(String[] args) {
        TestWait x = new TestWait();
        Thread t = new Thread(x);
        t.start();
        while (true) {
//            if (x.i == 5) {

                synchronized (x) {

                   x.notifyAll(); 
//                }
                
                }
            
        }
        
        
    }

}
