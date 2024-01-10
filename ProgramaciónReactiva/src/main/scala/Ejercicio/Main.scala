package Ejercicio
import rx._
object Main1 extends App {

  val a = Var(1)
  val b = Var(2)
  val c = Rx{ a() + b()} //3  //Reactivo

  println(c.now) //3
  a() = 4
  println(c.now) //6
}

object Main2 extends App {
  val a = Var(1)

  val b = Var(2)

  val c = Rx{ a() + b()} //3
  val d = Rx{ c() * 5} //15
  val e = Rx{ c() + 4} //7
  val f = Rx{ d() + e() + 4} //26

  println(f.now) //26
  a() = 3
  println(f.now) //38
}

//Observers - Obs
//Observa y reacciona al comportamiento de Var o Rx
object Main3 extends App {

  val a = Var(1)
  var count = 0
  val o = a.trigger{
    count = a.now + 1 //2
  }

  println(count) //2
  a() = 4
  println(count) //5

}

object Main4 extends App {

  val a = Var(1)
  var count = 0
  val o = a.trigger{
    count = count + 1
  }

  println(count) // 1
  a() = 4
  println(count) // 2
}

object Main5 extends App {

  val a = Var(1)
  var count = 0
  val o = a.triggerLater{
    count = count + 1
  }

  println(count) // 0
  a() = 4
  println(count) // 1
}

//Kill Obs - Es posible "apagar un Obs" usando el metodo kill
object Main6 extends App {

  val a = Var(1)
  val b = Rx{ 2 * a()}
  var target = 0
  val o = b.trigger{
       target = b.now
  }

  println(target) // 2
  a() = 2
  println(target) //4
  o.kill() //Se mata el obs por lo tanto deja de estar pendiete de b que esta influeciado de a
  a() = 3
  println(target) //4

}

object Main7 extends App{

  val a = Var(Seq(1,2,3))
  val b = Var(3)
  val c = Rx{ b() +: a()}  // C es el resultado de concadenar b con a
  val d = Rx{ c().map("omg" * _)}
  val e = Var("wtf")
  val f = Rx{ (d() :+ e()).mkString }

  println(c)

  println(f.now)
  a() = Nil
  println(f.now)
  e() = "wtfbbq"
  print(f.now)
}