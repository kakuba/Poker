package main;

public class SameKindNumber {
	private int figureValue;
	private int number;
	public SameKindNumber(int figureValue) {
		this.figureValue = figureValue;
		this.number = 1;
	}
	public int getFigureValue() {
		return figureValue;
	}
	public int getNumber() {
		return number;
	}
	public void addNumber(){
		number++;
	}
}
