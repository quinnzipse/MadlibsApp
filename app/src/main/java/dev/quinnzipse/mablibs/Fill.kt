package dev.quinnzipse.mablibs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_fill.*

class Fill : AppCompatActivity() {
    private var wordsLeft: Int = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill)
        words_left.text = String.format(getString(R.string.fill_left), wordsLeft)
        nextButton.setOnClickListener { onNextButton() }
        showFill()
    }

    private fun onNextButton() {
        val input = input.text
        Log.d("QIN", "$input - $wordsLeft")

        wordsLeft--
        words_left.text = String.format(getString(R.string.fill_left), wordsLeft)

        input.clear()

        if (wordsLeft == 0) {
            showStory();
        }
    }

    private fun showStory() {

    }

    private fun showFill() {
        val fragment = FillFragment()
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container, fragment)
        trans.commit()
    }
}