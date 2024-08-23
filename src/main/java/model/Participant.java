package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    List<Card> currentHand;
    Boolean isBusted ;

    public Participant() {
        currentHand = new ArrayList<>();
        isBusted = false;
    }

}
