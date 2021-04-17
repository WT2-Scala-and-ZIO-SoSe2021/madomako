import org.scalatest.flatspec.AnyFlatSpec

class E1T1Test extends AnyFlatSpec {

  "max" should "return max value of filled array" in {
    assert(E1T1.max(Array(-2, 1, 2, 3, 4)) == 4)
  }

  "max" should "throw IllegalArgumentException upon empty array" in {
    intercept[IllegalArgumentException](E1T1.max(Array[Int]()))
  }

  "max" should "throw IllegalArgumentException upon null" in {
    intercept[IllegalArgumentException](E1T1.max(null))
  }

  "min" should "return min value of filled array" in {
    assert(E1T1.min(Array(-2, 1, 2, 3, 4)) == -2)
  }

  "min" should "throw IllegalArgumentException upon empty array" in {
    intercept[IllegalArgumentException](E1T1.min(Array[Int]()))
  }

  "min" should "throw IllegalArgumentException upon null" in {
    intercept[IllegalArgumentException](E1T1.min(null))
  }

  "sum" should "return sum value of filled array" in {
    assert(E1T1.sum(Array(-2, 1, 2, 3, 4)) == 8)
  }

  "sum" should "throw IllegalArgumentException upon empty array" in {
    intercept[IllegalArgumentException](E1T1.sum(Array[Int]()))
  }

  "sum" should "throw IllegalArgumentException upon null" in {
    intercept[IllegalArgumentException](E1T1.sum(null))
  }
}
