package exercise4.Task3

import zio.{Dequeue, Has, Hub, Managed, UIO, ULayer, URIO, ZIO, ZLayer, ZManaged}

object CompletedJobsHub {
  trait CompletedJobsHub {
    def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]]
    def publish(job: CompletedJob): UIO[Unit]
  }

  case class CompletedJobsHubLive(jobsBroadcaster: Hub[CompletedJob]) extends CompletedJobsHub {
    override def subscribe: ZManaged[Any, Nothing, Dequeue[CompletedJob]] =
      jobsBroadcaster.subscribe

    override def publish(job: CompletedJob): UIO[Unit] = {
      jobsBroadcaster.publish(job).unit
    }
  }

  // public "API"
  object CompletedJobsHub {
    def subscribe: ZManaged[Has[CompletedJobsHub], Nothing, Dequeue[CompletedJob]] =
      Managed.accessManaged[Has[CompletedJobsHub]](_.get.subscribe)

    def publish(job: CompletedJob): URIO[Has[CompletedJobsHub], Unit] =
      ZIO.serviceWith[CompletedJobsHub](_.publish(job))
  }

  // make service accessible as layer
  object CompletedJobsHubLive {
    val layer: ULayer[Has[CompletedJobsHub]] = ZLayer.fromEffect(Hub.unbounded[CompletedJob].map(hub => CompletedJobsHubLive(hub)))
  }
}
