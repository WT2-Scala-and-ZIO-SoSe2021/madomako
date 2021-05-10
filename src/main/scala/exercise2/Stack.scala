package exercise2

import scala.util.{Failure, Success, Try}

trait StackLike[T] {
  def push(elem: T): StackLike[T]
  def pop(): Try[StackLike[T]]
  def top(): Option[T]
  def isEmpty: Boolean
  def reverse: StackLike[T]
}

case class Stack[T](elem: T, tail: StackLike[T] = EmptyStack[T]()) extends StackLike[T] {

  override def push(elem: T): StackLike[T] = {
    Stack(elem, this)
  }

  override def pop(): Try[StackLike[T]] = {
    Success(this.tail)
  }

  override def top(): Option[T] = {
    Some(this.elem)
  }

  override def isEmpty: Boolean = {
    false
  }

  def reverseHelper(oldStackRemainder: StackLike[T], currentResultStack: StackLike[T]): StackLike[T] = {
    val top = oldStackRemainder.top()
    val popped = oldStackRemainder.pop()
    popped match {
      case Failure(_) => currentResultStack // Stack is empty
      case Success(poppedStack) => reverseHelper(poppedStack, currentResultStack.push(top.get)) // Stack is not empty yet
    }
  }

  override def reverse: StackLike[T] = {
    reverseHelper(this, EmptyStack[T]())
  }
}

case class EmptyStack[T]() extends StackLike[T] {
  override def push(elem: T): StackLike[T] = {
    Stack(elem, this)
  }

  override def pop(): Try[StackLike[T]] = {
    Failure(new RuntimeException("Stack is empty!"))
  }

  override def top(): Option[T] = {
    None
  }

  override def isEmpty: Boolean = {
    true
  }

  override def reverse: StackLike[T] = {
    this
  }
}