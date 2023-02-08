package tn.esprit.cv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var picIv : ImageView
    private lateinit var nameEt : TextInputEditText; private lateinit var ageEt : TextInputEditText; private lateinit var emailEt : TextInputEditText
    private lateinit var nameLyt : TextInputLayout; private lateinit var ageLyt : TextInputLayout; private lateinit var emailLyt : TextInputLayout
    private lateinit var genderRg : RadioGroup; private lateinit var maleRb : RadioButton; private lateinit var femaleRb : RadioButton
    private lateinit var nextBtn : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.create_resume_title)

        //INIT UI ELEMENTS
        picIv=findViewById(R.id.iv_pic)
        nameEt=findViewById(R.id.et_name); ageEt=findViewById(R.id.et_age); emailEt=findViewById(R.id.et_email)
        nameLyt=findViewById(R.id.lyt_name); ageLyt=findViewById(R.id.lyt_age); emailLyt=findViewById(R.id.lyt_email)
        genderRg=findViewById(R.id.rg_gender); maleRb=findViewById(R.id.rb_male); femaleRb=findViewById(R.id.rb_female)
        nextBtn=findViewById(R.id.btn_next)

        //ON CLICK NEXT
        nextBtn.setOnClickListener{
            if(validateInput()){
                saveData()
                var  intent= Intent(this, SkillsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveData() {
        val sharedPreference =  getSharedPreferences("GENERAL_INFO", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("fullname", nameEt.text.toString())
        editor.putString("email", emailEt.text.toString())
        editor.putString("age", ageEt.text.toString())
        var gender="Male"
        if(femaleRb.isChecked)
            gender="Female"
        editor.putString("gender", gender)
        editor.apply()
    }

    private fun validateInput(): Boolean {
        //Vérifier si les champs des 3 EditText ne sont pas vides
        if(setError(nameEt,getString(R.string.must_not_be_empty)) || setError(ageEt,getString(R.string.must_not_be_empty)) || setError(emailEt,getString(R.string.must_not_be_empty))){
            return false
        }else{
            //vérifier si l'adresse email est valide
             return emailVerified()
        }
    }

    private fun emailVerified():Boolean {
        if(!EMAIL_ADDRESS.matcher(emailEt.text).matches()){
            (emailEt.parent.parent as TextInputLayout).isErrorEnabled = true
            (emailEt.parent.parent as TextInputLayout).error = getString(R.string.check_email)
            return false
        }
        return true
    }

    private fun setError(et: TextInputEditText, errorMsg: String): Boolean {
        if(et.text?.isEmpty() == true){
            (et.parent.parent as TextInputLayout).isErrorEnabled = true
            (et.parent.parent as TextInputLayout).error = errorMsg
            return true
        }else{
            (et.parent.parent as TextInputLayout).isErrorEnabled = false
            return false
        }
    }
}