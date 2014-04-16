package threadTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class Test {
	public Test () {
		a = "b";
	}
	String a = "bb";
	public static void main(String[] args) throws IOException {
		Test t = new Test();
		System.out.println(t.a);
	}
	
}
abstract class  TestA {}
class  TestB extends TestA {}
class  TestC {}