package dd.code
import zio.*

object Main extends App:
  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"
  val prog =
    for
      _    <- console.putStrLn("-" * 10).debug(trace)
      _    <- console.putStrLn("your name?")
      name <- console.getStrLn
      // _    <- ZIO.effect(sys.error("boom"))
      _    <- console.putStrLn(s"Hello, $name")
      _    <- console.putStrLn("-" * 10)
    yield ()
  override def run(args: List[String]) = prog.exitCode
end Main