package exercise4

import Task3.CompletedJobsHub.CompletedJobsHub
import Task3.JobBoard.JobBoard
import Task3.News.News
import zio.{Has, ZIO, clock, console, random}

package object Task3 {
  // TODO?
  type MyEnv = Has[JobBoard] with Has[CompletedJobsHub] with Has[News] with clock.Clock with console.Console with random.Random

  // TODO: ZIO.serviceWith <-> ZIO.accessM

  trait Robot {
    val name: String
    def work: ZIO[MyEnv, Any, Unit]
  }

  sealed trait Job {
    val name: String
  }

  case class PendingJob(name: String, duration: zio.duration.Duration) extends Job

  case class CompletedJob(name: String, completedBy: Robot) extends Job
}
