package exercise4.Task3

import zio.{ExitCode, URIO, ZIO, clock, console, random}

import java.util.concurrent.TimeUnit

object AutoZion extends zio.App {
  val program = {
    for {
      worker1 <- Worker("WorkerMorker").work.fork
      worker2 <- Worker("Workerum").work.fork
      overseer <- Overseer("Overseeme").work.fork
      praiser <- Praiser("PraiserMazer").work.fork
      _ <- ZIO.sleep(zio.duration.Duration(2, TimeUnit.SECONDS))
      elder1 <- Elder("ElderMelder", 1, 2).work.fork
      elder2 <- Elder("Elderum", 4, 8).work.fork
      reporter <- Reporter("ConsolePutter").work
    } yield ()
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    program
      .provideLayer(
        JobBoard.JobBoardLive.layer
          ++ CompletedJobsHub.CompletedJobsHubLive.layer
          ++ News.NewsLive.layer
          ++ clock.Clock.live // TODO?
          ++ console.Console.live // TODO?
          ++ random.Random.live) // TODO?
      .exitCode
  }
}
