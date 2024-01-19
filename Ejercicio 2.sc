import rx.lang.scala.{Observable, Subject, Subscription}
import scala.concurrent.duration._

val observable: Observable[Int] = Observable.from(1 to 5)

val subject: Subject[Int] = Subject[Int]()

val observer: Subscription = subject.subscribe(
  onNext = elem => println(s"Recibido: $elem"),
  onError = error => println(s"Error: $error.getMessage"),
  onCompleted = () => println("Completado")
)

val transformed: Observable[Int] = observable
  .map(_ * 2)
  .filter(_ % 2 == 0)
  .delay(1.second)

transformed.subscribe(subject)
