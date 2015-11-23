package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Poker {
	public static void main(String[] args) throws IOException{
		int numberOfPlayer1Wins = countPlayer1Wins();
		System.out.printf("Players 1 win for %d times.", numberOfPlayer1Wins);
	}
	
	
	
	public static int countPlayer1Wins() throws IOException {
		int result = 0;
		List<String> lineList = getLineList();
		for (int i = 0; i < lineList.size(); i++) {
			List<Hand> handsList = makeTwoHands(lineList.get(i));
			if (isPlayer1Win(handsList.get(0), handsList.get(1))) {
				result++;
			}
		}
		return result;
	}
	
	
	public static List<String> getLineList() throws IOException {
		List<String> lineList = new ArrayList<>();
		File file = new File("C:\\Users\\JWOLAN\\workspace\\Poker\\src\\main\\poker.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			lineList.add(scanner.nextLine());
		}
		return lineList;
	}
	
	
	public static int countPlayer1WinsTest() throws IOException {
		int result = 0;
		List<String> lineList = getLineListTest();
		for (int i = 0; i < lineList.size(); i++) {
			List<Hand> handsList = makeTwoHands(lineList.get(i));
			if (isPlayer1Win(handsList.get(0), handsList.get(1))) {
				result++;
			}
		}
		return result;
	}
	
	
	public static List<String> getLineListTest() throws IOException {
		List<String> lineList = new ArrayList<>();
		File file = new File("C:\\Users\\JWOLAN\\workspace\\Poker\\src\\main\\dane.txt");
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			lineList.add(scanner.nextLine());
		}
		return lineList;
	}
	
	public static List<Hand> makeTwoHands(String cardsLine) {
		List<Hand> handsList = new ArrayList<>();
		List<Card> cardsList = getCardFromString(cardsLine);
		handsList.add(new Hand (cardsList.get(0), cardsList.get(1), cardsList.get(2), cardsList.get(3), cardsList.get(4)));
		handsList.add(new Hand (cardsList.get(5), cardsList.get(6), cardsList.get(7), cardsList.get(8), cardsList.get(9)));
		return handsList ;
	}
	
	public static List<Card> getCardFromString(String cardsLine){
		List<Card> cardsList = new ArrayList<>();
		List<String> cardsListFromString = makeCardsListFromString(cardsLine);
		for (int i = 0; i <cardsListFromString.size(); i++) {
			cardsList.add(new Card (cardsListFromString.get(i).substring(0, 1),cardsListFromString.get(i).substring(1, 2)));
		}
		return cardsList;
	}
	
	public static List<String> makeCardsListFromString(String cardsLine) {
		List<String> cardsListFormString = new ArrayList<>();
		Scanner scanner = new Scanner(cardsLine);
		while (scanner.hasNext()) {
			cardsListFormString.add(scanner.next());
		}
		
		return cardsListFormString ;
	}
	
	public static boolean isPlayer1Win(Hand hand1, Hand hand2) {
		boolean result = true;
		CardSet hand1Point = getCardSet(hand1);
		CardSet hand2Point = getCardSet(hand2);
		List<SameKindNumber> listSameKindCard1 = getListSameKindCard(hand1.makeCardList());
		List<SameKindNumber> listSameKindCard2 = getListSameKindCard(hand2.makeCardList());
		
		if (hand1Point.ordinal() < hand2Point.ordinal()){
			result = false;
		} else if (hand1Point.ordinal() == hand2Point.ordinal() && listSameKindCard1.get(0).getFigureValue() < listSameKindCard2.get(0).getFigureValue()) {
			result = false;
		} else if (hand1Point.ordinal() == hand2Point.ordinal() && listSameKindCard1.get(0).getFigureValue() == listSameKindCard2.get(0).getFigureValue() &&listSameKindCard1.get(1).getFigureValue() < listSameKindCard2.get(1).getFigureValue()) {
			result = false;
		}
		//System.out.printf("%d %d\n", hand1Point.ordinal(), hand2Point.ordinal());
		return result;
	}
	
	public static List<SameKindNumber> getListSameKindCard(List<Card> cardList) {
		List<SameKindNumber> listSameKindCard = new ArrayList<SameKindNumber>();
		
		for (int i = 0; i < cardList.size(); i++) {
			boolean found = false;
			for (SameKindNumber sameKindNumber: listSameKindCard) {
				if (sameKindNumber.getFigureValue() == cardList.get(i).getFigureValue()) {
					sameKindNumber.addNumber();
					found = true;
				}
			}
			if (!found) {
				listSameKindCard.add(new SameKindNumber(cardList.get(i).getFigureValue()));
			}
		}
		listSameKindCard.sort(new SameKindCardComparator());
		
		return listSameKindCard;
	}
	
	public static CardSet getCardSet(Hand hand) {
		CardSet cardSet = null;
		List<SameKindNumber> listSameKindCard = getListSameKindCard(hand.makeCardList());
		if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.ROYAL_FLUSH)) {
			cardSet = CardSet.ROYAL_FLUSH;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.STRAIGHT_FLUSH)) {
			cardSet = CardSet.STRAIGHT_FLUSH;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.FOUR_OF_A_KIND)) {
			cardSet = CardSet.FOUR_OF_A_KIND;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.FULL_HOUSE)) {
			cardSet = CardSet.FULL_HOUSE;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.FLUSH)) {
			cardSet = CardSet.FLUSH;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.STRAIGHT)) {
			cardSet = CardSet.STRAIGHT;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.THREE_OF_A_KIND)) {
			cardSet = CardSet.THREE_OF_A_KIND;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.TWO_PAIRS)) {
			cardSet = CardSet.TWO_PAIRS;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.ONE_PAIR)) {
			cardSet = CardSet.ONE_PAIR;
		} else if (isValidSet(listSameKindCard, hand.isSameColour(), CardSet.HIGH_CARD)) {
			cardSet = CardSet.HIGH_CARD;
		}
		
		return cardSet;
		
	}
	
	private static boolean isValidSet(List<SameKindNumber> listSameKindCard,  boolean isSameColour, CardSet cardSet) {
		return isSameFigureNumberList(listSameKindCard, cardSet)
				&& isSameColour(isSameColour, cardSet)
				&& isStaright(listSameKindCard, cardSet)
				&& hasAs(listSameKindCard, cardSet);
	}

	private static boolean hasAs(List<SameKindNumber> listSameKindCard, CardSet cardSet) {
		boolean result = true;
		if (cardSet.mustBeAs() && listSameKindCard.get(0).getFigureValue() != Figure.AS.getValue()) {
			result = false;
		}
		return result;
	}

	private static boolean isStaright(List<SameKindNumber> listSameKindCard, CardSet cardSet) {
		boolean result = true;
		if (cardSet.isStraight() && listSameKindCard.get(0).getFigureValue() != listSameKindCard.get(listSameKindCard.size()-1).getFigureValue() + 4) {
			result = false;
		}
		return result;
	}

	private static boolean isSameColour(boolean isSameColour, CardSet cardSet) {
		boolean result = true;
		if (cardSet.isSameColour() && !isSameColour) {
			result = false;
		}
		return result;
	}

	private static boolean isSameFigureNumberList(List<SameKindNumber> listSameKindCard, CardSet cardSet) {
		boolean result = true;
		List<Integer> figureNumberList = cardSet.getFigureNumberList();
		
		for (int i = 0; i < listSameKindCard.size(); i++) {
			if (listSameKindCard.get(i).getNumber() != figureNumberList.get(i)) {
				result = false;
			}
		}
		
		return result;
	}
	
	
}
