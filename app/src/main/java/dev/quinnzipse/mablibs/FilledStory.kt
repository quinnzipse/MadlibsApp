package dev.quinnzipse.mablibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_filled_story.*

class FilledStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filled_story)
        val list = intent.getStringArrayExtra("List").orEmpty()
        val fillables = intent.getStringArrayExtra("Fillables").orEmpty()
        var raw = intent.getStringExtra("Story").orEmpty()

        for (x in list.indices) {
            raw = raw.replaceFirst(Regex(fillables[x]), list[x])
        }

        story.text = raw
    }

    fun restart(view: View) {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }
}