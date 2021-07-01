package exercise4
import zio.{ExitCode, URIO, ZIO}

object Task2 extends zio.App {
  val program = for {
    fib <- ((ZIO.succeed().forever zipPar ZIO.fail("Error"))).fork
    res <- fib.join
  } yield ()

//  val program = for {
//    fib <- ((ZIO.sleep(5.seconds) race ZIO.sleep(20.seconds))).fork
//    res <- fib.await
//  } yield ()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = program.exitCode
}
