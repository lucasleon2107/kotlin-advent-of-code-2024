import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val leftArray = input.map { it.split("   ").first().toInt() }.sorted()
        val rightArray = input.map { it.split("   ").last().toInt() }.sorted()

        val distancesArray = mutableListOf<Int>()

        for (i in 0..leftArray.lastIndex) {
            distancesArray += abs(leftArray[i] - rightArray[i])
        }

        return distancesArray.sum()
    }

    fun part2(input: List<String>): Int {
        val leftArray = input.map { it.split("   ").first().toInt() }
        val rightArray = input.map { it.split("   ").last().toInt() }

        return leftArray
            .groupingBy { it }
            .eachCount()
            .map { (number, _) ->
                number * (rightArray.count() { it == number })
            }
            .sum()
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
