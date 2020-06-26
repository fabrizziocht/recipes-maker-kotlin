const val MENU = """Selecciona la opción deseada
    1. Hacer una receta
    2. Ver mis recetas
    3. Salir"""
val INGREDIENTES = listOf("Agua", "Leche", "Carne", "Verduras", "Frutas", "Cereal", "Huevos", "Aceite")

fun main() {
   val recetas = arrayListOf<Pair<String, ArrayList<String>>>()
   loop@ while(true) {
      println(MENU)
      when (readLine()) {
         "1" -> {
            print("Indique un nombre de la receta:")
            val receta = Pair(readLine() ?: "", arrayListOf<String>())
            loop1@ while (true) {
               println("""Ahora seleccione los productos que desea agregar:
${INGREDIENTES.mapIndexed { index, ing ->  "${index.inc()}. $ing" }.joinToString("\n")}
${INGREDIENTES.size.inc()}. Salir""".trimIndent())
               when(val opcion = readLine()?.toInt() ?: -1) {
                  in 0..INGREDIENTES.size -> {
                     val ingrediente = INGREDIENTES[opcion - 1]
                     if (receta.second.any { x -> x == ingrediente }.not()) {
                        receta.second.add(ingrediente)
                        println("$ingrediente añadido")
                     } else
                        println("$ingrediente ya está agregado en la receta.")
                  }
                  INGREDIENTES.size.inc() -> {
                     recetas.add(receta)
                     break@loop1
                  }
                  else -> println("Opción incorrecta, intente nuevamente")
               }
            }
         }
         "2" -> {
            println("LISTA DE RECETAS:")
            if (recetas.size > 0)
               for ((index, receta) in recetas.withIndex()){
                  println("${index.inc()}. ${receta.first} (${receta.second.size} items)")
               }
            else println("Sin recetas registradas")
         }
         "3" -> break@loop
         else -> println("Opción incorrecta, intente nuevamente.")
      }
   }
}