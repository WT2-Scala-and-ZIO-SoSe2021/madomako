package exercise2

import lib.StdAudio

import scala.annotation.tailrec
import scala.util.Random

object KarplusStrongAlgorithm {
  val energyDecayFactor = 0.996
  val r = Random

  def whiteNoiseHelper(volume: Double, resultQueue: QueueLike[Double], elementsLeft: Int): QueueLike[Double] = {
    if (elementsLeft == 0) {
      resultQueue
    } else {
      val value = (r.nextDouble() - 0.5) * volume
      val updatedQueue = resultQueue.enqueue(value)
      whiteNoiseHelper(volume, updatedQueue, elementsLeft-1)
    }
  }

  def whiteNoise(frequency:Int=440, volume:Double = 1.0): QueueLike[Double] = {
    whiteNoiseHelper(volume, Queue(), frequency)
  }

  def update(queue: QueueLike[Double]): QueueLike[Double] = {
    val front = queue.front.get
    val dequeued = queue.dequeue.get
    val nextFront = dequeued.front.get

    val newDouble = (0.5*front + 0.5*nextFront) * energyDecayFactor
    dequeued.enqueue(newDouble)
  }

  @tailrec
  final def loop(queue: QueueLike[Double])(f: Double => Unit): Unit = {
    val newQueue = update(queue)
    f(newQueue.front.get)
    loop(newQueue)(f)
  }
}

object Main extends App {
  val initQueue = KarplusStrongAlgorithm.whiteNoise()
  KarplusStrongAlgorithm.loop(initQueue)(StdAudio.play)
}