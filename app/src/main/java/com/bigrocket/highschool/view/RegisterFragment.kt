package com.bigrocket.highschool.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bigrocket.highschool.R
import com.bigrocket.highschool.model.Student
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createStudent()
    }

    private fun createStudent() {
        button.setOnClickListener {
            val student = Student(
                editTextNome.text.toString(),
                editTextMateria.text.toString(),
                editTextNota1.text.toString().toFloat(),
                editTextNota2.text.toString().toFloat(),
                editTextNota3.text.toString().toFloat(),
                editTextNota4.text.toString().toFloat()
            )
            val action = RegisterFragmentDirections.actionRegisterFragmentToListAverageFragment(
                student,
                null
            )
            findNavController().navigate(action)
        }
    }
}