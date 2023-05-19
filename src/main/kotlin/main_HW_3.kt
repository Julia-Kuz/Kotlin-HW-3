fun main() {
    // Задача 1
    println(agoToText(32400))
    // Задача 2
    println(fee(sumMonth = 30_000, sumTransfer = 2_000))
}

// Задача 1
fun agoToText(secTime: Int): String {
    val time: Int
    when (secTime) {
        in 0..60 -> return "Был(а) в сети только что"
        in 61..60 * 60 -> {
            time = secTime / 60
            return "Был(а) в сети $time" + grammarMinute(time) + " назад"
        }

        in 60 * 60 + 1..24 * 60 * 60 -> {
            time = secTime / 3600
            return "Был(а) в сети $time" + grammarHour(time) + " назад"
        }

        in 24 * 60 * 60 + 1..48 * 60 * 60 -> return "Был(а) в сети вчера"
        in 48 * 60 * 60 + 1..72 * 60 * 60 -> return "Был(а) в сети позавчера"
        else -> return "Был(а) в сети давно"
    }
}

fun grammarMinute(time: Int): String {
    return when {
        time % 10 == 1 && time % 100 != 11 -> " минуту"
        time % 10 in 2..4 && time % 100 !in 12..14 -> " минуты"
        else -> " минут"
    }
}

fun grammarHour(time: Int): String {
    return when {
        time % 10 == 1 && time % 100 != 11 -> " час"
        time % 10 in 2..4 && time % 100 !in 12..14 -> " часа"
        else -> " часов"
    }
}

// Задача 2

fun fee(card: String = "VK Pay", sumMonth: Int = 0, sumTransfer: Int): String {
    val minFee = 35.0
    val sumLimit = 75000
    val limitTransferMonth = 600_000
    val limitTransferOnce = 150_000
    val limitTransferMonthVK = 40_000
    val limitTransferOnceVK = 15_000

    return when (card) {
        "MasterCard", "Maestro" ->
            when {
                (sumTransfer + sumMonth) < limitTransferMonth && sumTransfer < limitTransferOnce ->
                    if ((sumTransfer + sumMonth) < sumLimit) "Комиссия составляет (руб.): " + 0.0 else "Комиссия составляет (руб.): " + sumTransfer * 0.06 / 100 + 20

                else -> "Превышен лимит перевода"
            }

        "Visa", "Мир" ->
            when {
                (sumTransfer + sumMonth) < limitTransferMonth && sumTransfer < limitTransferOnce ->
                    if (sumTransfer * 0.75 / 100 < minFee) "Комиссия составляет (руб.): $minFee" else "Комиссия составляет (руб.): " + sumTransfer * 0.75 / 100

                else -> "Превышен лимит перевода"
            }

        else ->
            when {
                (sumTransfer + sumMonth) < limitTransferMonthVK && sumTransfer < limitTransferOnceVK -> "Комиссия составляет (руб.): " + 0.0
                else -> "Превышен лимит перевода"
            }
    }
}


//    без проверки на лимиты
//    return when (card) {
//        "MasterCard", "Maestro" -> if ((sumTransfer + sumMonth) < sumLimit) 0.0 else sumTransfer * 0.06 / 100 + 20
//        "Visa", "Мир" -> if (sumTransfer * 0.75 / 100 < minFee) minFee else sumTransfer * 0.75 / 100
//        else -> 0.0
//    }


