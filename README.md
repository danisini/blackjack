# Blackjack

Проектът представлява сървърната част на класическия вариант на играта блекджек. 
Състои се от 5 ендпойнта -> /start, /split, /double, /hit, /stand. Заради подаването на стейт на играта е направено 
възможно имплементацията на продължаване, откъдето сме стигнали последно, когато сме затворили играта, ако тя е 
затворила внезапно.

# Как да тестваме:
mvn clean install на проекта
java -jar target/blackjack-1.0.0-SNAPSHOT.jar

по този начин пускаме сървъра, след което пращаме следните заявки (Напр. през postman):
# Пример за /start:
```json
{
  "state": {
    "hasPlayerWon": false,
    "hasDealerWon": false,
    "isRoundOver": false,
    "isStakeDoubled": false,
    "stake": 0.0,
    "additionalStake": 0.0,
    "winAmount": 0.0,
    "balance": 100.0,
    "playerHand": [],
    "playerSplitHand": [],
    "dealerHand": [],
    "possibleActions": [
      "/start"
    ],
    "cardsDealt":[]
  },
  "balance": 100.0,
  "stake": 1.0
}
```
# Пример за /hit
```json
{
  "state": {
    "hasPlayerWon": false,
    "hasDealerWon": false,
    "stake": 1.0,
    "additionalStake": 0.0,
    "winAmount": 0.0,
    "balance": 100.0,
    "possibleActions": [
      "/double",
      "/stand",
      "/hit"
    ],
    "playerHand": [
      {
        "suit": "HEARTS",
        "rank": "TWO"
      },
      {
        "suit": "CLUBS",
        "rank": "THREE"
      }
    ],
    "playerSplitHand": [],
    "dealerHand": [
      {
        "suit": "CLUBS",
        "rank": "KING"
      }
    ],
    "cardsDealt": [
      {
        "suit": "HEARTS",
        "rank": "TWO"
      },
      {
        "suit": "CLUBS",
        "rank": "THREE"
      },
      {
        "suit": "CLUBS",
        "rank": "KING"
      }
    ],
    "stakeDoubled": false,
    "roundOver": false
  },
  "handNumber": 0
}
```
# Пример за /stand
```json
{
  "state": {
    "hasPlayerWon": false,
    "hasDealerWon": false,
    "stake": 1.0,
    "additionalStake": 0.0,
    "winAmount": 0.0,
    "balance": 100.0,
    "possibleActions": [
      "/double",
      "/stand",
      "/hit"
    ],
    "playerHand": [
      {
        "suit": "HEARTS",
        "rank": "ACE"
      },
      {
        "suit": "CLUBS",
        "rank": "FIVE"
      },
      {
        "suit": "HEARTS",
        "rank": "TWO"
      }
    ],
    "playerSplitHand": [],
    "dealerHand": [
      {
        "suit": "CLUBS",
        "rank": "KING"
      }
    ],
    "cardsDealt": [
      {
        "suit": "HEARTS",
        "rank": "ACE"
      },
      {
        "suit": "CLUBS",
        "rank": "FIVE"
      },
      {
        "suit": "CLUBS",
        "rank": "KING"
      },
      {
        "suit": "HEARTS",
        "rank": "TWO"
      }
    ],
    "stakeDoubled": false,
    "roundOver": false
  }
}
```
# Пример за /double
```json
{
  "state": {
    "hasPlayerWon": false,
    "hasDealerWon": false,
    "stake": 1.0,
    "additionalStake": 0.0,
    "winAmount": 0.0,
    "balance": 100.0,
    "possibleActions": [
      "/double",
      "/stand",
      "/hit"
    ],
    "playerHand": [
      {
        "suit": "HEARTS",
        "rank": "TWO"
      },
      {
        "suit": "CLUBS",
        "rank": "THREE"
      },
      {
        "suit": "HEARTS",
        "rank": "TWO"
      }
    ],
    "playerSplitHand": [],
    "dealerHand": [
      {
        "suit": "CLUBS",
        "rank": "KING"
      }
    ],
    "cardsDealt": [
      {
        "suit": "HEARTS",
        "rank": "TWO"
      },
      {
        "suit": "CLUBS",
        "rank": "THREE"
      },
      {
        "suit": "CLUBS",
        "rank": "KING"
      },
      {
        "suit": "HEARTS",
        "rank": "TWO"
      }
    ],
    "stakeDoubled": false,
    "roundOver": false
  }
}
```

# Пример за /split
```json
{
  "state": {
    "hasPlayerWon": false,
    "hasDealerWon": false,
    "stake": 1.0,
    "additionalStake": 0.0,
    "winAmount": 0.0,
    "balance": 100.0,
    "possibleActions": [
      "/double",
      "/stand",
      "/hit",
      "/split"
    ],
    "playerHand": [
      {
        "suit": "HEARTS",
        "rank": "SIX"
      },
      {
        "suit": "CLUBS",
        "rank": "SIX"
      }
    ],
    "playerSplitHand": [],
    "dealerHand": [
      {
        "suit": "HEARTS",
        "rank": "FOUR"
      }
    ],
    "cardsDealt": [
      {
        "suit": "HEARTS",
        "rank": "SIX"
      },
      {
        "suit": "CLUBS",
        "rank": "SIX"
      },
      {
        "suit": "HEARTS",
        "rank": "FOUR"
      }
    ],
    "stakeDoubled": false,
    "roundOver": false
  },
  "additionalStake":2.0
}
```