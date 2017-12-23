package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MetaTraderDataFrame {

	private StringProperty symbol;

	private DoubleProperty open;
	private DoubleProperty high;
	private DoubleProperty low;
	private DoubleProperty close;

	private DoubleProperty rsi;
	private DoubleProperty atr;

	private static MetaTraderDataFrame instance = null;

	public MetaTraderDataFrame() {
		symbol = new SimpleStringProperty();
		open = new SimpleDoubleProperty();
		high = new SimpleDoubleProperty();
		low = new SimpleDoubleProperty();
		close = new SimpleDoubleProperty();
		rsi = new SimpleDoubleProperty();
		atr = new SimpleDoubleProperty();
	}

	public static MetaTraderDataFrame getInstance() {
		if (instance == null) {
			instance = new MetaTraderDataFrame();
		}
		return instance;
	}

	public String getSymbol() {
		return symbol.get();
	}

	public void setSymbol(String symbol) {
		this.symbol.set(symbol);
	}

	public double getOpen() {
		return open.get();
	}

	public void setOpen(double open) {
		this.open.set(open);
	}

	public double getHigh() {
		return high.get();
	}

	public void setHigh(double high) {
		this.high.set(high);
	}

	public double getLow() {
		return low.get();
	}

	public void setLow(double low) {
		this.low.set(low);

	}

	public double getClose() {
		return close.get();
	}

	public void setClose(double close) {
		this.close.set(close);
	}

	public double getRsi() {
		return rsi.get();
	}

	public void setRsi(double rsi) {
		this.rsi.set(rsi);
	}

	public double getAtr() {
		return atr.get();
	}

	public void setAtr(double atr) {
		this.atr.set(atr);
	}

}
