import rx._

object DidacticExample extends App {
// Creamos una variable reactiva
val temperature = Var(20.0)

// Creamos una expresión reactiva que depende de la variable
val isHot = Rx { temperature() > 25 }

// Creamos un observador que reacciona a cambios en la expresión reactiva
val hotObserver = isHot.trigger {
if (isHot.now) {
println("Hace calor!")
} else {
println("No hace tanto calor.")
}
}

// Imprimimos el estado actual de isHot
println(s"¿Hace calor ahora? ${isHot.now}")

// Cambiamos el valor de la variable
temperature() = 30.0

// El observador reacciona automáticamente al cambio
// Imprimimos el nuevo estado de isHot
println(s"¿Hace calor ahora? ${isHot.now}")

// Detenemos el observador
hotObserver.kill()

// Cambiamos nuevamente la temperatura
temperature() = 18.0

// Como el observador está detenido, no se imprimirá nada
}