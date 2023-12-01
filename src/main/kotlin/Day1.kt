import readInputWhole

class Day1 {

    val threeLetterDigits = mapOf("one" to 1, "two" to 2, "six" to 6)
    val fourLetterDigits = mapOf("four" to 4, "five" to 5, "nine" to 9)
    val fiveLetterDigits = mapOf("three" to 3, "seven" to 7, "eight" to 8)

    fun part1(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        return parts.fold(0) { acc: Int, line ->
            val digits = line.toCharArray().filter { it.isDigit() }
            acc + "${digits.first()}${digits.last()}".toInt()
        }
    }

    fun part2(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        return parts.fold(0) { acc: Int, line ->
            val digits = ArrayList<Int>()
            for (index in line.indices) {
                val digit = findDigit(line.substring(index))
                if (digit != null) {
                    digits.add(digit)
                }
            }
            acc + "${digits.first()}${digits.last()}".toInt()
        }
    }

    fun findDigit(line: String): Int? {
        if (line.first().isDigit()) {
            return line.first().toString().toInt()
        } else if (line.length > 2 && threeLetterDigits.containsKey(line.subSequence(0, 3))) {
            return threeLetterDigits[line.subSequence(0, 3)]!!
        } else if (line.length > 3 && fourLetterDigits.containsKey(line.subSequence(0, 4))) {
            return fourLetterDigits[line.subSequence(0, 4)]!!
        } else if (line.length > 4 && fiveLetterDigits.containsKey(line.subSequence(0, 5))) {
            return fiveLetterDigits[line.subSequence(0, 5)]!!
        }
        return null
    }
}
