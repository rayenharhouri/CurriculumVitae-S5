package tn.esprit.cv.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tn.esprit.cv.R
import tn.esprit.cv.fragments.EducationFragment
import tn.esprit.cv.fragments.ExperienceFragment


class CareerActivity : AppCompatActivity() {
    lateinit var btnExperience:Button;lateinit var btnEducation:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)
        actionBarConfig()
        btnExperience=findViewById(R.id.btn_experience);btnEducation=findViewById(R.id.btn_education)
        replaceFragment(ExperienceFragment())
        btnExperience.setOnClickListener{
            replaceFragment(ExperienceFragment())
        }
        btnEducation.setOnClickListener{
            replaceFragment(EducationFragment())
        }

    }

    private fun actionBarConfig() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.title="Your Career"
        toolbar.setTitleTextColor(Color.WHITE)
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }
}