package exercise3

import exercise3.temperature.Locale.Locale

package object temperature {
  type Temperature = Double

  // TODO Temperature as type?
  implicit class Celsius(systemValue: Temperature) {
    val freezingPoint = 0
    val absoluteZero = -273.15

    def celsius: Temperature = systemValue
    def kelvin: Temperature = systemValue + 273.15
    def fahrenheit: Temperature = (systemValue - 32) / (9.0/5.0)

    def avg(other: Temperature): Temperature = (systemValue + other) / 2.0
  }

  object Locale extends Enumeration {
    type Locale = Value
    val US, SCI, Other = Value
  }

  implicit val locale = Locale.Other

  def display(t: Temperature)(implicit locale: Locale): String = {
    locale match {
      case Locale.US => t + " °F"
      case Locale.SCI => t + " °K"
      case Locale.Other => t + " °C"
    }
  }
}
