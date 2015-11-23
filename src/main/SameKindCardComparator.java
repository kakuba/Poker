package main;

import java.util.Comparator;

public class SameKindCardComparator implements Comparator<SameKindNumber> {

	@Override
	public int compare(SameKindNumber prevCard, SameKindNumber nextCard) {
		int result = 0;
		if (nextCard.getNumber() > prevCard.getNumber()) {
			result = 1;
		} else if (nextCard.getNumber() < prevCard.getNumber()) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

}
