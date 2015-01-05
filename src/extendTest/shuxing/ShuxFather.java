package extendTest.shuxing;

public class ShuxFather {
    public String firstName = "aa";
    protected String middleName = "bb";
    private String lastName = "cc";

    public String getFirstName(){
    	return this.firstName;
    }

    public String getMiddleName(){
    	return this.middleName;
    }
    
    public String getLastName(){
    	return this.lastName;
    }
}
