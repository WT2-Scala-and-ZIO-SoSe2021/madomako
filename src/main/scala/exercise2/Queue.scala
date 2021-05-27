package exercise2

import scala.util.{Failure, Success, Try}

trait QueueLike[T] {
  def enqueue(elem: T): QueueLike[T]
  def dequeue(): Try[QueueLike[T]]
  def front(): Option[T]
  def isEmpty: Boolean
}
// val q = Queue(stack01, stack02)
// q.enqueue(4)
case class Queue[T](in: StackLike[T] = EmptyStack[T](), out: StackLike[T] = EmptyStack[T]()) extends QueueLike[T] {
  override def enqueue(elem: T): QueueLike[T] = {
    Queue[T](this.in.push(elem), this.out)
  }

  override def dequeue(): Try[QueueLike[T]] = {
    this.out match {
      case _: EmptyStack[T] =>
        val inReversePopped = this.in.reverse.pop()
        inReversePopped match {
          case _: Failure[StackLike[T]] => Failure[QueueLike[T]](new RuntimeException("Queue is empty!"))
          case Success(content) => Success[QueueLike[T]](Queue(EmptyStack[T](), content))
        }
      case _: Stack[T] => Success[QueueLike[T]]
    }
  }

  override def front(): Option[T] = {
    this.out match {
      case _: EmptyStack[T] => in.reverse.top()
      case _: Stack[T] => out.top()
    }
  }

  override def isEmpty: Boolean = {
    if (this.in.isEmpty && this.out.isEmpty) {
      true
    } else {
      false
    }
  }
}
