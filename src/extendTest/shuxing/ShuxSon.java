package extendTest.shuxing;

public class ShuxSon extends ShuxFather{
    public String firstName = "aaSon";
    protected String middleName = "bbSon";
    private String lastName = "ccSon";

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
