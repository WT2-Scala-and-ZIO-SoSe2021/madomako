package exercise4.Task3

import zio.{Has, Queue, UIO, ULayer, URIO, ZIO, ZLayer}

object News {
  trait News {
    def post(news: String): UIO[Unit]
    def proclaim(): UIO[String]
  }

  case class NewsLive(newsList: Queue[String]) extends News {

    override def post(news: String): UIO[Unit] = {
      newsList.offer(news).unit
    }

    override def proclaim(): UIO[String] = {
      newsList.take
    }
  }

  // public "API"
  object News {
    def post(news: String): URIO[Has[News], Unit] = ZIO.serviceWith[News](_.post(news))
    def proclaim(): URIO[Has[News], String] = ZIO.serviceWith[News](_.proclaim())
  }

  // make service accessible as layer
  object NewsLive {
    val layer: ULayer[Has[News]] = ZLayer.fromEffect(Queue.unbounded[String].map(q => NewsLive(q)))
  }
}
