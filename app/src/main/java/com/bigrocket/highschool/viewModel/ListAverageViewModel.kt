package com.bigrocket.highschool.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bigrocket.highschool.model.Student
import com.bigrocket.highschool.view.ListAverageFragment
import com.google.gson.Gson

class ListAverageViewModel(private val student: Student,
                           private val fragment: ListAverageFragment) : ViewModel() {

    private var list = mutableListOf<Student>()

    fun readStudent() : List<Student> {
        list.add(student)
        return list
    }

    fun updateStudent(student: Student, studentUpdated: Student) {
        list.remove(student)
        list.add(studentUpdated)
    }

    fun deleteStudent(student: Student) {
        list.remove(student)
        fragment.averageAdapter.notifyItemRemoved(list.size)
    }

    fun averageCalculate() : Float {
        return ((student.noteOne + student.noteTwo) + (student.noteThree + student.noteFour)) / 4
    }

    private fun myPrefs(): SharedPreferences {
        return fragment.requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    }

    fun onSaveStudentData() {
        val json = Gson().toJson(student)

        myPrefs().edit {
            putString("initialStudent", json)
            commit()
        }
    }

    fun onRetrieveStudent() : Student {
        val gson = Gson()
        val json = myPrefs().getString("initialStudent", "")
        return gson.fromJson(json!!, Student::class.java)
    }

    class ListAverageViewModelProvider(
        private val student: Student,
        private val fragment: ListAverageFragment,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListAverageViewModel(student, fragment) as T
        }
    }
}