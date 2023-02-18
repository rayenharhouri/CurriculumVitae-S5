package tn.esprit.cv

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var picIv : ImageView
    private lateinit var nameEt : TextInputEditText; private lateinit var ageEt : TextInputEditText; private lateinit var emailEt : TextInputEditText
    private lateinit var nameLyt : TextInputLayout; private lateinit var ageLyt : TextInputLayout; private lateinit var emailLyt : TextInputLayout
    private lateinit var genderRg : RadioGroup; private lateinit var maleRb : RadioButton; private lateinit var femaleRb : RadioButton
    private lateinit var nextBtn : Button
    private var picSet=false;

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

        selectProfilePic()

        //ON CLICK NEXT
        nextBtn.setOnClickListener{
            if(!picSet){
                Toast.makeText(this, "Please select an image!", Toast.LENGTH_SHORT).show()
            }else if(validateInput()){
                saveData()
                var  intent= Intent(this, SkillsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun selectProfilePic() {
        picIv.setOnClickListener{
            pickFromGallery()
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startForResult.launch(intent)
    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                picIv.setImageURI(data?.data)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    saveSelectedImage29(data?.data)
                }else{
                    saveSelectedImage(data?.data)
                }
            }
        }

    private fun saveSelectedImage(uri: Uri?) {
        if (uri != null) {
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val file = File(getExternalFilesDir(null), "profile_pic.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(this, "Profile picture saved!", Toast.LENGTH_SHORT).show()
            picSet=true
            val sharedPreference =  getSharedPreferences("GENERAL_INFO", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            val uri = FileProvider.getUriForFile(this, "${packageName}.provider", file)
            editor.putString("uri", uri.toString())
            editor.apply()
        }else{
            Toast.makeText(this, "Failed to save profile picture!", Toast.LENGTH_SHORT).show()
        }
    }

    // API 29 and higher
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveSelectedImage29(uri: Uri?) {
        if (uri != null) {
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val fileName = "profile_pic.png"
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/$packageName")
            }
            val resolver = applicationContext.contentResolver
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
            val selectionArgs = arrayOf(fileName)
            resolver.delete(contentUri, selection, selectionArgs)
            val uri = resolver.insert(contentUri, contentValues)

            if (uri != null) {
                try {
                    val outputStream = resolver.openOutputStream(uri)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream?.flush()
                    outputStream?.close()
                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    resolver.update(uri, contentValues, null, null)
                    Toast.makeText(this, "Profile picture saved!", Toast.LENGTH_SHORT).show()
                    picSet=true
                    val sharedPreference =  getSharedPreferences("GENERAL_INFO", Context.MODE_PRIVATE)
                    var editor = sharedPreference.edit()
                    editor.putString("uri", uri.toString())
                    editor.apply()
                } catch (e: IOException) {
                    resolver.delete(uri, null, null)
                    Toast.makeText(this, "Failed to save profile picture!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Failed to save profile picture!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Failed to save profile picture!", Toast.LENGTH_SHORT).show()
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
        if(setError(nameEt,getString(R.string.must_not_be_empty)) || setError(emailEt,getString(R.string.must_not_be_empty)) || setError(ageEt,getString(R.string.must_not_be_empty))){
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