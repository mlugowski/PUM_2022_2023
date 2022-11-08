package pl.edu.uwr.pum.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions: List<Question> = listOf(
        Question("Czy w prawie Ohma, opór wyraża się jako R = U/I?", 1),
        Question("Czy pierwsze prawo Kirchoffa odnosi się do napięć w poszczególnych oczkach/układzie?", 0),
        Question("Czy natężenie prądu elektrycznego to stosunek ilości ładunku elektrycznego do czasu?", 1),
        Question("Czy jednostką natężenie prądu elektrycznego jest wolt?", 0),
        Question("Czy opór właściwy jest zależny w jakikolwiek sposób od długości przewodnika?", 1),
        Question("Czy opór w połączeniu szeregowym jest sumą oporów?", 1),
        Question("Czy wzór na moc prądu elektrycznego to P = U*I?", 1),
        Question("Czy natężenie prądu w układzie z opornikiem 1k i napięciem 5V wykosi 4A?", 0),
        Question("Czy opór właściwy srebra jest większy niż opór właściwy żelaza?", 0),
        Question("Czy w gniazdku elektrycznym znajduje się prąd stały?", 0)

    )
}

class MainActivity : AppCompatActivity() {
    private var currentPosition = 0
    private var points = 0
    private var correctAnswers = 0
    private var cheatsCount = 0
    private val questions: List<Question> = Questions.questions
    private val questionText: TextView by lazy {findViewById(R.id.Question)}
    private val answerYes: Button by lazy { findViewById(R.id.Yes) }
    private val answerNo: Button by lazy { findViewById(R.id.No) }
    private val cheat: Button by lazy { findViewById(R.id.Cheat) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setQuestion()
        answerYes.setOnClickListener{ answerButton(1) }
        answerNo.setOnClickListener{ answerButton(0) }
        cheat.setOnClickListener { cheatButton() }
    }

    private fun setQuestion() {
        questionText.text = questions[currentPosition].question
    }

    @SuppressLint("SetTextI18n")
    private fun answerButton(answer: Int) {
        if (answer == questions[currentPosition].correctAnswer) {
            points += 10
            correctAnswers += 1
        }
        if (currentPosition < 9) {
            currentPosition += 1

            setQuestion()
        }
        else {
            answerYes.visibility = View.GONE
            answerNo.visibility = View.GONE
            cheat.visibility = View.GONE
            questionText.text = "Points = $points\n" +
                    "Correct answers = $correctAnswers\n" +
                    "Cheats count = $cheatsCount"
        }
    }

    private fun cheatButton() {
        points -= 15
        cheatsCount += 1
        val bdl = Bundle()
        bdl.putString("question", questions[currentPosition].question)
        bdl.putInt("answer", questions[currentPosition].correctAnswer)

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtras(bdl)
        startActivity(intent)
    }
}