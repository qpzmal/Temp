package extendTest;


public class ExtendTest {
	protected int method1(int a, int b) { return 0; }
	
	public static void main(String[] args) {}
}
class B extends ExtendTest{
    // 正确：访问控制符可以扩大
//	public int method1(int a, int b) { return 0; }
	
	// 错误：重写时方法访问控制符不能缩小
//	private int method1(int a, int b) { return 0; }
	
	// 正确：参数不同，为重载
//	 private int method1(int a, long b) { return 0; }
	 
    // 错误：要求子类方法返回类型不变，或为父类返回类型的子类
//	 public short method1(int a, int b) { return 0; }
	 
	 // 错误：父类非静态不能重写为静态
//	 static protected int method1(int a, int b) { return 0; }
}