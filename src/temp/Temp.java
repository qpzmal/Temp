package temp;

import java.util.List;

import interfaceTest.IStudy;

public class Temp {
	/**
	 * 目标：本方法用来测试返回值与finally的执行先后顺序
	 * 结论：finally先执行
	 * 
	 */
	private String t() {
		try {
			System.out.println("before return...");
			return "asdf";
//			System.out.println("肯定编译报错");
		} finally {
			System.out.println("in finally");
		}
	}
	
	public static void main (String[] arg) {
		System.out.println("before call t()...");
		System.out.println(new Temp().t());
		System.out.println("after call t()...");
	}

}
abstract class Name{
	private String name;
	public abstract boolean isStupidName(String name);
//	public abstract boolean isStupidName(String name){};
}

class Something {
    void doSomething() {
           // 局部变量访问控制符最多为final
        final String s = "";
        // private String s="";
        int l = s.length();
    }
}

abstract class Something2 {
    public abstract void doSomething1();
    abstract void doSomething2();
    protected abstract void doSomething3();
    // private abstract void doSomething4();
    // void doSomething5();
    void doSomething6() {};
}

interface Something3 {
   public void dosomething1();
   void dosomething2();
//   protected void dosomething3();
//   private void dosomething4();
   abstract void dosomething5();
   abstract public void dosomething6();
}
class Something3Impl implements Something3{
    public void dosomething1() {}
     // 因为java接口默认都是public的，不管是否写public
     // 所以实现方法必须为public
//     void dosomething2() {}
    public void dosomething2() {}
//     void dosomething5() {}
    public void dosomething5() {}
    public void dosomething6() {}
}

