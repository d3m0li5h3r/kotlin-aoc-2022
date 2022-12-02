fun main() {
    val outcomes = mapOf<String, Int>(
        "A X" to 4,
        "B X" to 1,
        "C X" to 7,
        "A Y" to 8,
        "B Y" to 5,
        "C Y" to 2,
        "A Z" to 3,
        "B Z" to 9,
        "C Z" to 6
    )

    val points = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )

    val wins = mapOf(
        "A" to "Y",
        "B" to "Z",
        "C" to "X"
    )

    val loss = mapOf(
        "A" to "Z",
        "B" to "X",
        "C" to "Y"
    )

    val scores = mapOf(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    fun part1(lines: List<String>): String {
        return lines.sumOf { outcomes[it] ?: 0 }.toString()
    }

    fun part2(lines: List<String>): String {
        return lines.sumOf { match ->
            val moves = match.split(" ")
            val player1 = moves[0]
            val result = scores[moves[1]]!!

            val score = when (result) {
                0 -> points[loss[player1]!!]!!
                6 -> points[wins[player1]!!]!!
                else -> points[player1]!!
            }

            score + result
        }.toString()
    }

    val testInput = readInputLines("Day02_test")
    check(part1(testInput) == "15")
    check(part2(testInput) == "12")

    val input = readInputLines("Day02")
    println(part1(input))
    println(part2(input))
}
