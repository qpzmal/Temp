package cloneTest;

import java.util.HashMap;

public class MyCloneTest extends HashMap implements Cloneable{
	// 想测试Object.clone方法和HashMap.clone方法
	String name = "";
	String age = "";
	public MyCloneTest(String name, String age) {
		this.name = name;
		this.age = age;
	}
	
	public static void main(String[] agrs) throws CloneNotSupportedException {
		MyCloneTest weiqz = new MyCloneTest("weiqz", "32");
		MyCloneTest wangry = (MyCloneTest) weiqz.clone(); 
		System.out.println(wangry.name);
		System.out.println(wangry.age);
	}
}
