package tn.esprit.cv

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private lateinit var nameTv : TextView; private lateinit var emailTv : TextView; private lateinit var ageTv : TextView; private lateinit var genderTv : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nameTv=view.findViewById(R.id.tv_name); ageTv=view.findViewById(R.id.tv_age); emailTv=view.findViewById(R.id.tv_email); genderTv=view.findViewById(R.id.tv_gender)
        setGeneralInfo()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setGeneralInfo() {
        val sp="GENERAL_INFO"
        setTextViewText(sp,"fullname",nameTv,R.string.my_name)
        setTextViewText(sp,"email",emailTv,null)
        setTextViewText(sp,"age",ageTv,R.string.my_age)
        setTextViewText(sp,"gender",genderTv,R.string.my_gender)
    }
    private fun setTextViewText(sp:String,key:String,tv:TextView,resId:Int?){
        val sharedPreference =  requireContext().getSharedPreferences(sp, Context.MODE_PRIVATE)
        var value=sharedPreference.getString(key,"")
        if(resId!=null){
            tv.text=getString(resId,value)
        }else{
            tv.text=value
        }
    }
}
