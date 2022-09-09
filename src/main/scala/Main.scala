import io.circe.parser
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.io.Source
import scala.io.StdIn.readLine
import scala.io.Source.fromURL

object Main {

  def main(args: Array[String]): Unit = {
    val configData = getDataFromResources("application.yml")
    val param = getInputParam(configData.apiDefaultParam)
    val data = getDataFromApi(configData.apiUrl)
    val output = s"Your param: \"$param\" is ${parseParam(data, param)}"
    println(output)
  }

  def getDataFromResources(resourceName: String): Config = {
    new Yaml(new Constructor(classOf[Config])).
      load(
        Source.fromResource(resourceName).mkString
      ).asInstanceOf[Config]
  }

  def getInputParam(defaultParam: String): String = {
    println("Please enter requested param name (or skip and will be ip by default)")
    val input = readLine()
    if (input.isEmpty) defaultParam else input
  }

  def getDataFromApi(url: String): String = fromURL(url).mkString

  def parseParam(data: String, param: String): Any = {
    parser.parse(data) match {
      case Left(parsingError) =>
        throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
      case Right(json) =>
        if (json.asObject.get(param).isEmpty)
          None
        else json.asObject.get(param).get
    }
  }
}