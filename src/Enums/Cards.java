package Enums;

import Objects.Card;
import Objects.Character;
import Objects.Characters.*;
import Objects.Room;
import Objects.Rooms.*;
import Objects.Weapons.*;

public enum Cards {

    CHARACTERS (new Character[] {new Yellow(), new Brown(), new Black(), new Orange(), new Blue(), new Red()}),
    WEAPONS (new Card[] {new Arrow(), new Axe(), new Candlestick(), new Knife(), new Poison(), new Revolver()}),
    ROOMS (new Room[] {new Ballroom(), new Conservatory(), new BilliardRoom(), new Library(), new Laboratory(), new Lounge(), new Pool(), new DiningRoom(), new Kitchen()});

    private Card[] cards;

    Cards (Card[] cards) {this.cards = cards;}

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
