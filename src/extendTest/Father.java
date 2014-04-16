package extendTest;

public class Father {
	protected Father(){
		// 如果此构造方法的访问控制符为private, 
		// 那么它子类的构造方法必须用super调用父类其他构造方法
		// ...........我是分割线...........
		// 变量a是想验证成员变量的初始化顺序。
		a=1;}
	protected Father(String name){}
	Father(int ID){}
	public Father(String name,int ID){}
	int a=0;
}
