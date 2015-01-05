package extendTest.shuxing;

public class TestShuxing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShuxSon s = new ShuxSon();
		System.out.println(s.firstName);// guess:aaSon
		System.out.println(s.middleName);// guess:bbSon
		System.out.println(s.getFirstName());// guess:aaSon
		System.out.println(s.getMiddleName());// guess:bbSon
		System.out.println(s.getLastName());// guess:ccSon
		
		ShuxFather sf = new ShuxSon();
		System.out.println(sf.firstName);// guess:aa
		System.out.println(sf.middleName);//  guess:bb
		System.out.println(sf.getFirstName());// guess:aa---->aaSon
		System.out.println(sf.getMiddleName());//  guess:bb---->bbSon
		System.out.println(sf.getLastName());//  guess:cc---->ccSon

		ShuxSonNoMethod noMethod = new ShuxSonNoMethod();
		System.out.println(noMethod.firstName);// guess:aaNoMethod
		System.out.println(noMethod.middleName);// guess:bbNoMethod
		System.out.println(noMethod.getFirstName());// guess:aa
		System.out.println(noMethod.getMiddleName());// guess:bb
		System.out.println(noMethod.getLastName());// guess:cc

		ShuxFather noMethodf = new ShuxSonNoMethod();
		System.out.println(noMethodf.firstName);// guess:aaNoMethod---->aa
		System.out.println(noMethodf.middleName);// guess:bbNoMethod---->aa
		System.out.println(noMethodf.getFirstName());// guess:aa
		System.out.println(noMethodf.getMiddleName());// guess:bb
		System.out.println(noMethodf.getLastName());// guess:cc
	}

}
