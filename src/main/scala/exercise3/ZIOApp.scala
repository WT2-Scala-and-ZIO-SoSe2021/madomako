package exercise3
import exercise2.{Queue, QueueLike}
import zio.{UIO, URIO, ZIO}
import zio.random._

import lib.StdAudio

object ZIOApp extends zio.App {

  def run(args: List[String]) =
    myAppLogic.exitCode

  val myAppLogic: ZIO[Random, Throwable, Unit] = {
    for {
      initQueue <- whiteNoise()
      _ <- loop(initQueue)
    } yield ()
  }

  val energyDecayFactor = 0.996

  def whiteNoiseHelper(volume: Double, resultQueue: URIO[Random, QueueLike[Double]], elementsLeft: Int)
  : URIO[Random, QueueLike[Double]] = {
    if (elementsLeft == 0) {
      resultQueue
    } else {
      val valueZIO = nextDoubleBetween(-0.5, 0.5).map(_ * volume)
      for {
        value <- valueZIO
        updatedQueue = resultQueue.map(_.enqueue(value))
        x <- whiteNoiseHelper(volume, updatedQueue, elementsLeft-1)
      } yield (x)
    }
  }

  def whiteNoise(frequency:Int=440, volume:Double = 1.0): URIO[Random, QueueLike[Double]] = {
    whiteNoiseHelper(volume, URIO(Queue()), frequency)
  }

  def update(queue: QueueLike[Double]): QueueLike[Double] = {
    val front = queue.front.get
    val dequeued = queue.dequeue.get
    val nextFront = dequeued.front.get

    val newDouble = (0.5*front + 0.5*nextFront) * energyDecayFactor
    dequeued.enqueue(newDouble)
  }

  final def loop(queue: QueueLike[Double]): ZIO[Random, Throwable, Unit] = {
    val newQueue = update(queue)
    val a = for {
      _ <- play(newQueue.front.get)
      _ <- loop(newQueue)
    } yield ()
    a
  }

  def play(sample: Double): UIO[Unit] = {
    UIO(StdAudio.play(sample))
  }
}
