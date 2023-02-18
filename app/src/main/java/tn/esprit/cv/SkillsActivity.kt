package tn.esprit.cv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class SkillsActivity : AppCompatActivity() {
    private lateinit var androidSb : SeekBar; private lateinit var iosSb : SeekBar; private lateinit var flutterSb : SeekBar
    private lateinit var arCb : CheckBox; private lateinit var frCb : CheckBox; private lateinit var enCb : CheckBox
    private lateinit var musicCb : CheckBox; private lateinit var sportCb : CheckBox; private lateinit var gamesCb : CheckBox
    private lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills)
        androidSb=findViewById(R.id.sb_android); iosSb=findViewById(R.id.sb_ios); flutterSb=findViewById(R.id.sb_flutter)
        arCb=findViewById(R.id.cb_ar); frCb=findViewById(R.id.cb_fr); enCb=findViewById(R.id.cb_en)
        musicCb=findViewById(R.id.cb_music); sportCb=findViewById(R.id.cb_sport); gamesCb=findViewById(R.id.cb_games)
        submitBtn=findViewById(R.id.btn_submit)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.create_resume_title)

        //ON CLICK SUBMIT
        submitBtn.setOnClickListener{
            saveData()
            var  intent= Intent(this, ResumeActivityV2::class.java)
            startActivity(intent)
        }

    }

    private fun saveData() {
        var languages=""
        var hobbies=""
        val sharedPreference =  getSharedPreferences("SKILLS_INFO", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        if (arCb.isChecked)
            languages+=getString(R.string.arabic)+" "
        if (frCb.isChecked)
            languages+=getString(R.string.french)+" "
        if (enCb.isChecked)
            languages+=getString(R.string.english)+" "
        if (musicCb.isChecked)
            hobbies+=getString(R.string.music)+" "
        if (sportCb.isChecked)
            hobbies+=getString(R.string.sport)+" "
        if (gamesCb.isChecked)
            hobbies+=getString(R.string.games)+" "
        editor.putString("languages", languages)
        editor.putString("hobbies", hobbies)
        editor.putString("android", androidSb.progress.toFloat().toString())
        editor.putString("ios", iosSb.progress.toFloat().toString())
        editor.putString("flutter", flutterSb.progress.toFloat().toString())
        editor.apply()
    }
}