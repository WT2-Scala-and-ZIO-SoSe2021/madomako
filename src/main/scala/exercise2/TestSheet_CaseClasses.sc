case class MovieCaseClass(title: String, year: Int)

val p1 = MovieCaseClass("Monthy Python and The Holy Grail", 1975)

class Movie(val title: String, val year: Int) {
  override def toString: String = s"Movie($title,$year)"

  def copy(title: String = title, year: Int = year): Movie = new Movie(title, year)

  override def equals(obj: Any): Boolean = this.hashCode() == obj.hashCode()

  override def hashCode(): Int = title.hashCode + year.hashCode()
}

object Movie {
  def apply(title: String, year: Int): Movie = new Movie(title, year)

  def unapply(m: Movie): Option[(String, Int)] = Some((m.title, m.year))
}

val pMovie = Movie("Monthy Python and The Holy Grail", 1975)

p1.toString
pMovie.toString

p1.title
pMovie.title

p1.copy(year = 2021)
p1
pMovie.copy(year = 2021)
pMovie

val p2 = MovieCaseClass("Monthy Python and The Holy Grail", 1975)

p1 == p2

val pMovie2 = Movie("Monthy Python and The Holy Grail", 1975)
pMovie == pMovie2
pMovie.hashCode()
pMovie2.hashCode()

p1.hashCode()
p2.hashCode()

val t1 = p1 match {
  case MovieCaseClass(title, year) => title
}

val titleMovie = pMovie match {
  case Movie(title, year) => title
}
/*p1.toString

p1.name

p1.copy(year = 2021)

val p2 = MovieCaseClass("Monthy Python and The Holy Grail", 1975)

p1 == p2
p1.hashCode()
p2.hashCode()

p1 match {
  case MovieCaseClass(name, year) => year
}

*/