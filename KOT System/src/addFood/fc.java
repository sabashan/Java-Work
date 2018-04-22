package addFood;

import login.Department;

public enum fc {
	
  dish, drinks;
	
	private fc() {}
	
	
	public String value() {
		return name();
		
	}
	
	
	
	
	public static fc fromvalue(String v) {
		return valueOf(v);
		
		
		
	}

}
