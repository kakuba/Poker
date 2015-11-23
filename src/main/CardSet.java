package main;

import java.util.ArrayList;
import java.util.List;

public enum CardSet {
	/**
	 * Parameters
	 * 1 - figureNumber1
	 * 2 - figureNumber2
	 * 3 - figureNumber3
	 * 4 - figureNumber4
	 * 5 - figureNumber5
	 * 6 - isSameColour
	 * 7 - isStraight
	 * 8 - mustBeAs
	 */
	HIGH_CARD(1, 1, 1, 1, 1, false, false, false),
	ONE_PAIR(2, 1, 1, 1, 0, false, false, false),
	TWO_PAIRS(2, 2, 1, 0, 0, false, false, false),
	THREE_OF_A_KIND(3, 1, 1, 0, 0, false, false, false),
	STRAIGHT(1, 1, 1, 1, 1, false, true, false),
	FLUSH(1, 1, 1, 1, 1, true, false, false),
	FULL_HOUSE(3, 2, 0, 0, 0, false, false, false),
	FOUR_OF_A_KIND(4, 1, 0, 0, 0, false, false, false),
	STRAIGHT_FLUSH(1, 1, 1, 1, 1, true, true, false),
	ROYAL_FLUSH(1, 1, 1, 1, 1, true, true, true);
	
	private int figureNumber1;
	private int figureNumber2;
	private int figureNumber3;
	private int figureNumber4;
	private int figureNumber5;
	private boolean isSameColour;
	private boolean isStraight;
	private boolean mustBeAs;
	
	
	private CardSet(int figureNumber1, int figureNumber2, int figureNumber3, int figureNumber4, int figureNumber5,
			boolean isSameColour, boolean isStraight, boolean mustBeAs) {
		this.figureNumber1 = figureNumber1;
		this.figureNumber2 = figureNumber2;
		this.figureNumber3 = figureNumber3;
		this.figureNumber4 = figureNumber4;
		this.figureNumber5 = figureNumber5;
		this.isSameColour = isSameColour;
		this.isStraight = isStraight;
		this.mustBeAs = mustBeAs;
	}

	public List<Integer> getFigureNumberList() {
		List<Integer> figureNumberList = new ArrayList<>();
		figureNumberList.add(figureNumber1);
		figureNumberList.add(figureNumber2);
		figureNumberList.add(figureNumber3);
		figureNumberList.add(figureNumber4);
		figureNumberList.add(figureNumber5);
		return figureNumberList;
	}
	public boolean mustBeAs() {
		return mustBeAs;
	}
	public boolean isStraight() {
		return isStraight;
	}
	public boolean isSameColour() {
		return isSameColour;
	}
	
	
	
}

