def anwenden(f: Int => Int, x: Int) = f(x)

def square(a: Int = 2) : Int = {
  return a*a
}

def rekursiveFkt(fib: Int) : Int = {
  if (fib == 0 || fib == 1) {
    return 1
  }
  rekursiveFkt(fib - 1) + rekursiveFkt(fib - 2)
}

/*def fakultaet(n : Int) : Int = {
  def fakAkk(n : Int, akku: Int): Int = {
    fakAkk(n - 1, n * akku)
  }
  return fakAkk(n, 1)
}*/

def addI(a:Int)(b:Int) = a+b
def addI2(a:Int) = (b:Int) => a+b

// TEST
anwenden(square, 2)
anwenden(addI(2), 2)
anwenden(addI2(2), 2)
rekursiveFkt(5)

