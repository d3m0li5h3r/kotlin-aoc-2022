fun main() {
    fun containsDuplicate(line: String, left: Int, right: Int, size: Int): Boolean {
        val set = mutableSetOf<Char>()

        for (index in left..right) {
            set.add(line[index])
        }

        return set.size != size
    }

    fun getUniqueSubstringStart(line: String, length: Int): Int {
        var left = 0
        var right = left + length - 1

        while (right < line.length) {
            if (!containsDuplicate(line, left, right, length)) {
                return right + 1
            }

            left++
            right++
        }

        return 0
    }

    fun part1(line: String): String = getUniqueSubstringStart(line, 4).toString()

    fun part2(line: String): String = getUniqueSubstringStart(line, 14).toString()

    val testInput = readInputString("Day06_test")
    check(part1(testInput) == "7")
    check(part2(testInput).also { println(it) } == "19")

    val input = readInputString("Day06")
    println(part1(input))
    println(part2(input))
}
