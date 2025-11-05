
import java.io.File
import kotlin.random.Random

fun main() {
    // Game start message
    println("Game begins:")
    
    // Load words from file and select random target word
    val words = readWordList("data/words.txt") 
    val target = pickRandomWord(words) 
    
    // Main game loop - player has 10 attempts
    for (attempt in 1..10) {
        // Get and validate player's guess
        val guess = obtainGuess(attempt) 
        // Evaluate how close the guess is to target word
        val matches = evaluateGuess(guess, target) 
        // Display feedback to player
        displayGuess(guess, matches) 
        
        // Check if player guessed the word correctly
        if (matches.all { it == 1 }) {
            println("You win!")
            return // End game if player wins
        }
    }
    
    // Player failed to guess the word in 10 attempts
    println("The word was: $target") 
}