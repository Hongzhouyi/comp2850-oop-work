import java.io.File
import kotlin.random.Random

// Function 1: isValid(word: String): Boolean
// Checks if the given word is valid for Wordle.
// A valid word must contain exactly 5 alphabetic letters (A–Z or a–z).
fun isValid(word: String): Boolean {
    return word.length == 5 && word.all { it.isLetter() }
}

// Function 2: readWordList(filename: String): MutableList<String>
// Reads words from the specified file and returns them as a mutable list.

fun readWordList(filename: String): MutableList<String> {
    return File(filename).readLines().toMutableList()
}

// Function 3: pickRandomWord(words: MutableList<String>): String
// Selects a random word from the given list, removes it from the list, and returns it.

fun pickRandomWord(words: MutableList<String>): String {
    if (words.isEmpty()) {
        throw IllegalArgumentException("Cannot pick from empty list")
    }
    // Select a random word from the list
    val selectedWord = words.random()

    // Remove the selected word from the list to prevent reuse
    words.remove(selectedWord)

    // Return the selected word in uppercase for consistency
    return selectedWord.uppercase()
}

// Function 4: obtainGuess(attempt: Int): String
// Prompts the user to input a word for the given attempt number.
// If the word is invalid, it keeps asking until a valid word is entered.

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val userGuess = readLine()?.trim() ?: ""
        if (isValid(userGuess)) {
            return userGuess.uppercase()
        } else {
            println("Invalid word. Please enter exactly 5 letters (A–Z).")
        }
    }
}

// Function 5: evaluateGuess(guess: String, target: String): List<Int>
// Compares the guessed word with the target word.
// Returns a list of integers (0 or 1) indicating whether each letter matches.
// 1 = correct letter in correct position, 0 = incorrect letter
fun evaluateGuess(guess: String, target: String): List<Int> {
    // Create a mutable list to store the match results for each position
    val guessResult = mutableListOf<Int>()   
    // Iterate through each position in the 5-letter word
    for (i in 0 until 5) {
        // Check if the letter at current position matches the target word
        if (guess[i] == target[i]) {
            // Add 1 to indicate correct letter in correct position
            guessResult.add(1)
        } else {
            // Add 0 to indicate incorrect letter at this position
            guessResult.add(0)
        }
    }
    // Return the complete list of match results
    return guessResult
}

// Function 6: displayGuess(guess: String, matches: List<Int>)
// Displays matching letters and replaces incorrect ones with '?'.

fun displayGuess(guess: String, matches: List<Int>) {
    var result = ""
for (i in 0 until 5) {
    if (matches[i] == 1) {
        result += guess[i]
    } else {
        result += '?'
    }
}
    println(result)
}
