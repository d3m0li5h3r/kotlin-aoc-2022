fun main() {
    fun sections(line: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val sections = line.split(",")

        val section1 = sections[0].split("-")
        val section1Pair = Pair(section1[0].toInt(), section1[1].toInt())

        val section2 = sections[1].split("-")
        val section2Pair = Pair(section2[0].toInt(), section2[1].toInt())

        return Pair(section1Pair, section2Pair)
    }

    fun part1(lines: List<String>): String {
        var overlapCount = 0

        lines.forEach {
            val sections = sections(it)

            if ((sections.first.first >= sections.second.first && sections.first.second <= sections.second.second)
                || (sections.second.first >= sections.first.first && sections.second.second <= sections.first.second)
            ) {
                overlapCount++
            }
        }

        return overlapCount.toString().also { println("count: $overlapCount") }
    }

    fun part2(lines: List<String>): String {
        fun Pair<Int, Int>.toRange(): IntRange =
            IntRange(first, second)

        var overlapCount = 0

        lines.forEach {
            val sections = sections(it)

            if (
                sections.first.first in sections.second.toRange()
                || sections.first.second in sections.second.toRange()
                || sections.second.first in sections.first.toRange()
                || sections.second.second in sections.first.toRange()
            ) {
                overlapCount++
            }
        }
        return overlapCount.toString()
    }

    val testInput = readInputLines("Day04_test")
    check(part1(testInput) == "2")
    check(part2(testInput) == "4")

    val input = readInputLines("Day04")
    println(part1(input))
    println(part2(input))
}
