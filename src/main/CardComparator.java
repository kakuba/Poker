package main;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card prevCard, Card nextCard) {
		int result = 0;
		if (nextCard.getFigureValue() > prevCard.getFigureValue()) {
			result = 1;
		} else if (nextCard.getFigureValue() < prevCard.getFigureValue()) {
			result = -1;
		} else {
			if (nextCard.getColourValue() > prevCard.getColourValue()) {
				result = 1;
			} else if (nextCard.getColourValue() < prevCard.getColourValue()) {
				result = -1;
			} else {
				result = 0;
			}
		}
		
		return result;
	}

}
