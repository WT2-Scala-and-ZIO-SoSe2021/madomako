package exercise2

sealed trait Ior[A] {
  def flatMap[B](f: A => Ior[B]): Ior[B] = {
    this match {
      case Left(elem) => Ior.left(elem)
      case Right(elem) => f(elem)
      case Both(left, elem) =>
        f(elem) match {
          case Left(elem) => Ior.left(elem) // convert the both to a left
          case Right(elem) => Ior.both(left, elem) // keep the old left and do calculation on elem
          case Both(left, elem) => Ior.both(left, elem) // keep the new left and do calculation on elem
        }
    }
  }

  def map[B](f: A => B): Ior[B] = {
    this match {
      case Left(elem) => Ior.left(elem)
      case Right(elem) => Ior.right(f(elem))
      case Both(left, elem) => Ior.both(left, f(elem))
    }
  }
}

object Ior {
  def right[A](elem:A): Right[A] = {
    Right(elem)
  }

  def left[A](elem:Throwable): Left[A] = {
    Left(elem)
  }

  def both[A](left:Throwable, elem:A): Both[A] = {
    Both(left, elem)
  }

  def unit[A](elem: A): Ior[A] = {
    Ior.right(elem)
  }
}

case class Left[A](elem:Throwable) extends Ior[A]

case class Right[A](elem:A) extends Ior[A]

case class Both[A](left:Throwable, elem:A) extends Ior[A]