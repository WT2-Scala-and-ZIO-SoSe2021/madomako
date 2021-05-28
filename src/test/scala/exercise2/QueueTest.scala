package exercise2

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class QueueTest extends AnyFlatSpec {

  "enqueue on empty Queue" should "return a Queue with the element" in {
    assert(Queue().enqueue(2) == Queue(Stack(2, EmptyStack()), EmptyStack()))
  }

  "enqueue on filled Queue" should "return a Queue with all elements" in {
    assert(Queue().enqueue(2).enqueue(3) == Queue(Stack(3, Stack(2, EmptyStack())), EmptyStack()))
  }

  "dequeue on empty Queue" should "return a Failure" in {
    assert(Queue().dequeue.getClass() == Failure[QueueLike[Int]](new RuntimeException()).getClass())
      val msg = Queue().dequeue match {
        case Failure(e) => e.getMessage()
        case _ => ""
      }
      assert(msg == "Queue is empty!")
  }

  "dequeue on filled Queue with filled output stack" should "return a Success with the new Queue" in {
    val q = Queue(EmptyStack(), Stack("first in", Stack("second in", EmptyStack())))
    assert(q.dequeue == Success(Queue(EmptyStack(), Stack("second in", EmptyStack()))))
  }

  "dequeue on filled Queue with empty output stack" should "return a Success with the new Queue with reversed input stack as output stack" in {
    val q = Queue(Stack("second in", Stack("first in", EmptyStack())), EmptyStack())
    assert(q.dequeue == Success(Queue(EmptyStack(), Stack("second in", EmptyStack()))))
  }

  "front on empty Queue" should "return None" in {
    assert(Queue().front == None)
  }

  "front on filled Queue with filled output stack" should "return first enqueued element" in {
    val q = Queue(EmptyStack(), Stack("first in", Stack("second in", EmptyStack())))
    assert(q.front == Some("first in"))
  }

  "front on filled Queue with empty output stack" should "return first enqueued element" in {
    val q = Queue(Stack("second in", Stack("first in", EmptyStack())), EmptyStack())
    assert(q.front == Some("first in"))
  }

  "isEmpty on empty Queue" should "return true" in {
    assert(Queue().isEmpty == true)
  }

  "isEmpty on filled Queue with filled output stack" should "return false" in {
    val q = Queue(EmptyStack(), Stack("first in", Stack("second in", EmptyStack())))
    assert(q.isEmpty == false)
  }

  "isEmpty on filled Queue with empty output stack" should "return false" in {
    val q = Queue(Stack("second in", Stack("first in", EmptyStack())), EmptyStack())
    assert(q.isEmpty == false)
  }
}