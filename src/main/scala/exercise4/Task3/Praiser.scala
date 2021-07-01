package exercise4.Task3

import exercise4.Task3.CompletedJobsHub.CompletedJobsHub
import exercise4.Task3.News.News
import zio.{Dequeue, Has, Schedule, URIO, ZManaged, clock}

case class Praiser(name: String) extends Robot {
  override def work: URIO[Has[CompletedJobsHub] with Has[News] with clock.Clock, Unit] = {
    val completedJobsQueue: ZManaged[Has[CompletedJobsHub], Nothing, Dequeue[CompletedJob]] = CompletedJobsHub.subscribe
    val action = completedJobsQueue.use( q =>
      for {
        job <- q.take
        _ <- News.post(s"[posted by $name]: ${job.completedBy.name} did an excellent job")
      } yield ()
    )
    val repeated = action repeat Schedule.forever
    repeated.unit
  }
}
