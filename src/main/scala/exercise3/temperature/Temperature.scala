package exercise3

package object Temperature {
    /* ??? */ implicit /* ??? */ val default: Locale.Locale = Locale.Other // TODO: Ask for help!!!

    type Temperature = Double;

    object Locale extends Enumeration {
      type Locale = String

      val US = "US"
      val SCI = "SCI"
      val Other = "Other"
    }

    def display(temp: Temperature)(implicit locale: Locale.Locale): String = locale match {
      case Locale.US => s"${temp.celsius()} °C"
      case Locale.SCI => s"${temp.fahrenheit()} °F"
      case Locale.Other => s"${temp.fahrenheit()} °K"
      case _ => "Use a Locale with only [US | SCI | Other]"
    }

    implicit class TemperatureClass(temp: Temperature) { // TODO: Ask for help!!!
      val freezingPoint: Temperature = 0.0
      val absoluteZero: Temperature = -273.15

      def celsius(): Temperature = {
        temp
      }

      def fahrenheit(): Temperature = {
        (temp - 32) * 5 / 9
      }

      def kelvin(): Temperature = {
        temp + absoluteZero
      }

      def avg(other: Temperature): Temperature = {
        (temp + other) / 2
      }
    }
}
