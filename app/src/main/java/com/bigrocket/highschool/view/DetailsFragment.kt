package com.bigrocket.highschool.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bigrocket.highschool.view.DetailsFragmentArgs
import com.bigrocket.highschool.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDataStudent()
    }

    fun showDataStudent() {
        textViewName.text = args.student.name
        textViewMatter.text = args.student.matter
        textViewNote1.text = args.student.noteOne.toString()
        textViewNota2.text = args.student.noteTwo.toString()
        textViewNota3.text = args.student.noteThree.toString()
        textViewNota4.text = args.student.noteFour.toString()
        textViewAverage.text = args.average.toString()
    }
}