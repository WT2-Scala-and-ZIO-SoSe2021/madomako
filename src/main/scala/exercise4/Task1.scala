package exercise4

import zio.{ExitCode, Has, ULayer, URIO, URLayer}

object Task1 extends zio.App {

  trait Config
  trait Logging
  trait Parsing
  trait Database
  trait Serialization
  trait UserService

  val configLive: ULayer[Has[Config]] = ???
  val userServiceLive: URLayer[Has[Database] with Has[Logging] with Has[Serialization], Has[UserService]] = ???
  val parsingLive: ULayer[Has[Parsing]] = ???
  val serializationLive: URLayer[Has[Parsing], Has[Serialization]] = ???
  val databaseLive: URLayer[Has[Config], Has[Database]] = ???
  val loggingLive: ULayer[Has[Logging]] = ???

  type MyEnv = Has[Database] with Has[Logging] with Has[Serialization] with Has[UserService] with Has[Parsing] with Has[Config]

  def f(): URIO[MyEnv, Unit] = ???

  // VERBOSE:
//  val databaseLive_ : ULayer[Has[Database]] = configLive >>> databaseLive
//  val serializationLive_ : ULayer[Has[Serialization]] = parsingLive >>> serializationLive
//  val userServiceLive_ : ULayer[Has[UserService]] = (databaseLive_ ++ loggingLive ++ serializationLive_) >>> userServiceLive
//  val myEnvLayer: ZLayer[Any, Nothing, MyEnv] =
//    (databaseLive_ ++ loggingLive ++ serializationLive_ ++ userServiceLive_ ++ parsingLive ++ configLive)

  val databaseLive_ = configLive >+> databaseLive
  val serializationLive_ = parsingLive >+> serializationLive
  val userServiceLive_ = (databaseLive_ ++ loggingLive ++ serializationLive_) >+> userServiceLive
  val myEnvLayer: ULayer[MyEnv] = (userServiceLive_)

  // SHORT:
//  val myEnvLayer: ULayer[MyEnv] = (configLive >+> databaseLive) ++ loggingLive ++ (parsingLive >+> serializationLive) >+> userServiceLive // Database + Logging + Serialization

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    f().provideCustomLayer(myEnvLayer).exitCode
}