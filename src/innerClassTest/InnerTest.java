package innerClassTest;

public class InnerTest {
	public String a = "";
	String b = "";
	protected String c = "";
	private String d = "";

	public class A{
		private void test() {
			String temp = a;
			temp = b;
			temp = c;
			temp = d;
		}
	};
	class B{
		private void test() {
			String temp = a;
			temp = b;
			temp = c;
			temp = d;
		}
	};
	protected class C{
		private void test() {
			String temp = a;
			temp = b;
			temp = c;
			temp = d;
		}
	};
	private class D{
		private void test() {
			String temp = a;
			temp = b;
			temp = c;
			temp = d;
		}
	};
	
	public static void main(String[] args) {
		
	}

}
