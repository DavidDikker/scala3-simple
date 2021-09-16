package dd.code
import zio.*
import scala.languageFeature.implicitConversions

object Main extends App:
  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"
  val prog =
    for
      _    <- console.putStrLn("-" * 10).debug(trace)
      _    <- console.putStrLn("your name?")
      name <- console.getStrLn
      // _    <- ZIO.effect(sys.error("boom"))
      _ <- console.putStrLn(s"Hello, $name")
      _ <- console.putStrLn("-" * 10)
    yield ()
  override def run(args: List[String]) = prog.exitCode
end Main

extension [R, A](run: R => A) def provive(r: => R): Any => A = _ => run(r)
trait Google:
  def countPicturesOf(topic: String): Int

object GoogleImpl:
  lazy val live: Any => Google = _ => make
  lazy val make: Google =
    new:
      override def countPicturesOf(topic: String): Int =
        if topic == "cats" then 1337 else 1336

trait BusinessLogic:
  def doesGoogleHavePics(topic: String): Boolean

object BusinessLogic:
  lazy val live: Google => BusinessLogic = make
  def make(google: Google): BusinessLogic =
    new:
      override def doesGoogleHavePics(topic: String): Boolean =
        google.countPicturesOf(topic) % 2 == 0

object DependencyGraph:
  lazy val live: Any => BusinessLogic = _ =>
    val google: Google = GoogleImpl.live(123)
    BusinessLogic.live(google)
  lazy val make: BusinessLogic = BusinessLogic.live(GoogleImpl.make)

@main def run: Unit =
  lazy val bl: BusinessLogic = DependencyGraph.make
  println(bl.doesGoogleHavePics("cats"))
