package tn.esprit.cv

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment

class LanguagesFragment : Fragment(R.layout.fragment_languages) {
    private lateinit var arCb : CheckBox; private lateinit var frCb : CheckBox; private lateinit var enCb : CheckBox

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arCb=view.findViewById(R.id.cb_ar); frCb=view.findViewById(R.id.cb_fr); enCb=view.findViewById(R.id.cb_en)
        setLanguages()
    }

    private fun setLanguages() {
        checkCB("english",enCb)
        checkCB("french",frCb)
        checkCB("arabic",arCb)
    }

    private fun checkCB(hb: String, cb: CheckBox) {
        val sharedPreference =  requireContext().getSharedPreferences("SKILLS_INFO", Context.MODE_PRIVATE)
        val languages=sharedPreference.getString("languages","")
        if(languages?.contains(hb, ignoreCase = true) == true){
            cb.isChecked=true
        }
    }
}