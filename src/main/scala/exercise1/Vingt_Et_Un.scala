package exercise1

object Vingt_Et_Un {

  val Jack = "J"
  val Queen = "Q"
  val King = "K"
  val Ace = "A"


  def parse(card: String): Int = {
    val cardAsInteger = toInt(card)
    if(cardAsInteger >= 2 && cardAsInteger <=10 ){
      cardAsInteger
    }else{
      card match{
        case Jack | Queen | King => 10
        case Ace => 11
      }
    }
  }

  def parseAll(allCards: Array[String]): Array[Int] = {
    allCards map parse
  }

  def values(card: Int): Array[Int] = {
    card match{
      case value if(value >=2 && value <=10) => Array(value)
      case 11 => Array(1, 11)
    }

  }

  def determineHandValue(strategy: Array[Int] => Int)(hand: Array[Int]): Int = {
    val vals = hand.map(x => values(x))
    val strategied = vals.map(x => strategy(x))
    Arrays.sum(strategied)
  }

  def isBust(handValue: Int): Boolean = {
    if (handValue > 21){
      true
    }else{
      false
    }
  }

  def optimisticF(hand: Array[Int]): Int = {
    determineHandValue(Arrays.max)(hand)
  }

  def pessimisticF(hand: Array[Int]): Int = {
    determineHandValue(Arrays.min)(hand)
  }

  def determineBestHandValue(hand: Array[Int]): Int = {
    val optimisticValue = optimisticF(hand)
    if (isBust(optimisticValue)) {
      pessimisticF(hand)
    } else {
      optimisticValue
    }
  }

  def toInt(s: String): Int = {
    try {
      s.toInt
    } catch {
      case e: Exception => 0
    }
  }

}
