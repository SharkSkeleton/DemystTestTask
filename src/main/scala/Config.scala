import scala.beans.BeanProperty

class Config {
  @BeanProperty var apiUrl = ""
  @BeanProperty var apiDefaultParam = ""
  override def toString: String = s"apiUrl: $apiUrl, apiDefaultParam: $apiDefaultParam"
}
