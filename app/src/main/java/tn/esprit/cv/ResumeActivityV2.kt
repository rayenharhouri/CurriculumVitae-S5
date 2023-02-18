package tn.esprit.cv

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ResumeActivityV2 : AppCompatActivity() {
    private lateinit var picIv : ImageView
    private lateinit var nameTv : TextView; private lateinit var emailTv : TextView
    private lateinit var skillsBtn : Button; private lateinit var hbsBtn : Button; private lateinit var langsBtn : Button ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_v2)

        picIv=findViewById(R.id.iv_pic)
        nameTv=findViewById(R.id.tv_fullname); emailTv=findViewById(R.id.tv_email);
        skillsBtn=findViewById(R.id.btn_skills); hbsBtn=findViewById(R.id.btn_hbs); langsBtn=findViewById(R.id.btn_langs)

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