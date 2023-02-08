package tn.esprit.cv

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResumeActivity : AppCompatActivity() {
    private lateinit var nameTv : TextView; private lateinit var emailTv : TextView; private lateinit var ageTv : TextView ; private lateinit var genderTv : TextView
    private lateinit var androidTv : TextView; private lateinit var iosTv : TextView; private lateinit var flutterTv : TextView ;
    private lateinit var langTv : TextView; private lateinit var hbsTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume)
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.your_resume_title)

        nameTv=findViewById(R.id.tv_name); ageTv=findViewById(R.id.tv_age); emailTv=findViewById(R.id.tv_email); genderTv=findViewById(R.id.tv_gender)
        androidTv=findViewById(R.id.tv_android); iosTv=findViewById(R.id.tv_ios); flutterTv=findViewById(R.id.tv_flutter)
        langTv=findViewById(R.id.tv_lang); hbsTv=findViewById(R.id.tv_hbs)

        setGeneralInfo()
        setSkills()
    }
    private fun setSkills() {
        val sp="SKILLS_INFO"
        setTextViewText(sp,"languages",langTv,R.string.languages)
        setTextViewText(sp,"hobbies",hbsTv,R.string.hobbies)
        setTextViewText(sp,"android",androidTv,R.string.android)
        setTextViewText(sp,"ios",iosTv,R.string.ios)
        setTextViewText(sp,"flutter",flutterTv,R.string.flutter)
    }
    private fun setGeneralInfo() {
        val sp="GENERAL_INFO"
        setTextViewText(sp,"fullname",nameTv,R.string.name)
        setTextViewText(sp,"email",emailTv,R.string.email)
        setTextViewText(sp,"age",ageTv,R.string.age)
        setTextViewText(sp,"gender",genderTv,R.string.gender)
    }
    private fun setTextViewText(sp:String,key:String,tv:TextView,resId:Int){
        val sharedPreference =  getSharedPreferences(sp, Context.MODE_PRIVATE)
        var value=sharedPreference.getString(key,"")
        tv.text=getString(resId)+": "+value
    }
}