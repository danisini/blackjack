package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {
    private Double balance;
    private List<Card> splitHand;

    public Player(Double balance) {
        super();
        this.balance = balance;
        splitHand = new ArrayList<>();
    }
}
