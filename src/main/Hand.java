package main;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private Card card1;
	private Card card2;
	private Card card3;
	private Card card4;
	private Card card5;
//zamienic na liste
	public Hand(Card card1, Card card2, Card card3, Card card4, Card card5){
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.card5 = card5;
		
	}
	public Card getCard1(){
		return card1;
	}
	public Card getCard2(){
		return card2;
	}
	public Card getCard3(){
		return card3;
	}
	public Card getCard4(){
		return card4;
	}
	public Card getCard5(){
		return card5;
	}
	public List<Card> makeCardList(){
		List<Card> cardList = new ArrayList<>();
		cardList.add(card1);
		cardList.add(card2);
		cardList.add(card3);
		cardList.add(card4);
		cardList.add(card5);
		
		cardList.sort(new CardComparator());
		
		return cardList;
	}
	public boolean isSameColour() {
		boolean result = true;
		List<Card> makeCardList = makeCardList();
		for (int i = 1; i < makeCardList.size(); i++) {
			if (makeCardList.get(i-1).getColourValue() != makeCardList.get(i).getColourValue()) {
				result = false;
			}
		}
		return result;
	}
}

