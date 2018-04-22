package waiter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class fooddetials {
	
	private final StringProperty fid;
	private final StringProperty foodName;
	private final IntegerProperty stock;
	private final DoubleProperty price;
	private final StringProperty catergories;
	
	public fooddetials(String fid, String foodName, int stock, Double price, String catergories ) {
		
		this.fid = new SimpleStringProperty(fid);
		this.foodName = new SimpleStringProperty(foodName);
		this.stock = new SimpleIntegerProperty(stock);
		this.price = new SimpleDoubleProperty(price);
		this.catergories = new SimpleStringProperty(catergories);
		
		
		
		
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
	

	public final StringProperty foodNameProperty() {
		return this.foodName;
	}
	

	public final String getfoodName() {
		return this.foodNameProperty().get();
	}
	

	public final void setfoodName(final String foodName) {
		this.foodNameProperty().set(foodName);
	}
	

	public final IntegerProperty stockProperty() {
		return this.stock;
	}
	

	public final int getstock() {
		return this.stockProperty().get();
	}
	

	public final void setstock(final int stock) {
		this.stockProperty().set(stock);
	}
	
	
	public final StringProperty catergoriesProperty() {
		return this.catergories;
	}
	

	public final String getcatergories() {
		return this.catergoriesProperty().get();
	}
	

	public final void setcatergories(final String catergories) {
		this.catergoriesProperty().set(catergories);
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
	
	
	
	
	
	
	

}
