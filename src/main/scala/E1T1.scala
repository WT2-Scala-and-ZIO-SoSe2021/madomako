object E1T1 {
  def max(arr: Array[Int]): Int = {
    require(arr != null, "Can't get max value out of nothingness!")
    require(arr.nonEmpty, "Can't get max value out of nowhere!")

    arr.reduce((a1, a2) => if (a1 < a2) a2 else a1)
  }

  def min(arr: Array[Int]): Int = {
    require(arr != null, "Can't get min value out of nothingness!")
    require(arr.nonEmpty, "Can't get min value out of nowhere!")

    arr.reduce((a1, a2) => if (a1 < a2) a1 else a2)
  }

  def sum(arr: Array[Int]): Int = {
    require(arr != null, "Can't get sum out of nothingness!")
    require(arr.nonEmpty, "Can't get sum out of nowhere!")

    arr.reduce((a1, a2) => a1+a2)
  }
}
