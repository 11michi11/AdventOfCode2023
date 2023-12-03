import readInputWhole

class Day2 {

    fun part1(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        var total = 0;
        parts.forEach { line ->
            val split = line.split(":")
            val game = split[0].substringAfter("Game ").substringBefore(":").toInt()

            val sets = split[1].split(";")

            var isGameGood = true
            sets.forEach { set ->
                if (!isSetGood(set)) {
                    isGameGood = false
                }
            }
            if (isGameGood) {
                total += game
            }


        }
        return total
    }

    fun isSetGood(set: String): Boolean {
        val colors = set.split(",")
        for (colorSet in colors) {
            val (value, color) = colorSet.trim().split(" ")
            if (color == "blue" && value.toInt() > 14) {
                return false
            } else if (color == "red" && value.toInt() > 12) {
                return false
            } else if (color == "green" && value.toInt() > 13) {
                return false
            }
        }

        return true
    }

    fun part2(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        var total = 0;
        parts.forEach { line ->
            val split = line.split(":")
            val sets = split[1].split(";")

            var stats =
                mutableMapOf("blue" to 0, "red" to 0, "green" to 0)

            sets.forEach { set ->
                stats = findNumberOfCubes(set, stats)
            }
            total += stats.values.reduce { acc, i -> acc * i }
        }
        return total
    }


    fun findNumberOfCubes(set: String, stats: MutableMap<String, Int>): MutableMap<String, Int> {
        val colors = set.split(",")
        for (colorSet in colors) {
            val (value, color) = colorSet.trim().split(" ")
            if (stats[color]!! < value.toInt()) {
                stats[color] = value.toInt()
            }
        }

        return stats
    }
}
