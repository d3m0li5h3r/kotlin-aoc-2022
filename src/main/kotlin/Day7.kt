fun main() {
    fun part1(line: String): String {
        return ""
    }

    fun part2(line: String): String {
        return ""
    }

    val testInput = readInputString("Day07_test")
    check(part1(testInput) == "")
    check(part2(testInput).also { println(it) } == "")

    val input = readInputString("Day07")
    println(part1(input))
    println(part2(input))
}
