package example

import org.scalatest._
import com.google.inject._
import com.google.inject.AbstractModule

class GreetingModule extends AbstractModule {
  override protected def configure() {
    bind(classOf[Greeting]).to(classOf[EnglishGreeting])
  }
}

class MockedGreeting extends Greeting {
  val greeting: String = "Mock!"
}

class HelloSpec extends FlatSpec with Matchers {

  "The HelloClass" should "returns normal response" in {
    // Guiceモジュールを作成する。
    // 既存のGuiceモジュールを流用してもよい。
    val module = new AbstractModule {
      def configure(): Unit = {
        bind(classOf[Greeting]).to(classOf[EnglishGreeting])
      }
    }
    // 既存のものを使う場合
    // val module = new GreetingModule()
    val injector: Injector = Guice.createInjector(module)
    val helloInstance: HelloClass = injector.getInstance(classOf[HelloClass])
    
    helloInstance.greeter.greeting shouldBe "Hello!"
  }

  "The HelloClass" should "returns mocked response" in {
    // Guiceモジュールを作成する。
    // 既存のGuiceモジュールを流用してもよい。
    val module = new AbstractModule {
      def configure(): Unit = {
        bind(classOf[Greeting]).to(classOf[MockedGreeting])
      }
    }

    val injector: Injector = Guice.createInjector(module)
    val helloInstance: HelloClass = injector.getInstance(classOf[HelloClass])

    helloInstance.greeter.greeting shouldBe "Mock!"
  }
}
