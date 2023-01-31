package com.bigrocket.highschool.view

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
import kotlinx.android.synthetic.main.res_list_average.*

class ListAverageFragment : Fragment(R.layout.fragment_list_average) {

    private lateinit var viewModel: ListAverageViewModel
    lateinit var averageAdapter: ListAverageAdapter
    private val args: ListAverageFragmentArgs by navArgs()
    private var floatActionButtonVisible = false
    lateinit var studentItem: Student

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkReceiptNewData()
        checkInitialized()
        viewModel.onSaveStudentData()

        floatActionButton()
    }


    private fun floatActionButton() {

        floatActionButtonVisible = false

        openFloatButton.setOnClickListener {
            if (!floatActionButtonVisible) {
                addFloatButton.show()
                editFloatButton.show()
                deleteFloatButton.show()

                addFloatButton.visibility = View.VISIBLE
                editFloatButton.visibility = View.VISIBLE
                deleteFloatButton.visibility = View.VISIBLE

                openFloatButton.setImageResource(R.drawable.ic_close)

                floatActionButtonVisible = true
            } else {
                addFloatButton.hide()
                editFloatButton.hide()
                deleteFloatButton.hide()

                addFloatButton.visibility = View.GONE
                editFloatButton.visibility = View.GONE
                deleteFloatButton.visibility = View.GONE

                openFloatButton.setImageResource(R.drawable.ic_add)

                floatActionButtonVisible = false
            }
        }

        addFloatButton.setOnClickListener {
            println(">>>>>>>>>>>>>>>>>>>")
            println("ADICIONANDO ->")
            println(">>>>>>>>>>>>>>>>>>>")
        }

        editFloatButton.setOnClickListener {
            navigationToUpdate()
        }

        deleteFloatButton.setOnClickListener {
            deleteItem()
        }
    }

    private fun checkReceiptNewData() {
        if (args.studentUpdated != null) {
            setUpdateData()
            checkInitialized()
        }
    }

    private fun checkInitialized() {
        if (!this::averageAdapter.isInitialized &&
                !this::viewModel.isInitialized) {
            setViewModel()
            setAdapter()
        } else {
            for (i in averageAdapter.list.indices) {
                if (averageAdapter.list[i] === averageAdapter.list[i]) {
                    averageAdapter.list.removeAt(i)
                    setAdapter()
                }
            }
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
         { itemToUpdate(it) } )

        recyclerView.apply {
            adapter = averageAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigationToDetails(student: Student) {
        val action = ListAverageFragmentDirections.actionListAverageFragmentToDetailsFragment(
            student,
            viewModel.averageCalculate()
        )
        findNavController().navigate(action)
    }

    private fun itemToUpdate(student: Student) {
        this.studentItem = student
    }

    private fun navigationToUpdate() {
        val action = ListAverageFragmentDirections.actionListAverageFragmentToUpdateFragment(
            this.studentItem
        )
        findNavController().navigate(action)
    }

    private fun deleteItem() {
        viewModel.deleteStudent(studentItem)
    }
}