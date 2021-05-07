import com.curso.Cliente
import com.curso._01_Metodos
import com.curso._02_Colecciones_Mapas
import com.curso._03_Beans

println "================================"

//Podemos declarar variables indicando el tipo
_01_Metodos m1 = new _01_Metodos()
//O declararla con 'def' y que se decida el tipo en tiempo de ejecución
def m2 = new _01_Metodos()

m2.metodo1()
m2.metodo2()
m2.metodo3()

println m2.saludar1()
println m2.saludar2()

//Los closure son objetos
Closure c1 = m2.multiplicar
println c1 //closure
println c1(10,20)

println "================================"

def cm = new _02_Colecciones_Mapas()

cm.listas()
cm.sets()
cm.maps()

println "================================"

def b = new _03_Beans()

b.prueba()

println "================================"


//Closure cl = { println "A VER" }
//cl()

//Metodo normal y corriente
def crearMultiplicador(Integer numero){
    //Un closure puede utilizar variables declaradas fuera de él sin que sean FINAL
    //Cuando un closure utiliza una variable declarada fuera de él
    return { valor -> { return valor*numero } }
}

Closure cl1 = crearMultiplicador(10)
Closure cl1Bis = { valor -> { Integer num=10; return valor*num } }

Closure cl2 = crearMultiplicador(20)
Closure cl2Bis = { valor -> { Integer num=20; return valor*num } }

Closure cl3 = crearMultiplicador(30)
Closure cl3Bis = { valor -> { Integer num=30; return valor*num } }

println cl1(100)    //1000
println cl1Bis(100) //1000
println cl2(200)    //4000
println cl2Bis(200) //4000
println cl3(300)    //9000
println cl3Bis(300) //9000



