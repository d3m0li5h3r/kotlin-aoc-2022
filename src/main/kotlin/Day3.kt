fun main() {
    fun sumChars(chars: List<Char>) =
        chars
            .sumOf {
                if (it - 'a' < 0) {
                    it - 'A' + 27
                } else {
                    it - 'a' + 1
                }
            }
            .toString()

    fun part1(lines: List<String>): String {
        val duplicates = mutableListOf<Char>()

        lines.forEach {
            val occurrences = mutableSetOf<Char>()

            val mid = it.length / 2

            for (index in 0 until mid) {
                occurrences.add(it[index])
            }

            for (index in mid until it.length) {
                if (occurrences.contains(it[index])) {
                    duplicates.add(it[index])

                    break
                }
            }
        }

        return sumChars(duplicates)
    }

    fun part2(lines: List<String>): String {
        val badges = mutableListOf<Char>()

        var index = 0
        while (index + 2 < lines.size) {
            val duplicates = mutableListOf<Char>()
            val occurrences = mutableSetOf<Char>()

            lines[index].forEach { occurrences.add(it) }

            for (char in lines[index + 1]) {
                if (occurrences.contains(char)) {
                    duplicates.add(char)
                }
            }

            for (char in lines[index + 2]) {
                if (duplicates.contains(char)) {
                    badges.add(char)

                    break
                }
            }

            index += 3
        }

        return sumChars(badges)
    }

    val testInput = readInputLines("Day03_test")
    check(part1(testInput) == "157")
    check(part2(testInput) == "70")

    val input = readInputLines("Day03")
    println(part1(input))
    println(part2(input))
}
