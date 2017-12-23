package de.qubit.controller;

import model.MetaTraderDataFrame;

public class DataFrameValidator {

	private String content;
	private MetaTraderDataFrame dataframe;
	private String symbol;
	private double close, high, low, open, atr, rsi;

	public DataFrameValidator(String content) {
		this.setContent(content);
	}

	public boolean contentIsValid() {
		boolean b = content.contains("Open") && content.contains("High") && content.contains("Low")
				&& content.contains("Close") && content.contains("RSI(14)") && content.contains("ATR(14)");

		return b;
	}
	
	private void extractFromContent() {
		String content = this.content.replace("\t", "").trim();
		String[] split = content.split("\n");
		symbol = split[0];
		for(int i=0;i<split.length;i++) {
			
			if(split[i].contains("Open")) {
				String[] value = split[i].split("Open");
				open = Double.parseDouble(value[1]);
			}
			if(split[i].contains("High")) {
				String[] value = split[i].split("High");
				high = Double.parseDouble(value[1]);
			}
			if(split[i].contains("Low")) {
				String[] value = split[i].split("Low");
				low = Double.parseDouble(value[1]);
			}
			if(split[i].contains("Close")) {
				String[] value = split[i].split("Close");
				close = Double.parseDouble(value[1]);
			}
			if(split[i].contains("RSI(14)")) {
				String[] value = split[i].split("\\)");
				System.out.println(value[0]);
				rsi = Double.parseDouble(value[1]);
			}
			if(split[i].contains("ATR(14)")) {
				String[] value = split[i].split("\\)");
				atr = Double.parseDouble(value[1]);
			}
	
		}
	}

	public void createDataFrame() {
		extractFromContent();
		
		dataframe = MetaTraderDataFrame.getInstance();
		dataframe.setSymbol(symbol);
		dataframe.setClose(close);
		dataframe.setHigh(high);
		dataframe.setLow(low);
		dataframe.setOpen(open);
		dataframe.setAtr(atr);
		dataframe.setRsi(rsi);

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MetaTraderDataFrame getDataFrame() {

		return dataframe;
	}

}
