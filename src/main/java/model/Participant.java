package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    List<Card> currentHand;

    public Participant() {
        currentHand = new ArrayList<>();
    }

}
