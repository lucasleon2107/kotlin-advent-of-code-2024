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

        val similarityArray = mutableListOf<Int>()

        leftArray.forEach { number ->
            similarityArray += rightArray.filter { it == number }.size * number
        }

        return similarityArray.sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
