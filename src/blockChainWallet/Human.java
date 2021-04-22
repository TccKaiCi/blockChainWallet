package blockChainWallet;

public class Human {
	
	private String name;
	private String age;
	
	public Human() {
		
	}
	
	public Human(String name, String age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAge( ) {
		return this.age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
}
