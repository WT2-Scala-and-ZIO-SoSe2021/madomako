package exercise4.Task3

import exercise4.Task3.CompletedJobsHub.CompletedJobsHub
import exercise4.Task3.JobBoard.JobBoard
import zio.{Has, Schedule, URIO, ZIO, clock}

case class Worker(name: String) extends Robot {
  override def work: URIO[Has[JobBoard] with Has[CompletedJobsHub] with clock.Clock, Unit] = {
    val action = for {
      job <- JobBoard.take()
      _ <- ZIO.sleep(job.duration)
      _ <- CompletedJobsHub.publish(CompletedJob(job.name, this))
    } yield ()

    val repeated = action repeat Schedule.forever
    repeated.unit
  }
}
