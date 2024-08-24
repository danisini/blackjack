# blackjack

Проектът представлява сървърната част на класическия вариант на играта блекджек. 
Състои се от 5 ендпойнта -> /start, /split, /double, /hit, /stand. Заради подаването на стейт на играта е направено 
възможно имплементацията на продължаване, откъдето сме стигнали последно, когато сме затворили играта, ако тя е 
затворила внезапно.

Как да тестваме:
mvn clean install на проекта
java -jar target/blackjack-1.0.0-SNAPSHOT.jar

по този начин пускаме сървъра, след което пращаме следните заявки (Напр. през postman):
#Пример за start:
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
"possibleActions": ["/start"]
},
"balance": 100.0,
"stake": 1.0
}

#Example for HIT

{
"state": {
"hasPlayerWon": false,
"hasDealerWon": false,
"isRoundOver": false,
"isStakeDoubled": false,
"stake": 1.0,
"additionalStake": 0.0,
"winAmount": 0.0,
"balance": 100.0,
"playerHand": [
{
"suit":"HEARTS",
"rank":"JACK"
},
{
"suit":"SPADES",
"rank":"JACK"
}
],
"playerSplitHand": [],
"dealerHand": [
{
"suit":"SPADES",
"rank":"JACK"
}
],
"possibleActions": ["/hit", "/double", "/stand"]
}
}

#Пример за /stand

{
"state": {
"hasPlayerWon": false,
"hasDealerWon": false,
"isRoundOver": false,
"isStakeDoubled": false,
"stake": 1.0,
"additionalStake": 0.0,
"winAmount": 0.0,
"balance": 100.0,
"playerHand": [
{
"suit":"HEARTS",
"rank":"JACK"
},
{
"suit":"SPADES",
"rank":"JACK"
}
],
"playerSplitHand": [],
"dealerHand": [
{
"suit":"CLUBS",
"rank":"JACK"
}
],
"possibleActions": ["/hit", "/double", "/stand"]
}
}

#Пример за /double
{
"state": {
"hasPlayerWon": false,
"hasDealerWon": false,
"isRoundOver": false,
"isStakeDoubled": false,
"stake": 1.0,
"additionalStake": 0.0,
"winAmount": 0.0,
"balance": 100.0,
"playerHand": [
{
"suit":"HEARTS",
"rank":"JACK"
},
{
"suit":"SPADES",
"rank":"JACK"
}
],
"playerSplitHand": [],
"dealerHand": [
{
"suit":"CLUBS",
"rank":"JACK"
}
],
"possibleActions": ["/hit", "/double", "/stand"]
}
}


#пример за /split
{
"state": {
"hasPlayerWon": false,
"hasDealerWon": false,
"isRoundOver": false,
"isStakeDoubled": false,
"stake": 1.0,
"additionalStake": 0.0,
"winAmount": 0.0,
"balance": 100.0,
"playerHand": [
{
"suit":"HEARTS",
"rank":"JACK"
},
{
"suit":"SPADES",
"rank":"JACK"
}
],
"playerSplitHand": [],
"dealerHand": [
{
"suit":"CLUBS",
"rank":"JACK"
}
],
"possibleActions": ["/hit", "/double", "/stand", "/split"]
},
"additionalStake":2.0
}