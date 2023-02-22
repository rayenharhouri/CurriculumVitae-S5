package tn.esprit.cv.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.cv.R
import tn.esprit.cv.adapters.RecyclerViewAdapter
import tn.esprit.cv.data.Data

class ExperienceFragment : Fragment(R.layout.fragment_experience) {

    private lateinit var experienceRv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        experienceRv = view.findViewById(R.id.rv_experience)
        experienceRv.layoutManager = LinearLayoutManager(view.context)
        // ArrayList of class ItemsViewModel
        val data = ArrayList<Data>()
        data.add(Data(R.drawable.amazon,"AMAZON","UNITED STATES","01/09/2019","TODAY","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap."))
        data.add(Data(R.drawable.facebook,"FACEBOOK","FRANCE","01/09/2018","31/08/2019","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap."))
        data.add(Data(R.drawable.ms,"MICROSOFT","UNITED KINGDOM","01/09/2015","31/08/2016","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap."))
        data.add(Data(R.drawable.esprit,"ESPRIT","TUNISIA","01/09/2023","31/08/2024","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap."))
        val adapter= RecyclerViewAdapter(data,R.layout.experience_item)
        experienceRv.adapter=adapter
    }
}