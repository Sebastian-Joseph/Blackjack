

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args){

        System.out.println("Welcome to Blackjack! This is where you will get robbed!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerCards = new Deck();
        double playerChips = 25;
        Deck dealerCards = new Deck();

        Scanner userInput = new Scanner(System.in);

        while(playerChips>0){
            System.out.println("You have " +  playerChips + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            boolean endRound = false;
            if(playerBet > playerChips){
                System.out.println("You cannot bet more than you have.");
                break;
            }

            System.out.println("Dealing...");
            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);

            while(true)
            {
                System.out.println("Your Hand:" + playerCards.toString());

                System.out.println("Your hand is currently valued at: " + playerCards.cardsValue());

                System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " and [hidden]");

                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = userInput.nextInt();
                if(response == 1){
                    playerCards.draw(playingDeck);
                    System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize()-1).toString());
                    if(playerCards.cardsValue() > 21){
                        System.out.println("Bust. Currently valued at: " + playerCards.cardsValue());
                        playerChips -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if(response == 2){
                    break;
                }

            }

            System.out.println("Dealer Cards:" + dealerCards.toString());
            if((dealerCards.cardsValue() > playerCards.cardsValue())&&endRound == false){
                System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
                playerChips -= playerBet;
                endRound = true;
            }
            while((dealerCards.cardsValue() < 17) && endRound == false){
                dealerCards.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize()-1).toString());
            }
            System.out.println("Dealers hand value: " + dealerCards.cardsValue());
            if((dealerCards.cardsValue()>21)&& endRound == false){
                System.out.println("Dealer Busts. You win!");
                playerChips += playerBet;
                endRound = true;
            }
            if((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false){
                System.out.println("Tied. Everyone gets their money back!");
                endRound = true;
            }
            if((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false){
                System.out.println("You win the hand.");
                playerChips += playerBet;
                endRound = true;
            }
            else if(endRound == false) //dealer wins
            {
                System.out.println("Dealer wins.");
                playerChips -= playerBet;
            }

            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);
            System.out.println("End of Hand.");

        }
        System.out.println("Game over! You're broke! :3 ");

        userInput.close();

    }


}
