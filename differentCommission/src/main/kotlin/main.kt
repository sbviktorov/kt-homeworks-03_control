package ru.netology

import kotlin.math.roundToInt

fun main() {
    commission("Mastercard", currentTransfer = 75000)
    commission("Mastercard", currentTransfer = 75001)
    commission("Mastercard", lastTransfer = 65000, currentTransfer = 20000)
    commission("Mastercard", lastTransfer = 0, currentTransfer = 150001)
    println()
    commission("Maestro", currentTransfer = 75000)
    commission("Maestro", currentTransfer = 75001)
    commission("Maestro", lastTransfer = 75000, currentTransfer = 1)
    commission("Maestro", lastTransfer = 599999, currentTransfer = 2)
    println()
    commission("Visa", currentTransfer = 4666)
    commission("Visa", currentTransfer = 4668)
    commission("Visa", lastTransfer = 65000, currentTransfer = 20000)
    commission("Visa", lastTransfer = 0, currentTransfer = 150001)
    println()
    commission("Мир", currentTransfer = 1)
    commission("Мир", currentTransfer = 4668)
    commission("Мир", lastTransfer = 65000, currentTransfer = 20000)
    commission("Мир", lastTransfer = 600000, currentTransfer = 1)
    println()
    commission("VK Pay", currentTransfer = 15000)
    commission(currentTransfer = 15001)
    commission(lastTransfer = 39999, currentTransfer = 2)
    commission(currentTransfer = 200)
    println()
    commission("UnionPay", currentTransfer = 15000)
}

fun commission(cardType: String = "VK Pay", lastTransfer: Long = 0, currentTransfer: Long) {
    val sumOfTransfers = lastTransfer * 100 + currentTransfer * 100
    val commissionValue: Int
    when (cardType) {

        "Mastercard", "Maestro" -> {
            if (!limit(cardType, lastTransfer, currentTransfer)) {
                if (sumOfTransfers <= 75_000_00) {
                    commissionValue = 0
                    transferMsg(cardType, currentTransfer, lastTransfer, commissionValue)
                } else {
                    commissionValue = ((sumOfTransfers - 75_000_00) * 0.006 + 20_00).roundToInt()
                    transferMsg(cardType, currentTransfer, lastTransfer, commissionValue)
                }
            }
        }
        "Visa", "Мир" -> {
            if (!limit(cardType, lastTransfer, currentTransfer)) {
                if (currentTransfer * 100 * 0.0075 < 35_00) {
                    commissionValue = 35_00
                    transferMsg(cardType, currentTransfer, lastTransfer, commissionValue)
                } else {
                    commissionValue = (currentTransfer * 100 * 0.0075).roundToInt()
                    transferMsg(cardType, currentTransfer, lastTransfer, commissionValue)
                }

            }
        }
        "VK Pay" -> {
            if (!limit(cardType, lastTransfer, currentTransfer)) {
                commissionValue = 0
                transferMsg(cardType, currentTransfer, lastTransfer, commissionValue)
            }
        }
        else -> {
            println("Карта вашего банка ($cardType) не обслуживается")
        }
    }

}

fun limit(cardType: String = "VK Pay", lastTransfer: Long = 0, currentTransfer: Long): Boolean {
    val currentTransferInKop = currentTransfer * 100
    val lastTransferInKop = lastTransfer * 100
    return when (cardType) {
        "Mastercard", "Maestro", "Visa", "Мир" -> {
            if ((currentTransferInKop > 150_000_00) || (currentTransferInKop + lastTransferInKop > 600_000_00)) {
                println(
                    "Перевод $currentTransfer руб. с карты $cardType невозможен. Максимальный перевод не более " +
                            "150000 руб. и не более 600000 руб. в месяц. Сумма предыдущих переводов за месяц " +
                            "$lastTransfer руб."
                )
                true
            } else {
                false
            }
        }
        "VK Pay" -> {
            if ((currentTransferInKop > 15_000_00) || (currentTransferInKop + lastTransferInKop > 40_000_00)) {
                println("Перевод $currentTransfer руб. с карты $cardType невозможен. Максимальный перевод не более " +
                        "15000 руб. и не более 40000 руб. в месяц. Сумма предыдущих переводов за месяц " +
                        "$lastTransfer руб.")
                true
            } else {
                false
            }

        }
        else -> {
            println("Такая карта не обслуживается")
            false
        }
    }
}

fun transferMsg(cardType: String, currentTransfer: Long, lastTransfer: Long, commissionValue: Int) {
    println("Комиссия за перевод $currentTransfer руб. с карты $cardType составила ${commissionValue / 100} руб. " +
            "${commissionValue % 100} коп. Ранее совершенные переводы в этом месяце $lastTransfer руб.")
}