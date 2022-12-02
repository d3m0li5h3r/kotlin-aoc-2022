fun main() {
    fun part1(lines: String): String {
        return lines.split("\n\n")
            .map { group ->
                val sum = group.split("\n").sumOf {
                    it.toIntOrNull() ?: 0
                }

                sum
            }
            .max()
            .toString()
    }

    fun part2(lines: String): String {
        return lines.split("\n\n")
            .map { group ->
                val sum = group.split("\n").sumOf {
                    it.toIntOrNull() ?: 0
                }

                sum
            }
            .sortedDescending()
            .take(3)
            .sum()
            .toString()
    }

    val testInput = readInputString("Day01_test")
    check(part1(testInput) == "24000")
    check(part2(testInput) == "45000")

    val input = readInputString("Day01")
    println(part1(input))
    println(part2(input))
}

