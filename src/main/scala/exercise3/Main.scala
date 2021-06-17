package exercise3

import exercise3.Temperature.{Locale, TemperatureClass, display}

object Main {

  def main(args: Array[String]): Unit = {
    implicit val locale: Locale.Value = Locale.US

    val c = 0.0
    println(c.celsius)

    val k = 0.0
    println(k.kelvin)

    val f = 0.0
    println(f.fahrenheit)

    println(display(c))
    println(display(c)(Locale.US))
  }
}
