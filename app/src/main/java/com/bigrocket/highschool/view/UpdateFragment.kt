package com.bigrocket.highschool.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bigrocket.highschool.R
import com.bigrocket.highschool.model.Student
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private val args: UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataField()
        buttonUpdate.setOnClickListener {
            setUpdate()
        }
    }

    private fun setDataField() {
        editTextNomeUpdate.setText(args.student.name)
        editTextMateriaUpdate.setText(args.student.matter)
        editTextNota1Update.setText(args.student.noteOne.toString())
        editTextNota2Update.setText(args.student.noteTwo.toString())
        editTextNota3Update.setText(args.student.noteThree.toString())
        editTextNota4Update.setText(args.student.noteFour.toString())
    }

    private fun setUpdate() {
        val student = Student(
            editTextNomeUpdate.text.toString(),
            editTextMateriaUpdate.text.toString(),
            editTextNota1Update.text.toString().toFloat(),
            editTextNota2Update.text.toString().toFloat(),
            editTextNota3Update.text.toString().toFloat(),
            editTextNota4Update.text.toString().toFloat())

        val action = UpdateFragmentDirections.actionUpdateFragmentToListAverageFragment(
            student
        )
        findNavController().navigate(action)
    }
}