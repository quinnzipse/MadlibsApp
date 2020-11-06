package dev.quinnzipse.mablibs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fill.*

class Fill : AppCompatActivity() {
    private var wordsLeft: Int = 7
    private val words: Int = wordsLeft - 1
    private lateinit var list: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill)
        words_left.text = String.format(getString(R.string.fill_left), wordsLeft)
        nextButton.setOnClickListener { onNextButton() }
        list = Array(wordsLeft) { "" }
    }

    private fun onNextButton() {
        val input = input.text

        wordsLeft--
        words_left.text = String.format(getString(R.string.fill_left), wordsLeft)

        list[words - wordsLeft] = input.toString()
        input.clear()

        if (wordsLeft == 0) showStory()
    }

    private fun showStory() {
        Intent(this, FilledStory::class.java).also {
            it.putExtra("List", list)
            startActivity(it)
        }
    }
}