package exercise4.Task3

import exercise4.Task3.JobBoard.JobBoard
import zio.{Has, Schedule, URIO, clock, random}

import java.util.concurrent.TimeUnit

case class Elder(name: String, submitInterval: Int, jobDuration: Int) extends Robot {

  override def work: URIO[Has[JobBoard] with clock.Clock with random.Random, Unit] = {
    val action = {
      for {
        randChar <- zio.random.nextString(2)
        job = PendingJob("Job" + jobDuration + randChar, zio.duration.Duration(jobDuration, TimeUnit.SECONDS))
        _ <- JobBoard.submit(job)
      } yield ()
    }
    val policy = {
      Schedule.fixed(zio.duration.Duration(submitInterval, TimeUnit.SECONDS))
    }
    val repeated = action repeat policy
    repeated.unit
  }
}
