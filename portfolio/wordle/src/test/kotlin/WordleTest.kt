
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import java.io.FileNotFoundException

@Suppress("unused")
class WordleTest : StringSpec({

    // F1--- Tests for isValid(word: String) ---

    "strings with exactly 5 letters should return true" {
        isValid("crane") shouldBe true
        isValid("PLANE") shouldBe true
        isValid("SpOOn") shouldBe true
    }

    "strings with length not equal to 5 should return false" {
        isValid("") shouldBe false
        isValid("z") shouldBe false
        isValid("door") shouldBe false
        isValid("bottle") shouldBe false
    }

    "strings with digits should return false" {
        isValid("34cDe") shouldBe false
        isValid("67890") shouldBe false
        isValid("a1b2c") shouldBe false
    }

    "strings with spaces should return false" {
        isValid(" c d ") shouldBe false
        isValid("f g h ") shouldBe false
        isValid("     ") shouldBe false
    }

    "strings with special characters should return false" {
        isValid("clo#k") shouldBe false
        isValid("wo@rd") shouldBe false
    }

    // F2 --- Tests for readWordList(filename: String) ---

    "should read content correctly from existing file" {
        val testFile = File("words.txt")
        testFile.writeText("flame\nspoon")

        readWordList("words.txt") shouldBe mutableListOf("flame", "spoon")
        testFile.delete()
    }

    "should return empty list for empty file" {
        val testFile = File("empty.txt")
        testFile.writeText("")

        readWordList("empty.txt") shouldBe mutableListOf<String>()
        testFile.delete()
    }

    "should throw exception when file does not exist" {
        shouldThrow<FileNotFoundException> {
            readWordList("missing_file.txt")
        }
    }

    // F3 --- Tests for pickRandomWord(words: MutableList<String>) ---

    "edge case: empty list should throw exception" {
        val words = mutableListOf<String>()
        shouldThrow<Exception> {
            pickRandomWord(words)
        }
    }

   "normal case: should return word and remove from list" {
    val words = mutableListOf("flame", "spoon", "crane")
    val result = pickRandomWord(words)

    result.length shouldBe 5
    result.all { it.isLetter() } shouldBe true
    words.size shouldBe 2
}

    "edge case: single word list" {
    val words = mutableListOf("brick")
    val result = pickRandomWord(words)

    result.uppercase() shouldBe "BRICK"
    
    words.isEmpty() shouldBe true
}

    // F4 --- Tests for case and uppercase conversion ---

    "should validate 5-letter words correctly" {
        isValid("crane") shouldBe true
        isValid("PLANE") shouldBe true
        isValid("SPOON") shouldBe true
    }

    "should validate mixed case words" {
        isValid("Crane") shouldBe true
        isValid("PlAnE") shouldBe true
        isValid("SpOOn") shouldBe true
    }

    "should reject invalid words" {
        isValid("") shouldBe false
        isValid("x") shouldBe false
        isValid("door") shouldBe false
        isValid("bottle") shouldBe false
        isValid("clo#k") shouldBe false
    }

    "should convert to uppercase correctly" {
        "crane".uppercase() shouldBe "CRANE"
        "spoon".uppercase() shouldBe "SPOON"
        "test1".uppercase() shouldBe "TEST1"
    }
})