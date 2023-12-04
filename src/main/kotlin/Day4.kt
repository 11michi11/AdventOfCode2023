

class Day4 {

    fun part1(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        var points = 0

        parts.forEach { line ->
            val (_, allNumbers) = line.split(":")
            val (winningPoints, numbers) = allNumbers.split("|")

            val winningList = winningPoints.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val numbersList = numbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            points += numbersList.fold(0) { acc, number ->
                if (winningList.contains(number)) {
                    if (acc == 0) {
                        return@fold 1
                    }
                    return@fold acc * 2
                }
                acc
            }

        }

        return points
    }

    fun part2(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")


        val scratchCards = mutableMapOf<Int, Triple<Int, List<Int>, List<Int>>>()

        parts.forEach { line ->
            val (cardNumberString, allNumbers) = line.split(":")
            val cardNumber = cardNumberString.substringAfter("Card ").trim().toInt()
            val (winningPoints, numbers) = allNumbers.split("|")

            val winningList = winningPoints.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val numbersList = numbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            scratchCards[cardNumber] = Triple(1, winningList, numbersList)
        }

        scratchCards.forEach { card ->
            val (cardNumber, winningAndNumbers) = card
            val (copies, winningList, numbersList) = winningAndNumbers

            repeat(copies) {
                val points = numbersList.fold(0) { acc, number ->
                    if (winningList.contains(number)) {
                        if (acc == 0) {
                            return@fold 1
                        }
                        return@fold acc + 1
                    }
                    acc
                }

                var counter = cardNumber
                repeat(points) {
                    counter++
                    val (first, second, third) = scratchCards[counter]!!
                    scratchCards[counter] = Triple(first+1, second, third)
                }
            }
        }

        return scratchCards.values.sumOf { it.first }
    }
}
