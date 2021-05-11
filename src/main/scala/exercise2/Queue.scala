package exercise2

import scala.util.{Failure, Success, Try}

trait QueueLike[T] {
  def enqueue(elem: T): QueueLike[T]
  def dequeue: Try[QueueLike[T]]
  def front: Option[T]
  def isEmpty: Boolean
}

case class Queue[T](in: StackLike[T] = EmptyStack[T](), out: StackLike[T] = EmptyStack[T]()) extends QueueLike[T] {
  override def enqueue(elem: T): QueueLike[T] = {
    Queue[T](this.in.push(elem), this.out)
  }

  override def dequeue: Try[QueueLike[T]] = {
    this.out match {
      case _: EmptyStack[T] => // Output stack consumed
        val inReversedPopped = this.in.reverse.pop()
        inReversedPopped match {
          case _: Failure[StackLike[T]] => Failure[QueueLike[T]](new RuntimeException("Queue is empty!")) // Nothing to pop from reversed input stack => Queue empty
          case Success(content) => Success[QueueLike[T]](Queue(EmptyStack[T](), content)) // Something to pop from reversed input stack => Queue not empty yet
        }
      case _: Stack[T] =>// Best case, output stack not consumed yet
        Success[QueueLike[T]](Queue(this.in, this.out.pop().get)) // this.out.pop().get can't throw an exception, because this.out is definitely a Stack
    }
  }

  override def front: Option[T] = {
    this.out match {
      case _: EmptyStack[T] => // Output stack consumed
        in.reverse.top()
      case _: Stack[T] =>// Best case, output stack not consumed yet
        this.out.top()
    }
  }

  override def isEmpty: Boolean = {
    this.out.isEmpty & this.in.isEmpty
  }
}
