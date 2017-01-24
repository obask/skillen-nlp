import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {

  it must "pop values in last-in-first-out order" in {
    (1+1) should be (2)
  }

}