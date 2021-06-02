package exercise3
import temperature._

class Main {

}

// INFO: 2 TODOs in total

// TODO task 1 E actually says: "Create a ___class___ outside"
object Main extends App {
  implicit val locale = Locale.SCI

  val c = 0.0
  println(c.celsius)

  val k = 0.0
  println(k.kelvin)

  val f = 0.0
  println(f.fahrenheit)

  println(display(c))
  println(display(c)(Locale.Other))
}