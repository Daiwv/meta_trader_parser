package de.qubit.controller;

public class StopLossCalculator {

	private double factor = 2;
	
	public double calculateStopLoss(double currentPrice, double atr) {
		
		return currentPrice-(factor*atr);
	}
}
