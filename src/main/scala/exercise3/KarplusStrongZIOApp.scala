package exercise3

import exercise2.{Queue, QueueLike}
import lib.StdAudio
import zio.random._
import zio.{App, ExitCode, UIO, URIO, ZEnv, ZIO}

object KarplusStrongZIOApp extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    val appLogic = for {
      noise <- whiteNoise()
      _ <- loop(noise)
    } yield ()
    appLogic.exitCode
  }

  def play(sample: Double) : UIO[Unit] = ZIO.succeed(StdAudio.play(sample))

/*
  def whiteNoiseHelper(volume: Double, resultQueue: QueueLike[Double], elementsLeft: Int): QueueLike[Double] = {
    if (elementsLeft == 0) {
      resultQueue
    } else {
      val value = nextDoubleBetween(-0.5, 0.5)
      val updatedQueue = resultQueue.enqueue(value)
      whiteNoiseHelper(volume, updatedQueue, elementsLeft-1)
    }
  }
*/

  val energyDecayFactor = 0.996
  def update(queue: QueueLike[Double]): QueueLike[Double] = {
    val front = queue.front.get
    val dequeued = queue.dequeue.get
    val nextFront = dequeued.front.get

    val newDouble = (0.5*front + 0.5*nextFront) * energyDecayFactor
    dequeued.enqueue(newDouble)
  }

  def whiteNoise(frequency: Int = 440, volume: Double = 1.0): URIO[Random, QueueLike[Double]] = {
    // if (frequency <= 0 || volume < 0.0 && volume > 1.0) return ZIO.fail("Incorrect values submitted - Frequency or Volume can't be 0")

    val a = ZIO.foldLeft(0 to frequency)(Queue[Double]().asInstanceOf[QueueLike[Double]]) { (currentQueue, _) =>
      for {
        value <- nextDoubleBetween(-0.5, 0.5)
        newQueue = currentQueue.enqueue(value * volume)
      } yield newQueue
    }

    a
  }

  final def loop(queue: QueueLike[Double]): ZIO[Random, Throwable, Unit] = {
    val newQueue = update(queue)
    for {
      _ <- play(newQueue.front.get)
      _ <- loop(newQueue)
    } yield ()
  }
}
