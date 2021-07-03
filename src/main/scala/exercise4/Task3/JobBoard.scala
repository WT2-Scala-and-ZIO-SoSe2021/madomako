package exercise4.Task3

import zio.{Has, Queue, UIO, ULayer, URIO, ZIO, ZLayer}

object JobBoard {
  trait JobBoard {
    /**
    Submits a job to the job board, which can later be taken up by a robot using the take method.
     */
    def submit(job: PendingJob): UIO[Unit]

    /**
    Take a job from the job board
     */
    def take(): UIO[PendingJob]
  }

  case class JobBoardLive(pendingJobs: Queue[PendingJob]) extends JobBoard {

    override def submit(job: PendingJob): UIO[Unit] = {
      pendingJobs.offer(job).unit
    }

    override def take(): UIO[PendingJob] = {
      pendingJobs.take
    }
  }

  // public "API"
  object JobBoard {
    def submit(job: PendingJob): URIO[Has[JobBoard], Unit] =
      ZIO.serviceWith[JobBoard](_.submit(job))

    def take(): URIO[Has[JobBoard], PendingJob] =
      ZIO.serviceWith[JobBoard](_.take())
  }

  // make service accessible as layer
  object JobBoardLive {
    val layer: ULayer[Has[JobBoard]] = ZLayer.fromEffect(Queue.unbounded[PendingJob].map(x => JobBoardLive(x)))
  }
}
