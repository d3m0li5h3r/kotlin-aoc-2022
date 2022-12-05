import java.util.*

data class Instruction(val count: Int, val from: Int, val to: Int)

data class Parsed(val stacks: List<Stack<Char>>, val instruction: List<Instruction>)

fun main() {
    fun parse(lines: List<String>): Parsed {
        var containers = ""
        var stackCount: Int = 0
        var startIndex: Int = 0
        for (index in lines.indices) {
            val normalized = lines[index].replace("    ", " - ")
            if (normalized.trim().startsWith("1")) {
                startIndex = index + 2
                stackCount = normalized.trim().last() - '0'

                break
            }

            containers += "\n ${normalized.trim()}"
        }

        val stacks = mutableListOf<Stack<Char>>()

        for (index in 0 until stackCount) {
            stacks.add(Stack())
        }

        containers
            .split("\n")
            .filter { it.isNotBlank() }
            .forEach { column ->
                column.split(" ")
                    .filter { it.isNotBlank() }
                    .forEachIndexed { index, s ->
                        if (s != "-") {
                            stacks[index].push(s.replace("[", "").replace("]", "").first())
                        }
                    }
            }

        stacks.forEach {
            it.reverse()
        }

        val instructions = mutableListOf<Instruction>()

        for (index in startIndex until lines.size) {
            val instruction = lines[index]
                .split(" ")
                .filter { it.toIntOrNull() != null }

            instructions.add(
                Instruction(
                    instruction[0].toInt(),
                    instruction[1].toInt() - 1,
                    instruction[2].toInt() - 1
                )
            )
        }

        return Parsed(stacks, instructions)
    }

    fun topOfAllStacks(stacks: List<Stack<Char>>): String =
        stacks.map { it.peek() }.joinToString("")

    fun part1(lines: List<String>): String {
        val (stacks, instructions) = parse(lines)

        instructions.forEach {
            for (index in 0 until it.count) {
                stacks[it.to].push(stacks[it.from].pop())
            }
        }

        return topOfAllStacks(stacks)
    }

    fun part2(lines: List<String>): String {
        val (stacks, instructions) = parse(lines)

        instructions.forEach {
            val popStack = Stack<Char>()
            for (index in 0 until it.count) {
                popStack.push(stacks[it.from].pop())
            }
            while(!popStack.empty()) {
                stacks[it.to].push(popStack.pop())
            }
        }

        return topOfAllStacks(stacks)
    }

    val testInput = readInputLines("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInputLines("Day05")
    println(part1(input))
    println(part2(input))
}
