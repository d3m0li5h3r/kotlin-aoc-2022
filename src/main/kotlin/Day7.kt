fun main() {
    fun sizes(lines: List<String>): Map<String, Int> = buildMap {
        put("", 0)

        var pwd = ""

        for (line in lines) {
            if (line.startsWith("$ cd")) {
                pwd = when (val dir = with(line.split(" ")) { get(lastIndex) }) {
                    "/" -> ""
                    ".." -> pwd.substringBeforeLast("/", "")
                    else -> if (pwd.isEmpty()) dir else "$pwd/$dir"
                }
            } else if (line[0].isDigit()) {
                val size = line.split(" ")[0].toInt()

                var dir = pwd
                while (true) {
                    this[dir] = (this[dir] ?: 0) + size
                    if (dir.isEmpty()) break
                    dir = dir.substringBeforeLast("/", "")
                }
            }
        }
    }

    fun part1(lines: List<String>): String {
        return sizes(lines).values.filter { it <= 100000 }.sum().toString()
    }

    fun part2(lines: List<String>): String {
        val sizes = sizes(lines)

        val availableSpace = 70000000 - (sizes[""] ?: 0)

        return sizes.values.filter { availableSpace + it >= 30000000 }.min().toString()
    }

    val testInput = readInputLines("Day07_test")
    check(part1(testInput) == "95437")
    check(part2(testInput) == "24933642")

    val input = readInputLines("Day07")
    println(part1(input))
    println(part2(input))
}
