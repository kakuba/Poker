package main;

public class Card {
	private Figure figure;
	private Colour colour;
	
	public Card(String figure, String colour) {
		this.figure = Figure.getByCode(figure);
		this.colour = Colour.getByColourCode(colour);
	}
	public int getFigureValue() {
		return figure.getValue();
	}
	public int getColourValue() {
		return colour.getColour();
	}
}
