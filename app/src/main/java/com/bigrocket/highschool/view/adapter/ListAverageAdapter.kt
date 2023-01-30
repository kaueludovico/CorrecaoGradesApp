package com.bigrocket.highschool.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigrocket.highschool.R
import com.bigrocket.highschool.model.Student
import kotlinx.android.synthetic.main.res_list_average.view.*

class ListAverageAdapter(
    val list: MutableList<Student>,
    val average: Float,
    private val onClicked: (Student) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var student: Student

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListAverageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_list_average, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ListAverageViewHolder -> {
                holder.bind(list[position], onClicked)
                student = list[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun returnPosition(callback: (Student) -> Unit) {
        callback(student)
    }

    inner class ListAverageViewHolder constructor(
        itemView: View
    ) :  RecyclerView.ViewHolder(itemView) {
        private val studentName = itemView.name
        private var studentAverage = itemView.average

        fun bind(student: Student,
                 onClicked: (Student) -> Unit) {
            studentName.text = student.name
            studentAverage.text = "$average"

            itemView.setOnClickListener {
                onClicked(student)
            }
        }
    }
}