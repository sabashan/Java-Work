package login;


public enum Department {
	
	waiter,kitchen,management;
	
	private Department() {}
	
	
	public String value() {
		return name();
		
	}
	
	
	
	
	public static Department fromvalue(String v) {
		return valueOf(v);
		
		
		
	}
	

}
