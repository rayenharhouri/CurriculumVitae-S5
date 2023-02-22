package tn.esprit.cv.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tn.esprit.cv.*
import tn.esprit.cv.fragments.HobbiesFragment
import tn.esprit.cv.fragments.LanguagesFragment
import tn.esprit.cv.fragments.SkillsFragment
import tn.esprit.cv.fragments.SummaryFragment

class ResumeActivityV2 : AppCompatActivity() {
    private lateinit var picIv : ImageView
    private lateinit var nameTv : TextView; private lateinit var emailTv : TextView
    private lateinit var skillsBtn : Button; private lateinit var hbsBtn : Button; private lateinit var langsBtn : Button
    private lateinit var careerBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_v2)
        actionBarConfig()

        picIv=findViewById(R.id.iv_pic)
        nameTv=findViewById(R.id.tv_fullname); emailTv=findViewById(R.id.tv_email);
        skillsBtn=findViewById(R.id.btn_skills); hbsBtn=findViewById(R.id.btn_hbs); langsBtn=findViewById(R.id.btn_langs)
        careerBtn=findViewById(R.id.btn_career)

        careerBtn.setOnClickListener{
            var  intent= Intent(this, CareerActivity::class.java)
            startActivity(intent)
        }
        setGeneralInfo()

        //open skillsFragment onCreate
        replaceFragment(SkillsFragment())

        skillsBtn.setOnClickListener{
            replaceFragment(SkillsFragment())
        }

        hbsBtn.setOnClickListener{
            replaceFragment(HobbiesFragment())
        }

        langsBtn.setOnClickListener{
            replaceFragment(LanguagesFragment())
        }
    }

    private fun setGeneralInfo() {
        val sharedPreference =  getSharedPreferences("GENERAL_INFO", Context.MODE_PRIVATE)
        nameTv.text=sharedPreference.getString("fullname","") //set fullname
        emailTv.text=sharedPreference.getString("email","") //set email
        var savedImagePath=sharedPreference.getString("uri","")
        Glide.with(this).load(savedImagePath).into(picIv) //set profile picture
    }

    private fun replaceFragment(fragment:Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu,menu)
        return true
    }

    private fun actionBarConfig() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        }
        toolbar.setTitleTextColor(Color.WHITE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.info_item -> {
                replaceFragment(SummaryFragment())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}