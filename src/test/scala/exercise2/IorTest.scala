package exercise2

import org.scalatest.flatspec.AnyFlatSpec

class IorTest extends AnyFlatSpec{

  "Ior.right" should "return a Right" in {
    assert(Ior.right[Int](2) == Right[Int](2))
  }

  "Ior.left" should "return a Left" in {
    val re = new RuntimeException()
    assert(Ior.left(re) == Left(re))
  }

  "Ior.both" should "return a Both" in {
    val re = new RuntimeException()
    assert(Ior.both[Int](re, 2) == Both[Int](re, 2))
  }

  "Mapping a Right" should "return a Right with new elem value" in {
    assert(Ior.right[Int](2).map(x => x*2) == Ior.right[Int](4))
  }

  "Mapping a Left" should "return an identical Left" in {
    val left = Ior.left[Int](new RuntimeException())
    assert(left.map(x => x*2) == left)
  }

  "Mapping a Both" should "return a Both with it's old left and the new elem value" in {
    val re = new RuntimeException()
    assert(Ior.both[Int](re, 2).map(x => x*2) == Ior.both[Int](re, 4))
  }

  "FlatMapping a Right with a Right" should "return a Right with the new elem value" in {
    assert(Ior.right[Int](2).flatMap(x => Ior.right[Int](x*2)) == Ior.right[Int](4))
  }

  "FlatMapping a Right with a Left" should "return a Left" in {
    val re = new RuntimeException()
    assert(Ior.right[Int](2).flatMap(_ => Ior.left[Int](re)) == Ior.left[Int](re))
  }

  "FlatMapping a Right with a Both" should "return a Both with the new elem value" in {
    val re = new RuntimeException()
    assert(Ior.right[Int](2).flatMap(x => Ior.both[Int](re, x*2)) == Ior.both[Int](re, 4))
  }

  "FlatMapping a Left with a Right" should "return a Left" in {
    val re = new RuntimeException()
    assert(Ior.left[Int](re).flatMap(x => Ior.right[Int](x*2)) == Ior.left[Int](re))
  }

  // TODO is it right to return the old left value?
  "FlatMapping a Left with a Left" should "return a Left with the old left value" in {
    val re = new RuntimeException()
    val re2 = new RuntimeException()
    assert(Ior.left[Int](re).flatMap(x => Ior.left[Int](re2)) == Ior.left[Int](re))
  }

  // TODO is it right to return the old left value?
  "FlatMapping a Left with a Both" should "return a Left with the old left value" in {
    val re = new RuntimeException()
    val re2 = new RuntimeException()
    assert(Ior.left[Int](re).flatMap(x => Ior.both[Int](re2, x*2)) == Ior.left[Int](re))
  }

  "FlatMapping a Both with a Right" should "return a Both with the new elem value" in {
    val re = new RuntimeException()
    assert(Ior.both[Int](re, 2).flatMap(x => Ior.right[Int](x*2)) == Ior.both[Int](re, 4))
  }

  "FlatMapping a Both with a Left" should "return a Left with the new left value" in {
    val re = new RuntimeException()
    val re2 = new RuntimeException()
    assert(Ior.both[Int](re, 2).flatMap(x => Ior.left[Int](re2)) == Ior.left[Int](re2))
  }

  "FlatMapping a Both with a Both" should "return a Both with the new left and elem value" in {
    val re = new RuntimeException()
    val re2 = new RuntimeException()
    assert(Ior.both[Int](re, 2).flatMap(x => Ior.both[Int](re2, x*2)) == Ior.both[Int](re2, 4))
  }

  // TODO Unit function test
}
