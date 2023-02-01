package tn.esprit.cv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.util.Patterns.EMAIL_ADDRESS

class MainActivity : AppCompatActivity() {
    private lateinit var picIv : ImageView
    private lateinit var nameEt : EditText; private lateinit var ageEt : EditText; private lateinit var emailEt : EditText
    private lateinit var genderRg : RadioGroup; private lateinit var maleRb : RadioButton; private lateinit var femaleRb : RadioButton
    private lateinit var androidSb : SeekBar; private lateinit var iosSb : SeekBar; private lateinit var flutterSb : SeekBar
    private lateinit var nextBtn : Button; private lateinit var resetBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //INIT UI ELEMENTS
        picIv=findViewById(R.id.iv_pic)
        nameEt=findViewById(R.id.et_name); ageEt=findViewById(R.id.et_age); emailEt=findViewById(R.id.et_email)
        genderRg=findViewById(R.id.rg_gender); maleRb=findViewById(R.id.rb_male); femaleRb=findViewById(R.id.rb_female)
        androidSb=findViewById(R.id.sb_android); iosSb=findViewById(R.id.sb_ios); flutterSb=findViewById(R.id.sb_flutter)
        nextBtn=findViewById(R.id.btn_next); resetBtn=findViewById(R.id.btn_reset)

        //ON CLICK NEXT
        nextBtn.setOnClickListener{
            if(validateInput()){
                evaluateSkills()
            }
        }

        //ON CLICK RESET
        resetBtn.setOnClickListener{
            resetAll()
        }
    }

    //remettre à zéro tous les composants
    private fun resetAll() {
        nameEt.setText("")
        ageEt.setText("")
        emailEt.setText("")
        genderRg.check(R.id.rb_male)
        androidSb.progress = 0
        iosSb.progress = 0
        flutterSb.progress = 0
    }

    private fun evaluateSkills() {
        //Si tous les skills sont supérieurs à 80, on affiche « Vous êtes d'excellents skills!»
        if(androidSb.progress>80 && iosSb.progress>80 && flutterSb.progress>80){
            Toast. makeText(this,getString(R.string.excellent_skills),Toast. LENGTH_SHORT).show()
        }
        //S’il a un skill qui est qui est supérieur à 80 (=la plus haute compétence), on affiche « Vous êtes excellent en NOM_SKILL! »
        else if(androidSb.progress>80 || iosSb.progress>80 || flutterSb.progress>80){
            val map = mapOf("Android" to androidSb.progress,"iOS" to iosSb.progress,"Flutter" to flutterSb.progress)
            Toast. makeText(this,getString(R.string.excellent_skill, bestSkill(map)),Toast. LENGTH_SHORT).show()
        }
        //Si tous les Skills sont <= 30, on affiche « Vous devez travailler vos skills! »
        else if(androidSb.progress<=30 && iosSb.progress<=30 && flutterSb.progress<=30){
            Toast. makeText(this,getString(R.string.below_average_skills),Toast. LENGTH_SHORT).show()
        }
        //Sinon on affiche un message « Vous avez de bons skills!»
        else{
            Toast. makeText(this,getString(R.string.good_skills),Toast. LENGTH_SHORT).show()
        }
    }

    private fun validateInput(): Boolean {
        //Vérifier si les champs des 3 EditText ne sont pas vide
        if(nameEt.text.isEmpty() || ageEt.text.isEmpty() || emailEt.text.isEmpty()){
            Toast. makeText(this,getString(R.string.empty_fields),Toast. LENGTH_SHORT).show()
            return false;
        }
        //Vérifier si L’email est écrit correctement
        else if(!EMAIL_ADDRESS.matcher(emailEt.text).matches()){
            Toast. makeText(this,getString(R.string.check_email),Toast. LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun bestSkill(map:Map<String,Int>):String{
        var bestSkillKey=""
        var bestSkillVal=0
        for ((key,value) in map){
            if(value>bestSkillVal){
                bestSkillVal=value
                bestSkillKey=key
            }
        }
        return bestSkillKey
    }
}