fun main() {
    fun getPairsOfNumbers(line: String): List<Pair<Int, Int>> {
        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        return regex.findAll(line)
            .mapNotNull { matchResult ->
                val (x, y) = matchResult.destructured
                try {
                    Pair(x.toInt(), y.toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
            .toList()
    }


    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val pairOfNumbers = getPairsOfNumbers(line)
            pairOfNumbers.forEach { (first, second) ->
                result += first * second
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val s = input.joinToString("")
        val pattern = Regex("""do\(\)|don't\(\)|mul\((\d+),(\d+)\)""")
        val matches = pattern.findAll(s)

        val (sum, _) = matches.fold((0 to true)) { acc, match ->
            if (match.value == "do()") {
                acc.first to true
            } else if (match.value == "don't()") {
                acc.first to false
            } else if (!acc.second) {
                acc
            } else {
                val x = match.groupValues[1].toInt()
                val y = match.groupValues[2].toInt()
                acc.first + x * y to true
            }
        }

        return sum
    }

    val testInput = readInput("Day03_test_part1")
    check(part1(testInput) == 161)

    val testInput2 = readInput("Day03_test_part2")
    check(part2(testInput2) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
