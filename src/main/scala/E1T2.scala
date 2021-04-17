object E1T2 {
  def parse(s:String): Int = {
    s match {
      case "2" => 2
      case "3" => 3
      case "4" => 4
      case "5" => 5
      case "6" => 6
      case "7" => 7
      case "8" => 8
      case "9" => 9
      case "10" => 10
      case "J" => 10
      case "Q" => 10
      case "K" => 10
      case "A" => 11
    }
  }

  def parseAll(arr: Array[String]): Array[Int] = {
    arr.map((s) => parse(s))
  }

  def values(c: Int): Array[Int] = {
    if (c == 11) Array(1, 11) else Array(c)
  }

  def determineHandValue(strategyFunction: Array[Int] => Int)(hand: Array[Int]): Int = {
    val vals = hand.map(x => values(x))
    val strategied = vals.map(x => strategyFunction(x))
    E1T1.sum(strategied)
  }

  def optimisticF(hand: Array[Int]): Int = {
    determineHandValue(E1T1.max) (hand)
  }

  def pessimisticF(hand: Array[Int]): Int = {
    determineHandValue(E1T1.min) (hand)
  }

  def isBust(handValue: Int): Boolean = {
    if (handValue > 21) true else false
  }

  def determineBestHandValue(hand: Array[Int]): Int = {
    val handValue = optimisticF(hand)
    if (!isBust(handValue)) handValue else pessimisticF(hand)
  }
}
