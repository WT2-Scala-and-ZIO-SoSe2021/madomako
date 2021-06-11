package exercise3

package object Temperature {
    implicit val defaultLocale: Locale.Locale = Locale.Other

    object Locale extends Enumeration {
      type Locale = Value

      val US, SCI, Other = Value
    }

    type Temperature = Double

    def display(temp: Temperature)(implicit locale: Locale.Locale): String = locale match {
        case Locale.US    => s"$temp °F"
        case Locale.SCI   => s"$temp °K"
        case Locale.Other => s"$temp °C"
        case _            => throw new IllegalArgumentException(locale.toString)
    }

    implicit class TemperatureClass(temp: Temperature) {
      val freezingPoint: Temperature = 0.0
      val absoluteZero: Temperature = -273.15

      def celsius : Temperature = temp

      def fahrenheit : Temperature = (temp - 32) * 5 / 9

      def kelvin : Temperature = temp + absoluteZero

      def avg(other: Temperature) : Temperature = (temp + other) / 2
    }
}
