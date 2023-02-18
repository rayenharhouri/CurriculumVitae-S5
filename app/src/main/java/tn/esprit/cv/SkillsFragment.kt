package tn.esprit.cv

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.widget.SeekBar
import androidx.fragment.app.Fragment


class SkillsFragment : Fragment(R.layout.fragment_skills) {

    lateinit var androidSb:SeekBar; lateinit var iosSb:SeekBar; lateinit var flutterSb:SeekBar;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        androidSb=view.findViewById(R.id.sb_android); iosSb=view.findViewById(R.id.sb_ios); flutterSb=view.findViewById(R.id.sb_flutter)
        disableSeekbars()
        setProgress()
    }

    private fun disableSeekbars() {
        androidSb.isEnabled=false
        androidSb.progressDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
        iosSb.isEnabled=false
        iosSb.progressDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
        flutterSb.isEnabled=false
        androidSb.progressDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
    }

    private fun setProgress() {
        val sharedPreference =  requireContext().getSharedPreferences("SKILLS_INFO", Context.MODE_PRIVATE)
        val androidProgress=sharedPreference.getString("android","")
        val iosProgress=sharedPreference.getString("ios","")
        val flutterProgress=sharedPreference.getString("flutter","")
        androidSb.progress= androidProgress!!.toFloat().toInt()
        iosSb.progress=iosProgress!!.toFloat().toInt()
        flutterSb.progress=flutterProgress!!.toFloat().toInt()
    }
}