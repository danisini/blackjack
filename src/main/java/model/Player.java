package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {
    private Boolean isBusted;
    private Double balance;
    private List<Card> splitHand;

    public Player(Double balance) {
        super();
        this.balance = balance;
        isBusted = false;
        splitHand = new ArrayList<>();
    }
}
