package test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.Card;
import main.CardSet;
import main.Colour;
import main.Figure;
import main.Hand;
import main.Poker;
import main.SameKindNumber;
import main.SameKindNumberAssert;

public class PokerTest {

	@Test
	public void shouldGiveValueTenForStringT() {
		//given
		String figure = "T";
		//when
		Figure tmpFigure = Figure.getByCode(figure);
		int figureResult = tmpFigure.getValue();
		//then
		Assert.assertEquals(figureResult, Figure.TEN.getValue());
	}
	@Test
	public void shouldGiveColorValueTwoForStringD() {
		//given
		String colour = "D";
		//when
		Colour tmpColour = Colour.getByColourCode(colour);
		int colorResult = tmpColour.getColour();
		//then
		Assert.assertEquals(colorResult, Colour.DIAMOND.getColour());
	}
	@Test
	public void shouldGiveCorrectValueForColourAndFigureForOneCard() {
		//given
		String figure = "T";
		String colour = "D";
		//when
		Card card = new Card(figure, colour);
		//then
		CardAssert.assertThat(card).hasFigureValue(10).hasColourValue(2);
	}
	@Test
	public void shouldGiveCorrectValueForColourAndFigureForOneCardString() {
		//given
		String cardString = "TD";
		String figure = cardString.substring(0, 1);
		String colour = cardString.substring(1, 2);
		//when
		Card card = new Card(figure, colour);
		//then
		CardAssert.assertThat(card).hasFigureValue(10).hasColourValue(2);
	}
	@Test
	public void shouldGiveCorrectValueForColourAndFigureForOneHand() {
		//given
		Hand hand = new Hand(new Card("4","D"),new Card("Q","C"),new Card("7","D"),new Card("7","H"),new Card("K","S"));
		//when
		//then
		HandAssert.assertThat(hand).hasCard1(new Card("4","D")).hasCard2(new Card("Q","C")).hasCard3(new Card("7","D")).hasCard4(new Card("7","H")).hasCard5(new Card("K","S"));
	}
	@Test
	public void shouldMakeSortedCardList() {
		//given
		Card card1 = new Card("4","D");
		Card card2 = new Card("Q","C");
		Card card3 = new Card("7","D");
		Card card4 = new Card("7","H");
		Hand hand = new Hand(card1,card2,card3,card4,new Card("K","S"));
		//when
		List<Card> resultCardList = hand.makeCardList();
		//then
		CardAssert.assertThat(resultCardList.get(0)).hasColourValue(4).hasFigureValue(13);
		CardAssert.assertThat(resultCardList.get(1)).hasColourValue(1).hasFigureValue(12);
		CardAssert.assertThat(resultCardList.get(2)).hasColourValue(3).hasFigureValue(7);
		CardAssert.assertThat(resultCardList.get(3)).hasColourValue(2).hasFigureValue(7);
		CardAssert.assertThat(resultCardList.get(4)).hasColourValue(2).hasFigureValue(4);
	}
	@Test
	public void shouldMakeSortedSetCardList() {
		//given
		Hand hand = new Hand(new Card("4","D"),new Card("Q","C"),new Card("7","D"),new Card("7","H"),new Card("K","S"));
		List<Card> cardList = hand.makeCardList();
		//when
		List<SameKindNumber> resultListSameKindCard = Poker.getListSameKindCard(cardList);
		//then
		SameKindNumberAssert.assertThat(resultListSameKindCard.get(0)).hasFigureValue(7).hasNumber(2);
		SameKindNumberAssert.assertThat(resultListSameKindCard.get(1)).hasFigureValue(13).hasNumber(1);
		SameKindNumberAssert.assertThat(resultListSameKindCard.get(2)).hasFigureValue(12).hasNumber(1);
		SameKindNumberAssert.assertThat(resultListSameKindCard.get(3)).hasFigureValue(4).hasNumber(1);
	}
	@Test
	public void shouldBeRoyalFlush() {
		//given
		Hand handRoyalFlush = new Hand(new Card("J","D"),new Card("Q","D"),new Card("A","D"),new Card("T","D"),new Card("K","D"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handRoyalFlush);
		//then
		Assert.assertEquals(resultCardSet, CardSet.ROYAL_FLUSH);
	}
	@Test
	public void shouldNotBeRoyalFlush() {
		//given
		Hand handNotRoyalFlush = new Hand(new Card("J","D"),new Card("Q","C"),new Card("A","D"),new Card("T","D"),new Card("K","D"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handNotRoyalFlush);
		//then
		Assert.assertNotEquals(resultCardSet, CardSet.ROYAL_FLUSH);
	}
	@Test
	public void shouldBeStraightFlush() {
		//given
		Hand handStarightFlush = new Hand(new Card("J","D"),new Card("Q","D"),new Card("9","D"),new Card("T","D"),new Card("K","D"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handStarightFlush);
		//then
		Assert.assertEquals(resultCardSet, CardSet.STRAIGHT_FLUSH);
	}
	@Test
	public void shouldBeFourOfAKind() {
		//given
		Hand handFourOfKind = new Hand(new Card("K","D"),new Card("K","C"),new Card("K","H"),new Card("T","S"),new Card("K","S"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handFourOfKind);
		//then
		Assert.assertEquals(resultCardSet, CardSet.FOUR_OF_A_KIND);
	}
	@Test
	public void shouldBeFullHouse() {
		//given
		Hand handFullHouse = new Hand(new Card("9","D"),new Card("9","C"),new Card("9","S"),new Card("K","D"),new Card("K","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handFullHouse);
		//then
		Assert.assertEquals(resultCardSet, CardSet.FULL_HOUSE);
	}
	@Test
	public void shouldBeFlush() {
		//given
		Hand handFlush = new Hand(new Card("A","D"),new Card("2","D"),new Card("9","D"),new Card("Q","D"),new Card("K","D"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handFlush);
		//then
		Assert.assertEquals(resultCardSet, CardSet.FLUSH);
	}
	@Test
	public void shouldBeStraight() {
		//given
		Hand handStraight = new Hand(new Card("9","D"),new Card("T","C"),new Card("8","S"),new Card("7","D"),new Card("J","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handStraight);
		//then
		Assert.assertEquals(resultCardSet, CardSet.STRAIGHT);
	}
	@Test
	public void shouldBeThreeOfAKind() {
		//given
		Hand handThreeOfKind = new Hand(new Card("J","D"),new Card("J","C"),new Card("J","S"),new Card("K","D"),new Card("7","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handThreeOfKind);
		//then
		Assert.assertEquals(resultCardSet, CardSet.THREE_OF_A_KIND);
	}
	@Test
	public void shouldBeTwoPairs() {
		//given
		Hand handTwoPair = new Hand(new Card("9","D"),new Card("9","C"),new Card("8","S"),new Card("K","D"),new Card("K","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handTwoPair);
		//then
		Assert.assertEquals(resultCardSet, CardSet.TWO_PAIRS);
	}
	@Test
	public void shouldBeOnePair() {
		//given
		Hand handOnePair = new Hand(new Card("J","D"),new Card("3","C"),new Card("7","S"),new Card("K","D"),new Card("7","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handOnePair);
		//then
		Assert.assertEquals(resultCardSet, CardSet.ONE_PAIR);
	}
	@Test
	public void shouldBeHighCard() {
		//given
		Hand handHighCard = new Hand(new Card("7","D"),new Card("6","C"),new Card("9","S"),new Card("2","D"),new Card("K","C"));
		//when
		CardSet resultCardSet = Poker.getCardSet(handHighCard);
		//then
		Assert.assertEquals(resultCardSet, CardSet.HIGH_CARD);
	}
	@Test
	public void shouldPlayer1Win() {
		//given
		Hand hand1 = new Hand(new Card("7","D"),new Card("6","C"),new Card("9","S"),new Card("9","D"),new Card("K","C"));
		Hand hand2 = new Hand(new Card("7","D"),new Card("6","C"),new Card("9","S"),new Card("2","D"),new Card("A","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1, hand2);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldPlayer2Win() {
		//given
		Hand hand1TwoPairs = new Hand(new Card("7","D"),new Card("6","C"),new Card("9","S"),new Card("9","D"),new Card("K","C"));
		Hand hand2ThreeOfKind = new Hand(new Card("7","D"),new Card("2","C"),new Card("9","S"),new Card("2","D"),new Card("2","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1TwoPairs, hand2ThreeOfKind);
		//then
		Assert.assertFalse(result);
	}
	@Test
	public void shouldPlayer1WinIfBothHasSamePoint() {
		//given
		Hand hand1ThreeOfKind9 = new Hand(new Card("9","D"),new Card("6","C"),new Card("9","S"),new Card("9","D"),new Card("K","C"));//handThreeOfKind9
		Hand hand2ThreeOfKind2 = new Hand(new Card("7","D"),new Card("2","C"),new Card("9","S"),new Card("2","D"),new Card("2","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1ThreeOfKind9, hand2ThreeOfKind2);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldPlayer1WinIfBothHasSamePointAnd1HighCard() {
		//given
		Hand hand1ThreeOfKind9AndHigherCard = new Hand(new Card("9","D"),new Card("6","C"),new Card("9","S"),new Card("9","H"),new Card("K","C"));
		Hand hand2ThreeOfKind9 = new Hand(new Card("7","D"),new Card("9","C"),new Card("9","S"),new Card("2","D"),new Card("9","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1ThreeOfKind9AndHigherCard, hand2ThreeOfKind9);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldPlayer1WinIfBothHasStraightAnd1HighCard() {
		//given
		Hand hand1HigherStraight = new Hand(new Card("9","D"),new Card("T","C"),new Card("7","S"),new Card("8","H"),new Card("J","C"));
		Hand hand2Straight = new Hand(new Card("T","D"),new Card("9","C"),new Card("8","S"),new Card("6","D"),new Card("7","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1HigherStraight, hand2Straight);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldPlayer1WinIfBothHasTwoPairsAnd1HasHigherSecondPair() {
		//given
		Hand hand1TwoPairsAndHigherCard = new Hand(new Card("9","D"),new Card("6","C"),new Card("9","S"),new Card("8","H"),new Card("8","C"));
		Hand hand2TwoPairs = new Hand(new Card("T","D"),new Card("9","C"),new Card("9","S"),new Card("7","D"),new Card("7","C"));
		//when
		boolean result =  Poker.isPlayer1Win(hand1TwoPairsAndHigherCard, hand2TwoPairs);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldMakeCardsListFromString() {
		//given
		String cardString = "6S 4C QH QC 8D";
		//when
		List<String> resultList =  Poker.makeCardsListFromString(cardString);
		//then
		Assert.assertEquals(resultList.get(0), "6S");
		Assert.assertEquals(resultList.get(1), "4C");
		Assert.assertEquals(resultList.get(2), "QH");
		Assert.assertEquals(resultList.get(3), "QC");
		Assert.assertEquals(resultList.get(4), "8D");
	}
	@Test
	public void shouldMakeCardList() {
		//given
		String cardsLine = "9D 6C 9S 8H 8C TD 9C 9S 7D 7C";
		//when
		List<Card> resultCardsList =  Poker.getCardFromString(cardsLine);
		//then
		CardAssert.assertThat(resultCardsList.get(0)).hasFigureValue(Figure.NINE.getValue()).hasColourValue(Colour.DIAMOND.getColour());
		CardAssert.assertThat(resultCardsList.get(1)).hasFigureValue(Figure.SIX.getValue()).hasColourValue(Colour.CLUB.getColour());
		CardAssert.assertThat(resultCardsList.get(2)).hasFigureValue(Figure.NINE.getValue()).hasColourValue(Colour.SPADE.getColour());
		CardAssert.assertThat(resultCardsList.get(3)).hasFigureValue(Figure.EIGHT.getValue()).hasColourValue(Colour.HEART.getColour());
		CardAssert.assertThat(resultCardsList.get(4)).hasFigureValue(Figure.EIGHT.getValue()).hasColourValue(Colour.CLUB.getColour());
		CardAssert.assertThat(resultCardsList.get(5)).hasFigureValue(Figure.TEN.getValue()).hasColourValue(Colour.DIAMOND.getColour());
		CardAssert.assertThat(resultCardsList.get(6)).hasFigureValue(Figure.NINE.getValue()).hasColourValue(Colour.CLUB.getColour());
		CardAssert.assertThat(resultCardsList.get(7)).hasFigureValue(Figure.NINE.getValue()).hasColourValue(Colour.SPADE.getColour());
		CardAssert.assertThat(resultCardsList.get(8)).hasFigureValue(Figure.SEVEN.getValue()).hasColourValue(Colour.DIAMOND.getColour());
		CardAssert.assertThat(resultCardsList.get(9)).hasFigureValue(Figure.SEVEN.getValue()).hasColourValue(Colour.CLUB.getColour());
		
	}
	@Test
	public void shouldPlayer1WinIfBothHasTwoPairsAnd1HasHigherSecondPairString() {
		//given
		String cardsLine = "9D 6C 9S 8H 8C TD 9C 9S 7D 7C";
		List<Card> cardsList =  Poker.getCardFromString(cardsLine);
		Hand hand1TwoPairsAndHigherCard = new Hand(cardsList.get(0),cardsList.get(1),cardsList.get(2),cardsList.get(3),cardsList.get(4));
		Hand hand2TwoPairs = new Hand(cardsList.get(5),cardsList.get(6),cardsList.get(7),cardsList.get(8),cardsList.get(9));
		//when
		boolean result =  Poker.isPlayer1Win(hand1TwoPairsAndHigherCard, hand2TwoPairs);
		//then
		Assert.assertTrue(result);
	}
	@Test
	public void shouldMakeTwoHandsFromStringCardLine() {
		//given
		String cardsLine = "9D 6C 9S 8H 8C TD 9C 9S 7D 7C";
		//when
		List<Hand> handsList =  Poker.makeTwoHands(cardsLine);
		Hand resultHand1 = handsList.get(0);
		Hand resultHand2 = handsList.get(1);
		//then
		HandAssert.assertThat(resultHand1).hasCard1(new Card("9","D")).hasCard2(new Card("6","C")).hasCard3(new Card("9","S")).hasCard4(new Card("8","H")).hasCard5(new Card("8","C"));
		HandAssert.assertThat(resultHand2).hasCard1(new Card("T","D")).hasCard2(new Card("9","C")).hasCard3(new Card("9","S")).hasCard4(new Card("7","D")).hasCard5(new Card("7","C"));
	} 
	@Test
	public void shouldMakeTwoHandsAndPlayer1Win() {
		//given
		String cardsLine = "9D 6C 9S 8H 8C TD 9C 9S 7D 7C";
		List<Hand> handsList =  Poker.makeTwoHands(cardsLine);
		
		Hand hand1 = handsList.get(0);
		Hand hand2 = handsList.get(1);
		//when
		boolean result =  Poker.isPlayer1Win(hand1, hand2);
		//then
		Assert.assertTrue(result);
	}
	
	@Test
	public void shouldPlayer1WinNineTimes() throws IOException {
		//given
		//when
		int expectedResult =  Poker.countPlayer1WinsTest();
		//then
		Assert.assertEquals(expectedResult, 9);
	}
	
	@Test
	public void shouldPlayer1Win602Times() throws IOException {
		//given
		//when
		int expectedResult =  Poker.countPlayer1Wins();
		//then
		Assert.assertEquals(expectedResult, 376);
	}

}
