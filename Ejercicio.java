import io.reactivex.rxjava3.core.Observable;

public class Main {
    public static void main(String[] args) {
        // Crear un Observable que emite los números del 1 al 5
        Observable<Integer> numeros = Observable.just(1, 2, 3, 4, 5);

        // Utilizar el operador map para transformar los datos
        Observable<Integer> numerosTransformados = numeros.map(num -> num * 10);

        // Suscribirse al Observable y imprimir los datos
        numerosTransformados.subscribe(System.out::println);
    }
}
