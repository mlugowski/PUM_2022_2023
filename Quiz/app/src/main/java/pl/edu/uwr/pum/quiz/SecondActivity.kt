package pl.edu.uwr.pum.quiz

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private val answerText: TextView by lazy { findViewById(R.id.Answer) }
    private val searchButton: Button by lazy { findViewById(R.id.Search) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bdl = intent.extras
        val answer = bdl?.getInt("answer")
        val question = bdl?.getString("question")
        if (answer == 1) {
            answerText.text = "YES"
        }
        else {
            answerText.text = "NO"
        }
        searchButton.setOnClickListener {
            if (question != null) {
                search(question)
            }
        }
    }

    private fun search(question: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, question)
        startActivity(intent)
    }
}