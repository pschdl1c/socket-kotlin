//import java.io.BufferedWriter
//import java.io.File
//import java.io.FileWriter
//import java.io.Writer
//
//fun main(args: Array<String>) {
//    println("\nСчитывание JSON-файла и вывод его содержимого\n")
//    println(readJson("src/input.json"))
//    val inp_str = readJson("src/input.json")
//    println("\nВведите данные о товаре\nТип товара, Производитель, Название, Дата производства, Цена\n")
//    val test = ",\n\t\"5\":\n\t\t{\"Тип товара\":\"Электрогитара\"," +
//            "\n\t\t\"Производитель\":\"CORT\",\n\t\t\"Название\":\"CORT G110-2T\",\n\t\t\"" +
//            "Дата производства\":\"13/03/2022\",\n\t\t\"Цена\":\"50000\"}\n}"
//    val out_str = inp_str.substring(0, inp_str.length-3) + test
//    println("\nДобавление данных к содержимому исходного файла и сохранение их в новый JSON-файл")
//    try {
//        println(saveJson(out_str, "output.json"))
//    } catch (e: Exception) {
//        println("Данные не были сохранены в файл")
//    }
//}
//
//fun readJson(s: String): String {
//    return File(s).readText()
//}
//
//private fun saveJson(s: String, out: String): String {
//    //throw Exception()
//    val output: Writer
//    val file = File(out)
//    if (!file.exists()) {
//        file.createNewFile()
//    }
//    output = BufferedWriter(FileWriter(file))
//    output.write(s)
//    output.close()
//    return "Данные успешно сохранены в файл " + out
//}
