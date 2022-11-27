package pl.edu.uwr.pum.crimes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Crime(
    val title: String,
    val content: String,
    val studentIndex: Int,
    val date: String,
    val isSolved: Boolean
)

object Crimes {
    val crimes: List<Crime> = listOf(
        Crime("Spóźnienie", "Spóźnił się na zajęcia.", 322500, "1-10-2022" , true),
        Crime("Spóźnienie", "Spóźnił się na zajęcia.", 322500, "11-10-2022", false),
        Crime("Ucieczka", "Uciekł z zajeć.", 322500, "1-10-2022" , true),
        Crime("Ucieczka", "Uciekł z zajeć.", 322500, "11-10-2022", false),
        Crime("Ściąganie", "Ściągał na egzaminie.", 322500, "1-10-2022" , true),
        Crime("Ściąganie", "Ściągał na kolokwium.", 322500, "11-10-2022", false),
        Crime("Ściąganie", "Ściągał z telefonu.", 322500, "1-10-2022" , true),
        Crime("Ściąganie", "Ściągał z telefonu.", 322500, "11-10-2022", false),
        Crime("Terminowość", "Student spoźnił się tydzień z oddaniem pracy.", 322500, "1-10-2022" , true),
        Crime("Terminowość", "Student spoźnił się dzień z oddaniem pracy.", 322500, "11-10-2022", false),
        Crime("Terminowość", "Student spoźnił się tydzień z oddaniem pracy.", 322500, "1-10-2022" , true),
        Crime("Spóźnienie", "Spóźnił się na zajęcia.", 322500, "1-10-2022" , true),
        Crime("Spóźnienie", "Spóźnił się na zajęcia.", 322500, "11-10-2022", false),
        Crime("Ucieczka", "Uciekł z zajeć.", 322500, "1-10-2022" , true),
        Crime("Ucieczka", "Uciekł z zajeć.", 322500, "11-10-2022", false),
        Crime("Ściąganie", "Ściągał na egzaminie.", 322500, "1-10-2022" , true),
        Crime("Ściąganie", "Ściągał na kolokwium.", 322500, "11-10-2022", false),
        Crime("Ściąganie", "Ściągał z telefonu.", 322500, "1-10-2022" , true),
        Crime("Ściąganie", "Ściągał z telefonu.", 322500, "11-10-2022", false),
        Crime("Terminowość", "Student spoźnił się tydzień z oddaniem pracy.", 322500, "1-10-2022" , true),
        Crime("Terminowość", "Student spoźnił się dzień z oddaniem pracy.", 322500, "11-10-2022", false),
        Crime("Terminowość", "Student spoźnił się tydzień z oddaniem pracy.", 322500, "1-10-2022" , true),
    )
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}