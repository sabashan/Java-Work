package waiter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class order {
	
	private final IntegerProperty orderid;
	private final StringProperty fName;
	private final StringProperty fid;

	private final DoubleProperty price;
	private Button delete;
	 private  TextField firstName;
	 private final IntegerProperty qty;
	 private final StringProperty categories;
	
	
	public order(int orderid,String fid, String fName, Double price,  int qty, String categories) {
		
		this.orderid = new SimpleIntegerProperty(orderid);
		this.fName = new SimpleStringProperty(fName);
		
		this.price = new SimpleDoubleProperty(price);
		this.fid = new SimpleStringProperty(fid);
		this.qty = new SimpleIntegerProperty(qty);
		this.categories = new SimpleStringProperty(categories);
		
		
	}
	

	
	
	
	
	public void setfirstName(TextField firstName) {
		
		this.firstName=firstName;
		
	}
	
	
	
	
	
	public TextField getfirstName() {
		
		return firstName;
		
		
	}
	
	
	public final StringProperty categoriesProperty() {
		return this.categories;
	}
	
	

	public final String getcategories() {
		return this.fidProperty().get();
	}
	

	public final void setcategories(final String categories) {
		this.categoriesProperty().set(categories);
	}
	

	

	public final IntegerProperty orderidProperty() {
		return this.orderid;
	}
	

	public final int getorderid() {
		return this.orderidProperty().get();
	}
	

	public final void setorderid(final int orderid) {
		this.orderidProperty().set(orderid);
	}
	
	
	
	public final StringProperty fidProperty() {
		return this.fid;
	}
	
	

	public final String getfId() {
		return this.fidProperty().get();
	}
	

	public final void setfId(final String fid) {
		this.fidProperty().set(fid);
	}
	
	
	
	

	public final StringProperty fNameProperty() {
		return this.fName;
	}
	

	public final String getfName() {
		return this.fNameProperty().get();
	}
	

	public final void setfNamee(final String fName) {
		this.fNameProperty().set(fName);
	}
	


	

	public final DoubleProperty priceProperty() {
		return this.price;
	}
	

	public final Double getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final Double price) {
		this.priceProperty().set(price);
	}
	
	
	public final IntegerProperty qtyProperty() {
		return this.qty;
	}
	

	public final int getqty() {
		return this.orderidProperty().get();
	}
	

	public final void setqty(final int qty) {
		this.orderidProperty().set(qty);
	}
	
	
	
	

}
