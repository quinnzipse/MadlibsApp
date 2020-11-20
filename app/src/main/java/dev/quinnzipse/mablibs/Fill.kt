package dev.quinnzipse.mablibs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fill.*
import java.io.BufferedReader
import java.io.InputStreamReader

class Fill : AppCompatActivity() {

    private var wordsRead: Int = 0
    private var words: Int = -1
    private lateinit var list: Array<String>

    private lateinit var inString: String
    private lateinit var matches: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill)

        // read the file
        inString = readFile()

        // Prep the backend.
        matches = getFillables()
        words = matches.count()
        list = Array(words) { "" }

        // Initialize the UI.
        words_left.text = String.format(getString(R.string.fill_left), words)
        subtext.text = String.format(getString(R.string.fill_subtext), getMatch(0))

        nextButton.setOnClickListener { onNextButton() }
    }

    private fun readFile(): String {
        val inFile = resources.openRawResource(R.raw.madlib1)
        return BufferedReader(InputStreamReader(inFile)).use { it.readText() }
    }

    private fun getFillables(): Array<String> {
        val regex = Regex("""<\S*>""")
        return regex.findAll(inString).map { it.value }.toList().toTypedArray()
    }

    private fun getMatch(i: Int): String {
        return matches.elementAt(i).drop(1).dropLast(1).replace("-", " ")
    }

    private fun onNextButton() {
        val input = input.text

        list[wordsRead] = input.toString()
        input.clear()

        wordsRead++

        // Update the number of words remaining.
        words_left.text = String.format(getString(R.string.fill_left), words - wordsRead)

        // If there are more words, update the remaining words.
        if (wordsRead == words) showStory()
        else subtext.text = String.format(getString(R.string.fill_subtext), getMatch(wordsRead))
    }

    private fun showStory() {
        Intent(this, FilledStory::class.java).also {
            it.putExtra("List", list)
            it.putExtra("Story", inString)
            it.putExtra("Fillables", matches)
            startActivity(it)
        }
    }
}