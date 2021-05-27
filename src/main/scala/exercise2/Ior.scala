package exercise2

/*
do not use VAR!!!
Ior[E, A] = E and A, E or A
map and flatmap works on the right side (A)
TODO: Implement the Ior monad.

Monads: Effekte -> Option[T], Future[T]
 */

// TRAIT (INTERFACE?)
sealed trait Ior[A] {
  def flatMap[B](f: A => Ior[B]) : Ior[B] = {
    this match {
      case Left(elem) => Ior.left(elem)
      case Right(elem) => f(elem)
      case Both(left, elem) =>
        f(elem) match {
          case Left(elem) => Ior.left(elem)
          case Right(elem) => Ior.both(left, elem)
          case Both(left, elem) => Ior.both(left, elem)
        }
    }
  }
  def map [B](f: A => B): Ior[B] = {
    this match {
      case Left(elem) => Ior.left(elem)
      case Right(elem) => Ior.right(f(elem))
      case Both(left, elem) => Ior.both(left, f(elem))
    }
  }
}

// CASE CLASSES
case class Left[A] (elem: Throwable) extends Ior[A] {
  // 'this' works here (as self-reference), because classes can be instantiated.
}
case class Right[A] (elem: A) extends Ior[A] {

}
case class Both[A] (left: Throwable, elem: A) extends Ior[A] {
}

// COMPANION OBJECT
object Ior {

  // IOR OBJECT FUNCTIONS
  def right[A](elem: A): Right[A] = {
    Right(elem)
  }
  def left[A](elem: Throwable): Left[A] = {
    Left(elem)
  }
  def both[A](left: Throwable, elem: A): Both[A] = {
    Both(left, elem)
  }

  def unit[A](elem: A): Ior[A] = {
    return new Ior[A] {}
  }

}

object Demo {
  def main(args: Array[String]): Unit = {
    val a = Ior.right(2) // Right(2)
    val b = a.map(x => x * 4) // Right(8)

    val c = b.flatMap(_ => Ior.right("a string")) // Right("a string")

    val d = c.flatMap(_ => Ior.left[String](new RuntimeException("a grave error"))) //Left(java.lang.RuntimeException: a grave error)

    val e = d.map(x => x + "something") //Left(java.lang.RuntimeException: a grave error)

    val both = Ior.both(new RuntimeException("not fatal"), 21) //Both(java.lang.RuntimeException: not fatal,21)
    val both1 = both.map(x => x * 2) //Both(java.lang.RuntimeException: not fatal,42)
    val both2 = both.flatMap(_ => Ior.left[Int](new RuntimeException("fatal error"))) //Left(java.lang.RuntimeException: fatal error)
    val both3 = both.flatMap(_ => Ior.right(480)) //Both(java.lang.RuntimeException: not fatal,480)
    val both4 = both.flatMap(x => Ior.both(new RuntimeException("another not fatal"), x * 3)) //Both(java.lang.RuntimeException: another not fatal,63)
  }
}
