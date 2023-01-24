package com.bigrocket.highschool.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigrocket.highschool.R
import com.bigrocket.highschool.model.Student
import com.bigrocket.highschool.view.adapter.ListAverageAdapter
import com.bigrocket.highschool.viewModel.ListAverageViewModel
import kotlinx.android.synthetic.main.fragment_list_average.*

class ListAverageFragment : Fragment(R.layout.fragment_list_average) {

    private lateinit var viewModel: ListAverageViewModel
    lateinit var averageAdapter: ListAverageAdapter
    private val args: ListAverageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setAdapter()
        viewModel.onSaveStudentData()
    }

    override fun onResume() {
        super.onResume()
        if (args.studentUpdated != null) {
            setUpdateData()
            setAdapter()
        }
    }

    private fun setUpdateData() {
        viewModel.updateStudent(viewModel.onRetrieveStudent(), args.studentUpdated!!)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            ListAverageViewModel.ListAverageViewModelProvider(args.student, this)
        )[ListAverageViewModel::class.java]
    }

    private fun setAdapter() {
        averageAdapter = ListAverageAdapter(
            viewModel.readStudent(),
            viewModel.averageCalculate(),
            { navigationToDetails(it) },
            { navigationToUpdate(it) },
            { viewModel.deleteStudent(it) }
        )

        recyclerView.apply {
            adapter = averageAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigationToDetails(student: Student) {
        var action = ListAverageFragmentDirections.actionListAverageFragmentToDetailsFragment(
            student,
            viewModel.averageCalculate()
        )
        findNavController().navigate(action)
    }

    private fun navigationToUpdate(student: Student) {
        var action = ListAverageFragmentDirections.actionListAverageFragmentToUpdateFragment(
            student
        )
        findNavController().navigate(action)
    }
}