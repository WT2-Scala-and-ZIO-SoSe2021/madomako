object Arrays {

  def max(arr: Array[Int]): Int = {
    arr.reduce(_ max _)
    /*arr.reduce((a1, a2) => a1.max(a2))*/
  }

  def min(arr: Array[Int]): Int = {
    arr.reduce(_ min _)
    /*arr.reduce((a1, a2) => a1.min(a2))*/
  }

  def sum(arr: Array[Int]): Int = {
    arr.fold(0)(_ + _)
  }







}
