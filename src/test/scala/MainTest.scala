import io.circe.Json
import org.scalatest.flatspec.AnyFlatSpec

class MainTest extends AnyFlatSpec {
  val data = Main.getDataFromResources("application.yml")
  val validResponse = "{\"ip\": \"127.0.0.1\"}"
  val invalidResponse = "{\"some\": \"text\"}"

  "A defaultParam" should "be retrieved from application.yml and be equal to 'ip'" in {
    assert("ip" === data.apiDefaultParam)
  }

  "A url" should "be retrieved from application.yml and should start with 'https'" in {
    assert(data.apiUrl.startsWith("https://"))
  }

  it should "return param value when data and param" in {
    assert(Json.fromString("127.0.0.1") === Main.parseParam(validResponse, "ip"))
  }

  it should "return None when invalid data and param" in {
    assert(None === Main.parseParam(invalidResponse, "ip"))
  }

  it should "return None when data and no param" in {
    assert(None === Main.parseParam(validResponse, ""))
  }

  it should "throw IllegalArgumentException when no data and no param" in {
    assertThrows[IllegalArgumentException] {
      Main.parseParam("", "")
    }
  }

  it should "throw IllegalArgumentException when no data and param" in {
    assertThrows[IllegalArgumentException] {
      Main.parseParam("", "ip")
    }
  }
}
