package dev.quinnzipse.mablibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_filled_story.*

class FilledStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filled_story)
        val list = intent.getStringArrayExtra("List")?.joinToString(", ")
        Log.d("QIN", list.orEmpty())
        story.text = list.orEmpty()
    }
}