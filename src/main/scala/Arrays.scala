object Arrays {

  def max(arr: Array[Int]): Int = {
    arr.reduce(_ max _)
  }

  def min(arr: Array[Int]): Int = {
    arr.reduce(_ min _)
  }

  def sum(arr: Array[Int]): Int = {
    arr.fold(0)(_ + _)
  }







}
