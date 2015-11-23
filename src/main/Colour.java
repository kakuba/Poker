package main;

public enum Colour {
	CLUB(1,"C"),
	DIAMOND(2,"D"),
	HEART(3,"H"),
	SPADE(4,"S");
	
	private int colour;
	private String colourCode;
	
	private Colour(int colour, String colourCode){
		this.colour = colour;
		this.colourCode = colourCode;
	}
	public String getColourCode(){
		return colourCode;
	}
	public int getColour(){
		return colour;
	}
	
	public static Colour getByColourCode(String colourCode){
		if (CLUB.getColourCode().equals(colourCode)) {
			return CLUB;
		}
		if (DIAMOND.getColourCode().equals(colourCode)) {
			return DIAMOND;
		}
		if (HEART.getColourCode().equals(colourCode)) {
			return HEART;
		}
		if (SPADE.getColourCode().equals(colourCode)) {
			return SPADE;
		}
		else{
			return null;
		}
	}
}
