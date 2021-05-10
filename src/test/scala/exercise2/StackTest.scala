package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class StackTest extends AnyFlatSpec {

  // ### EMPTY STACK

  "push on EmptyStack" should "return a Stack with that value" in {
    assert(EmptyStack().push(2) == Stack(2))
  }

  "pop on EmptyStack" should "return Failure with RuntimeException" in {
    assert(EmptyStack[Int]().pop().getClass() == Failure[StackLike[Int]](new RuntimeException()).getClass())
    val msg = EmptyStack[Int]().pop() match {
      case Failure(e) => e.getMessage()
      case _ => ""
    }
    assert(msg == "Stack is empty!")
  }

  "top on EmptyStack" should "return None" in {
    assert(EmptyStack[Int]().top() == None)
  }

  "isEmpty on EmptyStack" should "return true" in {
    assert(EmptyStack[Int]().isEmpty == true)
  }

  "reverse on EmptyStack" should "return the EmptyStack" in {
    assert(EmptyStack[Int]().reverse == EmptyStack[Int]())
  }


  // ### STACK

  "push on Stack" should "return a Stack with all values" in {
    val s = EmptyStack().push(2).push(3)
    assert(s == Stack(3, Stack(2, EmptyStack())))
  }

  "pop on Stack" should "return Success with new Stack" in {
    val s = Stack(3, Stack(2, EmptyStack()))
    assert(s.pop() == Success(Stack(2, EmptyStack())))
  }

  "top on Stack" should "return top element" in {
    val s = Stack(3, Stack(2, EmptyStack()))
    assert(s.top() == Some(3))
  }

  "isEmpty on Stack" should "return false" in {
    assert(Stack(2, EmptyStack()).isEmpty == false)
  }

  "reverse on Stack" should "return the reversed Stack" in {
    val s = Stack(4, Stack(3, Stack(2, EmptyStack())))
    assert(s.reverse == Stack(2, Stack(3, Stack(4, EmptyStack()))))
  }
}