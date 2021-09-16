package dd.service

import zio.*
import zhttp.http.*
import zhttp.service.Server

object HelloWorld extends App:
  val response = Response.text("Hello")
  val app = Http.collect[Request] { case Method.GET -> Root / "text" =>
    response
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    Server.start(8090, app).exitCode
