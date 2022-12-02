import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readInputString(name: String): String = File("src/main/kotlin/inputs", name).readText()

fun readInputLines(name: String): List<String> = File("src/main/kotlin/inputs", name).readLines()

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
