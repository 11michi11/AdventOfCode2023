class Day3 {

    fun part1(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        val mapLength = parts.size
        val mapWidth = parts[0].length

        val engineParts = mutableListOf<Int>()

        for (i in parts.indices) {
            var length = 0
            do {
                val remainingLine = parts[i].substring(length)
                val triple = findNumber(remainingLine)
                val number = triple.first
                length += triple.second
                val numberLength = triple.third


                // Skip
                if (number != 0) {
                    for (cursor in length..<length + numberLength) {
                        // find character around
                        if ((i - 1) >= 0 && (cursor - 1) >= 0 && isPart((parts[i - 1][cursor - 1]))) {
                            engineParts.add(number)
                            break
                        } else if ((i - 1) >= 0 && isPart((parts[i - 1][cursor]))) {
                            engineParts.add(number)
                            break
                        } else if ((i - 1) >= 0 && (cursor + 1) < mapWidth && isPart((parts[i - 1][cursor + 1]))) {
                            engineParts.add(number)
                            break
                        } else if ((cursor - 1) >= 0 && isPart((parts[i][cursor - 1]))) {
                            engineParts.add(number)
                            break
                        } else if ((cursor + 1) < mapWidth && isPart((parts[i][cursor + 1]))) {
                            engineParts.add(number)
                            break
                        } else if ((i + 1) < mapLength && (cursor - 1) >= 0 && isPart((parts[i + 1][cursor - 1]))) {
                            engineParts.add(number)
                            break
                        } else if ((i + 1) < mapLength && isPart((parts[i + 1][cursor]))) {
                            engineParts.add(number)
                            break
                        } else if ((i + 1) < mapLength && (cursor + 1) < mapWidth && isPart((parts[i + 1][cursor + 1]))) {
                            engineParts.add(number)
                            break
                        }
                        // No symbol around, move on
                    }


                }

                // Move course after the number
                length += numberLength

            } while (remainingLine.isNotEmpty())


        }

        return engineParts.sum()
    }

    fun findNumber(line: String): Triple<Int, Int, Int> {
        var digits = ""
        var digitLength = 0
        var lineLength = 0
        for (j in line.indices) {
            if (line[j].isDigit()) {

                digitLength++
                digits += line[j]
                while ((digitLength + j) < line.length && line[j + digitLength].isDigit()) {
                    digits += line[j + digitLength]
                    digitLength++
                }
                return Triple(digits.toInt(), lineLength, digitLength)
            }
            lineLength++
        }

        return Triple(0, lineLength, 0)
    }

    fun isPart(element: Char): Boolean {
        return !element.isDigit() && element != '.'
    }


    fun part2(filename: String): Int {
        val input = readInputWhole(filename)
        val parts = input.split("\r\n")

        val mapLength = parts.size
        val mapWidth = parts[0].length

        val engineParts = mutableListOf<Int>()

        val gears = mutableMapOf<Pair<Int, Int>, List<Int>>()

        for (i in parts.indices) {
            var length = 0
            do {
                val remainingLine = parts[i].substring(length)
                val triple = findNumber(remainingLine)
                val number = triple.first
                length += triple.second
                val numberLength = triple.third


                // Skip
                if (number != 0) {
                    for (cursor in length..<length + numberLength) {
                        // find character around
                        if ((i - 1) >= 0 && (cursor - 1) >= 0 && isPart(parts[i - 1][cursor - 1])) {
                            engineParts.add(number)
                            if (parts[i - 1][cursor - 1] == '*') {
                                gears[Pair(i - 1, cursor - 1)] = gears.getOrDefault(Pair(i - 1, cursor - 1), listOf()) + number
                            }
                            break
                        } else if ((i - 1) >= 0 && isPart(parts[i - 1][cursor])) {
                            engineParts.add(number)
                            if (parts[i - 1][cursor] == '*') {
                                gears[Pair(i - 1, cursor)] = gears.getOrDefault(Pair(i - 1, cursor), listOf()) + number
                            }
                            break
                        } else if ((i - 1) >= 0 && (cursor + 1) < mapWidth && isPart(parts[i - 1][cursor + 1])) {
                            engineParts.add(number)
                            if (parts[i - 1][cursor + 1] == '*') {
                                gears[Pair(i - 1, cursor + 1)] = gears.getOrDefault(Pair(i - 1, cursor + 1), listOf()) + number
                            }
                            break
                        } else if ((cursor - 1) >= 0 && isPart(parts[i][cursor - 1])) {
                            engineParts.add(number)
                            if (parts[i][cursor - 1] == '*') {
                                gears[Pair(i, cursor - 1)] = gears.getOrDefault(Pair(i, cursor - 1), listOf()) + number
                            }
                            break
                        } else if ((cursor + 1) < mapWidth && isPart(parts[i][cursor + 1])) {
                            engineParts.add(number)
                            if (parts[i][cursor + 1] == '*') {
                                gears[Pair(i, cursor + 1)] = gears.getOrDefault(Pair(i, cursor + 1), listOf()) + number
                            }
                            break
                        } else if ((i + 1) < mapLength && (cursor - 1) >= 0 && isPart(parts[i + 1][cursor - 1])) {
                            engineParts.add(number)
                            if (parts[i + 1][cursor - 1] == '*') {
                                gears[Pair(i + 1, cursor - 1)] = gears.getOrDefault(Pair(i + 1, cursor - 1), listOf()) + number
                            }
                            break
                        } else if ((i + 1) < mapLength && isPart(parts[i + 1][cursor])) {
                            engineParts.add(number)
                            if (parts[i + 1][cursor] == '*') {
                                gears[Pair(i + 1, cursor)] = gears.getOrDefault(Pair(i + 1, cursor), listOf()) + number
                            }
                            break
                        } else if ((i + 1) < mapLength && (cursor + 1) < mapWidth && isPart(parts[i + 1][cursor + 1])) {
                            engineParts.add(number)
                            if (parts[i + 1][cursor + 1] == '*') {
                                gears[Pair(i + 1, cursor + 1)] = gears.getOrDefault(Pair(i + 1, cursor + 1), listOf()) + number
                            }
                            break
                        }
                        // No symbol around, move on
                    }


                }

                // Move course after the number
                length += numberLength

            } while (remainingLine.isNotEmpty())


        }

        return gears.filterValues { it.size == 2 }.map { it.value.foldRight(1) {acc, that -> acc * that } }.sum()
    }
}
