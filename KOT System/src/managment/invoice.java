package managment;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

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

public class invoice {

	private final IntegerProperty invoiceid;
	private final  StringProperty waiterid;
	private final StringProperty tableid;
	private final  StringProperty date;
	private final StringProperty time;
	private final IntegerProperty dish;
	private final IntegerProperty drinks;
	private final DoubleProperty price;
	
	

	public invoice(int invoiceid,String waiterid, String tableid, String date,String time, int dish, int drinks, double price) {


		this.invoiceid = new SimpleIntegerProperty(invoiceid);
		this.waiterid = new SimpleStringProperty(waiterid);
		this.tableid = new SimpleStringProperty(tableid);
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.dish = new SimpleIntegerProperty(dish);
		this.drinks = new SimpleIntegerProperty(drinks);
		this.price = new SimpleDoubleProperty(price);


	}

	

	public final IntegerProperty invoiceidProperty() {
		return this.invoiceid;
	}

	public final int getinvoiceid() {
		return this.invoiceidProperty().get();
	}

	public final void setinvoiceid(final int invoiceid) {
		this.invoiceidProperty().set(invoiceid);
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
	
	
	

	public final IntegerProperty dishProperty() {
		return this.dish;
	}

	public final int getdish() {
		return this.dishProperty().get();
	}

	public final void setdish(final int dish) {
		this.dishProperty().set(dish);
	}
	
	
	public final IntegerProperty drinksProperty() {
		return this.drinks;
	}

	public final int getdrinks() {
		return this.drinksProperty().get();
	}

	public final void setdrinks(final int drinks) {
		this.drinksProperty().set(drinks);
	}
	
	
	public final StringProperty dateProperty() {
		return this.date;
	}

	public final String getdate() {
		return this.dateProperty().get();
	}

	public final void setdate(final String date) {
		this.dateProperty().set(date);
	}
	
	
	public final StringProperty timeProperty() {
		return this.time;
	}

	public final String gettime() {
		return this.timeProperty().get();
	}

	public final void settime(final String time) {
		this.timeProperty().set(time);
	}
	
	
	
	public final StringProperty waiteridProperty() {
		return this.waiterid;
	}

	public final String getwaiterid() {
		return this.waiteridProperty().get();
	}

	public final void setwaiterid(final String waiterid) {
		this.waiteridProperty().set(waiterid);
	}
	
	
	
	public final StringProperty tableidProperty() {
		return this.tableid;
	}

	public final String gettableid() {
		return this.tableidProperty().get();
	}

	public final void settableid(final String tableid) {
		this.tableidProperty().set(tableid);
	}


}
