import org.scalatest.flatspec.AnyFlatSpec

class ArraysTest extends AnyFlatSpec{

  val testArr: Array[Int] = Array(-2, 4, 7, -3)

  "max" should "return the biggest number" in{
    assert(Arrays.max(testArr) === 7)
  }

  "min" should "return the smallest number" in{
    assert(Arrays.min(testArr) === -3)
  }

  "sum" should "return the sum of the array" in{
    assert(Arrays.sum(testArr) === 6)
  }



}
