package com.ei.android.jokeapp.example.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.CommonUIModel
import com.ei.android.jokeapp.example.data.CommonDataModel

class CommonDataRecyclerAdapter():
    RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder>() {

    private val list = ArrayList<CommonUIModel>()
    fun show(data: List<CommonUIModel>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item,parent,false)
        return CommonDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class CommonDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
        fun bind(model: CommonUIModel) = model.show(textView)
    }
}
