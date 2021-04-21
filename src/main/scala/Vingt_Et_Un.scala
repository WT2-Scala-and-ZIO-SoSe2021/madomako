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
      case value
        if(value >=2 && value <=10)
      => Array(value)
      case 11 => Array(1, 11)
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
