package exercise3

import exercise2.KarplusStrongAlgorithm.update
import exercise2.{Queue, QueueLike}
import zio.random._
import zio.{App, ExitCode, UIO, URIO, ZIO}

import scala.annotation.tailrec

class KarplusStrongZIOApp extends App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val sound = whiteNoise()
    loop(sound)
  }

  def play(sample: Double) : UIO[Unit] = ZIO.effectTotal(play(sample))

  def whiteNoiseHelper(volume: Double, resultQueue: QueueLike[Double], elementsLeft: Int): QueueLike[Double] = {
    if (elementsLeft == 0) {
      resultQueue
    } else {
      val value = nextDoubleBetween(-0.5, 0.5)
      val updatedQueue = resultQueue.enqueue(value)
      whiteNoiseHelper(volume, updatedQueue, elementsLeft-1)
    }
  }

  def whiteNoise(frequency: Int = 440, volume: Double = 1.0): URIO[Random, Queue[Double]] = {
    whiteNoiseHelper(volume, Queue(), frequency)
  }

  @tailrec
    final def loop(queue: QueueLike[Double]): ZIO[Random, Throwable, Unit] = {
    val newQueue = update(queue)
    play(newQueue.front.get)
    loop(newQueue)
  }
}
