import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val safeReports = mutableListOf<Boolean>()

        input.forEach { line ->
            if(isSafe(toIntList(line))) {
                safeReports += true
            }
        }

        return safeReports.size
    }

    fun part2(input: List<String>): Int {
        var safeReports = 0

        for (line in input) {
            val numbers = toIntList(line)
            var safe = false
            for(i in 0..numbers.lastIndex) {
                safe = isSafe(numbers.toMutableList().apply { removeAt(i) })
                if(safe) {
                    break
                }
            }

            if(safe) safeReports++
        }

        return safeReports
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun toIntList(input: String): List<Int> {
    val result = mutableListOf<Int>()

    val stringNumbers = input.split(" ")

    stringNumbers.forEach {
        result += it.toInt()
    }

    return result
}

fun isMonotonic(input: List<Int>): Boolean {
    var isDecreasing = true
    var isIncreasing = true

    for(i in 0 until input.lastIndex) {
        if(input[i] < input[i + 1]) { isDecreasing = false}
        if(input[i] > input[i + 1]) { isIncreasing = false}
        if(input[i] == input[i + 1]) { return false}
    }

    return isDecreasing || isIncreasing
}

fun isDiffGreaterThan3(num1: Int, num2: Int): Boolean {
    return abs(num1 - num2) > 3
}

fun isSafe(input: List<Int>): Boolean {
    if(!isMonotonic(input)) {
        return false
    }

    for (i in 1..input.lastIndex) {
        if (isDiffGreaterThan3(input[i], input[i - 1])) {
            return false
        }
    }

    return true
}
