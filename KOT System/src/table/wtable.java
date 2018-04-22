package table;



public enum wtable {
	
	table1, table2, table3,table4;
	
	private wtable() {}
	
	
	public String value() {
		return name();
		
	}
	
	
	
	
	public static wtable fromvalue(String v) {
		return valueOf(v);
		
		
		
	}
	

}
