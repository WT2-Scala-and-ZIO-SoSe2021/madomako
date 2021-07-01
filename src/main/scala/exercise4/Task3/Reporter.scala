package exercise4.Task3

import exercise4.Task3.News.News
import zio.{Has, Schedule, ZIO, clock, console}

import java.io.IOException

case class Reporter(name: String) extends Robot {
  override def work: ZIO[Has[News] with clock.Clock with console.Console, IOException, Unit] = {
    val action = {
      for {
        news <- News.proclaim()
        _ <- console.putStrLn(s"[$name]: $news")
      } yield ()
    }
    val repeated = action repeat Schedule.forever
    repeated.unit
  }
}
