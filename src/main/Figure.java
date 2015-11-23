package main;

public enum Figure {
	TWO(2,"2"),
	THREE(3,"3"),
	FOUR(4,"4"),
	FIVE(5,"5"),
	SIX(6,"6"),
	SEVEN(7,"7"),
	EIGHT(8,"8"),
	NINE(9,"9"),
	TEN(10,"T"),
	JACK(11,"J"),
	QUEEN(12,"Q"),
	KING(13,"K"),
	AS(14,"A");
	
	private int value;
	private String code;
	
	private Figure(int value, String code){
		this.value = value;
		this.code = code;
	}
	public String getCode(){
		return code;
	}
	public int getValue(){
		return value;
	}
	
	public static Figure getByCode(String code){
		if (TWO.getCode().equals(code)) {
			return TWO;
		}
		else if(THREE.getCode().equals(code)) {
			return THREE;
		}
		else if(FOUR.getCode().equals(code)) {
			return FOUR;
		}
		else if(FIVE.getCode().equals(code)) {
			return FIVE;
		}
		else if(SIX.getCode().equals(code)) {
			return SIX;
		}
		else if(SEVEN.getCode().equals(code)) {
			return SEVEN;
		}
		else if(EIGHT.getCode().equals(code)) {
			return EIGHT;
		}
		else if(NINE.getCode().equals(code)) {
			return NINE;
		}
		else if(TEN.getCode().equals(code)) {
			return TEN;
		}
		else if(JACK.getCode().equals(code)) {
			return JACK;
		}
		else if(QUEEN.getCode().equals(code)) {
			return QUEEN;
		}
		else if(KING.getCode().equals(code)) {
			return KING;
		}
		else if(AS.getCode().equals(code)) {
			return AS;
		}
		else{
			return null;
		}
	}
	
}
