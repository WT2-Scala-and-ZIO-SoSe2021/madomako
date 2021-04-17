import org.scalatest.flatspec.AnyFlatSpec

class E1T2Test extends AnyFlatSpec {

  "parseAll" should "return correct mapping on all possible values" in {
    assert(E1T2.parseAll(Array("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"))
      .sameElements(Array(2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11)))
  }

  "values" should "return correct mapping on all possible values" in {
    assert(E1T2.values(2).sameElements(Array(2)))
    assert(E1T2.values(3).sameElements(Array(3)))
    assert(E1T2.values(4).sameElements(Array(4)))
    assert(E1T2.values(5).sameElements(Array(5)))
    assert(E1T2.values(6).sameElements(Array(6)))
    assert(E1T2.values(7).sameElements(Array(7)))
    assert(E1T2.values(8).sameElements(Array(8)))
    assert(E1T2.values(9).sameElements(Array(9)))
    assert(E1T2.values(10).sameElements(Array(10)))
    assert(E1T2.values(11).sameElements(Array(1, 11)))
  }

  "determineHandValue" should "return 12 for an ace" in {
    val partial = E1T2.determineHandValue(x => E1T1.sum(x)) _ // "check" for curried function
    assert(partial(Array(11)) == 12)
  }

  "isBust" should "return false for 21" in {
    assert(E1T2.isBust(21) == false)
  }

  "isBust" should "return true for 22" in {
    assert(E1T2.isBust(22) == true)
  }

  "optimisticF" should "return 13 for [2, 11]" in {
    assert(E1T2.optimisticF(Array(2, 11)) == 13)
  }

  "pessimisticF" should "return 3 for [2, 11]" in {
    assert(E1T2.pessimisticF(Array(2, 11)) == 3)
  }

  "determineBestHandValue" should "return 13 for [2, 11]" in {
    assert(E1T2.determineBestHandValue(Array(2, 11)) == 13) // this means, optimisticF was used
  }

  "determineBestHandValue" should "return 12 for [4, 7, 11]" in {
    assert(E1T2.determineBestHandValue(Array(4, 7, 11)) == 12) // this means, pessimisticF was used
  }
}
