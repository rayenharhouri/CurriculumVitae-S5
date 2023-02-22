package tn.esprit.cv.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.cv.R
import tn.esprit.cv.adapters.RecyclerViewAdapter
import tn.esprit.cv.data.Data

class EducationFragment : Fragment(R.layout.fragment_education) {
    private lateinit var educationRv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        educationRv = view.findViewById(R.id.rv_education)
        educationRv.layoutManager = LinearLayoutManager(view.context)
        // ArrayList of class ItemsViewModel
        val data = ArrayList<Data>()
        data.add(Data(R.drawable.mit,"MASSACHUSETTS","UNITED STATES","01/09/2020","TODAY",""))
        data.add(Data(R.drawable.oxford,"OXFORD","UNITED KINGDOM","01/09/2018","31/08/2020",""))
        data.add(Data(R.drawable.stanford,"STANFORD","UNITED STATES","01/09/2016","31/08/2018",""))
        data.add(Data(R.drawable.cambridge,"CAMBRIDGE","UNITED KINGDOM","01/09/2014","31/08/2016",""))
        data.add(Data(R.drawable.harvard,"HARVARD","UNITED STATES","01/09/2012","31/08/2014",""))
        data.add(Data(R.drawable.esprit,"ESPRIT","TUNISIA","01/09/2009","31/08/2012",""))
        val adapter= RecyclerViewAdapter(data,R.layout.education_item)
        educationRv.adapter=adapter
    }
}