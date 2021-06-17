package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Success

class StackTest extends AnyFlatSpec{
  "push on Stack" should "return a Stack with all values" in {
    val s = EmptyStack().push(2).push(3)
    assert(s == Stack(3, Stack(2, EmptyStack())))
  }

  "pop on Stack" should "return Success with new Stack" in {
    val s = Stack(3, Stack(2, EmptyStack()))
    assert(s.pop() == Success(Stack(2, EmptyStack())))
  }
}
