import zio.IO
import zio.config.*, ConfigDescriptor.{given, *}, ConfigSource.{given, *}
import java.io.File

final case class MyConfig(ldap: String, port: Int, dburl: String)

// val myConfig: ConfigDescriptor[MyConfig] = (string("LDAP") |@| int("PORT") |@| string("DB_URL")) (MyConfig.apply, MyConfig.unapply)
val three: 3 = 3
val four: 4 = 4
// Union
// 
// val tf: 3 | 4 = 1
val err: 3 & 4 = 99.asInstanceOf[3 & 4]
val err2: 3 | 4 = 99.asInstanceOf[3 | 4]
val threeOrFour:3 | 4 = three
type ErrorOr[T] = T | "error"
def handleErr(f:ErrorOr[File]) = ???
def partThree(t:3)(i:Int) = t + i
type IN = [INN] =>> Int
// Intersction
// 
//val in:IN[Any] = (_:Any) => 2
trait Config
trait HostConfig extends Config
trait HostController:
  def get:Option[HostConfig]

final class MyHost(config:HostConfig) extends HostConfig:
  def get = "host"

trait PortConfig extends Config
trait PortController:
  def get: Option[PortConfig]

class HostWihtPort(host:HostController, port:PortController) extends HostController with PortController:
  def get = ??? 


def getConfigs(c:HostController & PortController) = c.get

@main def hello: Unit =
  println(msg)
  println(err) 
  println(err2) 
//  println(in("21312"))

def msg = "I was compiled by Scala 3. :)"