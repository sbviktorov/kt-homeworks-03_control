package ru.netology

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val sdf = SimpleDateFormat("dd.M.yyyy HH:mm:ss")
    var dateInMS = Date().time - (0..60).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
    println()
    dateInMS = Date().time - (61..60 * 60).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
    println()
    dateInMS = Date().time - (60 * 60 + 1..24 * 60 * 60).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
    println()
    dateInMS = Date().time - (24 * 60 * 60 + 1..48 * 60 * 60).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
    println()
    dateInMS = Date().time - (48 * 60 * 60 + 1..72 * 60 * 60).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
    println()
    dateInMS = Date().time - (72 * 60 * 60 + 1..72 * 60 * 60 + 500).random() * 1000
    println("Условия: сейчас ${sdf.format(Date())}, пользователь был в сети в ${(sdf.format(Date(dateInMS)))}")
    println(agoToText(dateInMS))
}

fun agoToText(dateInMS: Long): String {
    val timeDifferenceInSec = (Date().time - dateInMS) / 1000
    return "Пользователь был в сети " + when (timeDifferenceInSec) {
        in 0..60 -> "только что"
        in 61..60 * 60 -> "${timeDifferenceInSec / 60} ${minutes((timeDifferenceInSec / 60).toInt())} назад"
        in 60 * 60 + 1..24 * 60 * 60 -> "${timeDifferenceInSec / (60 * 60)} ${hours((timeDifferenceInSec / (60 * 60)).toInt())} назад"
        in 24 * 60 * 60 + 1..48 * 60 * 60 -> "вчера"
        in 48 * 60 * 60..72 * 60 * 60 -> "позавчера"
        else -> "давно"
    }
}

fun minutes(minutes: Int): String {

    if ((minutes % 100 < 15) && (minutes % 100 > 10)) {
        return "минут"
    } else {
        return when (minutes % 10) {
            1 -> "минуту"
            in 2..4 -> "минуты"
            else -> {
                "минут"
            }
        }
    }
}

fun hours(hours: Int): String {

    return if ((hours % 100 < 15) && (hours % 100 > 10)) {
        "часов"
    } else {
        when (hours % 10) {
            1 -> "час"
            in 2..4 -> "часа"
            else -> {
                "часов"
            }
        }
    }
}