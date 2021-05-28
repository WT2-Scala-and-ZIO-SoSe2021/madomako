package exercise2

import org.scalatest.flatspec.AnyFlatSpec

class KarplusStrongAlgorithmTest extends AnyFlatSpec {

  def valuesOkay(remainingQueue: QueueLike[Double], elementsLeft: Int): Boolean = {
    val front = remainingQueue.front
    front match {
      case None => true & elementsLeft == 0 // queue is iterated through
      case Some(value) => value >= -0.5 & value <= 0.5 & valuesOkay(remainingQueue.dequeue.get ,elementsLeft-1)
    }
  }

  "whiteNoise" should "return a Queue with values between -0.5 and 0.5" in {
    val wn = KarplusStrongAlgorithm.whiteNoise(440, 1)
    assert(valuesOkay(wn, 440))
  }

  "update" should "properly update a given Queue" in {
    val q = Queue(Stack(0.1, Stack(0.2, EmptyStack())), EmptyStack())
    val updatedQueue = KarplusStrongAlgorithm.update(q)
    val expectedUpdatedQueue = Queue(Stack((0.2*0.5 + 0.1*0.5)*KarplusStrongAlgorithm.energyDecayFactor, EmptyStack()), Stack(0.1, EmptyStack()))
    assert(updatedQueue == expectedUpdatedQueue)
  }
}