import org.scalatest.flatspec.AnyFlatSpec

class Vingt_Et_UnTest extends AnyFlatSpec{


  "parse" should "return the correct card value" in {
    assert(Vingt_Et_Un.parse("2") === 2)
    assert(Vingt_Et_Un.parse("3") === 3)
    assert(Vingt_Et_Un.parse("4") === 4)
    assert(Vingt_Et_Un.parse("5") === 5)
    assert(Vingt_Et_Un.parse("6") === 6)
    assert(Vingt_Et_Un.parse("7") === 7)
    assert(Vingt_Et_Un.parse("8") === 8)
    assert(Vingt_Et_Un.parse("9") === 9)
    assert(Vingt_Et_Un.parse("10") === 10)
    assert(Vingt_Et_Un.parse(Vingt_Et_Un.Jack) === 10)
    assert(Vingt_Et_Un.parse(Vingt_Et_Un.Queen) === 10)
    assert(Vingt_Et_Un.parse(Vingt_Et_Un.King) === 10)
    assert(Vingt_Et_Un.parse(Vingt_Et_Un.Ace) === 11)
  }

  "isBust" should "return true if the value is 21" in{
    assert(Vingt_Et_Un.isBust(22) === true)
    assert(Vingt_Et_Un.isBust(21) === false)
    assert(Vingt_Et_Un.isBust(10) === false)
  }

  "optimisticF" should "find the max value of the hand" in {
    // Arrange
    val hand: Array[Int] = Array(5, 9, 11, 3)
    assert(Vingt_Et_Un.optimisticF(hand) === 28)
  }

  "pessimisticF" should "find the min value of the hand" in {
    // Arrange
    val hand: Array[Int] = Array(5, 9, 11, 3)
    assert(Vingt_Et_Un.pessimisticF(hand) === 18)
  }


}
