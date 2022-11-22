import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer

fun main(args: Array<String>) {
    val server = ServerSocket(9999)
    println("Сервер запущен, используется порт ${server.localPort}")
    while (true) {
        val client = server.accept()
        println("Подключен клиент с IP-адресом ${client.inetAddress.hostAddress}")
        thread { ClientHandler(client).run() }
    }
}

fun readJson(s: String): String {
    return File(s).readText()
}

private fun saveJson(s: String, out: String): String {
    //throw Exception()
    val output: Writer
    val file = File(out)
    if (!file.exists()) {
        file.createNewFile()
    }
    output = BufferedWriter(FileWriter(file))
    output.write(s)
    output.close()
    val res = "Данные успешно сохранены в файл " + out
    return res
}
fun inpt(type: String,  cons: String, name: String, date: String, price: String): Any {
    println("\nСчитывание JSON-файла и вывод его содержимого\n")
    println(readJson("src/input.json"))
    val inp_str = readJson("src/input.json")
//    println("\nВведите данные о товаре\nТип товара, Производитель, Название, Дата производства, Цена\n")
    val test = ",\n\t\"Товары\":\n\t\t{\"Тип товара\":\"$type\"," +
            "\n\t\t\"Производитель\":\"$cons\",\n\t\t\"Название\":\"$name\",\n\t\t\"" +
            "Дата производства\":\"$date\",\n\t\t\"Цена\":\"$price\"}\n}"
    val out_str = inp_str.substring(0, inp_str.length-3) + test
    println("\nДобавление данных к содержимому исходного файла и сохранение их в новый JSON-файл")
    try {
        return saveJson(out_str, "output.json")
    } catch (e: Exception) {
        return println("Данные не были сохранены в файл")
    }
}


class ClientHandler(client: Socket) {
    private val client: Socket = client
    private val reader: Scanner = Scanner(client.getInputStream())
    private val writer: OutputStream = client.getOutputStream()
    private var running: Boolean = false

    fun run() {
        running = true
        write("Добро пожаловать на сервер!\n" +
                "Для завершения работы напишите: Выход\n" +
                "\nВведите данные о товаре\nТип товара, Производитель, Название, Дата производства, Цена\n")

        while (running) {
            try {
                val text = reader.nextLine()
                if (text == "Выход"){
                    shutdown()
                    continue
                }
                val values = text.split(' ')
                try {
                    val result = inpt(values[0], values[1], values[2], values[3], values[4])
                    write(result as String)
                } catch (ex: Exception) {write("Вы ввели неверный формат")}

//                val result = inpt(values[0], values[1], values[2], values[3], values[4])
//                write("Вы ввели неверный формат")
//                write(result as String)
            } catch (ex: Exception) {
                shutdown()
            }
        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun shutdown() {
        running = false
        client.close()
        println("Клиент с IP-адресом ${client.inetAddress.hostAddress} закрыл соединение")
    }
}
