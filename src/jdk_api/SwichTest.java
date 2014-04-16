package jdk_api;

public class SwichTest {

	public static void main(String[] args) {
		// 本类测试内容：测试char,byte,long,double,String是否可以作为swith参数
		// 结论为可以转换为int型的基本类型可以作为参数，long/double/boolean 都不可以
		// 另外，jdk7开始，String可以作为switch参数。
		char a = 'z';
		byte b = 1;
		long c = 1L;
		String d = "";
		double e = 1d;
		boolean f = true;
		
		switch (a) {}
		switch (b) {}
//		switch (c) {}
		switch (d) {}
//		switch (e) {}
//		switch (f) {}
	}

}
