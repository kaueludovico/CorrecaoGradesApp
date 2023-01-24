package com.bigrocket.highschool.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigrocket.highschool.R
import com.bigrocket.highschool.model.Student
import kotlinx.android.synthetic.main.res_list_average.view.*

class ListAverageAdapter(
    private val list: List<Student>,
    val average: Float,
    private val onClicked: (Student) -> Unit,
    private val onEditClicked: (Student) -> Unit,
    private val onDeleteClicked: (Student) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListAverageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_list_average, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ListAverageViewHolder -> {
                holder.bind(list[position], onClicked, onEditClicked, onDeleteClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ListAverageViewHolder constructor(
        itemView: View
    ) :  RecyclerView.ViewHolder(itemView) {
        val studentName = itemView.name
        var studentAverage = itemView.average

        fun bind(student: Student,
                 onClicked: (Student) -> Unit,
                 onEditClicked: (Student) -> Unit,
                 onDeleteClicked: (Student) -> Unit) {
            studentName.text = student.name
            studentAverage.text = "$average"

            itemView.setOnClickListener {
                onClicked(student)
            }
            itemView.buttonDelete.setOnClickListener {
                onDeleteClicked(student)
            }
            itemView.buttonEdit.setOnClickListener {
                onEditClicked(student)
            }
        }
    }
}