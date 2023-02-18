package tn.esprit.cv

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment

class HobbiesFragment : Fragment(R.layout.fragment_hobbies) {
    private lateinit var musicCb : CheckBox; private lateinit var sportCb : CheckBox; private lateinit var gamesCb : CheckBox

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicCb=view.findViewById(R.id.cb_music); sportCb=view.findViewById(R.id.cb_sport); gamesCb=view.findViewById(R.id.cb_games)
        setHobbies()
    }

    private fun setHobbies() {
        checkCB("music",musicCb)
        checkCB("sport",sportCb)
        checkCB("games",gamesCb)
    }

    private fun checkCB(hb: String, cb: CheckBox) {
        val sharedPreference =  requireContext().getSharedPreferences("SKILLS_INFO", Context.MODE_PRIVATE)
        val hobbies=sharedPreference.getString("hobbies","")
        if(hobbies?.contains(hb, ignoreCase = true) == true){
            cb.isChecked=true
        }
    }

}