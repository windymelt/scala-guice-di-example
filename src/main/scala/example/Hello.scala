package example

import javax.inject._
import com.google.inject.AbstractModule

object Hello extends App {
  new HelloClass(new EnglishGreeting()).sayHello()
}

class HelloClass @Inject() (val greeter: Greeting) {
  def sayHello() {
    println(greeter.greeting)
  }
}

trait Greeting {
  val greeting: String;
}

class EnglishGreeting extends Greeting {
  val greeting: String = "Hello!"
}