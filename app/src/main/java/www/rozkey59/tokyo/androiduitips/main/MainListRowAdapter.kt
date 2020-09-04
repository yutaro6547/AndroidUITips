package www.rozkey59.tokyo.androiduitips.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.rozkey59.tokyo.androiduitips.databinding.ItemMainListRowBinding

class MainListRowAdapter(var dataList: List<MainListData>) : RecyclerView.Adapter<MainListRowAdapter.MainListRowViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListRowViewHolder {
        setOnItemClickListener(listener)
        return MainListRowViewHolder(
            ItemMainListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MainListRowViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.data = data
        holder.binding.titleContainer.setOnClickListener {
            listener.onClick(it, position, data)
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int, data: MainListData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    class MainListRowViewHolder(val binding: ItemMainListRowBinding) : RecyclerView.ViewHolder(binding.root)
}