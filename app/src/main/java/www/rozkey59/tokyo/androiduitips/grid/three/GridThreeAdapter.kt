package www.rozkey59.tokyo.androiduitips.grid.three

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import www.rozkey59.tokyo.androiduitips.databinding.ItemGridBinding
import www.rozkey59.tokyo.androiduitips.grid.GridListData
import androidx.databinding.BindingAdapter
import www.rozkey59.tokyo.androiduitips.grid.two.GridTwoAdapter


class GridThreeAdapter(val dataList: List<GridListData>): RecyclerView.Adapter<GridThreeAdapter.GridThreeViewHolder>() {

    lateinit var listener: OnItemClickListener
    lateinit var imageListener: OnSetImageIdListener

    override fun onBindViewHolder(holder: GridThreeViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.data = data
        holder.binding.image.setImageResource(imageListener.onSetImageId())
        holder.binding.container.setOnClickListener {
            listener.onClick(it, position, data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridThreeViewHolder {
        setOnItemClickListener(listener)
        setImageIdListener(imageListener)
        return GridThreeViewHolder(
            ItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int, data: GridListData)
    }

    interface OnSetImageIdListener {
        fun onSetImageId(): Int
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setImageIdListener(listener: OnSetImageIdListener) {
        this.imageListener = listener
    }

    class GridThreeViewHolder(val binding: ItemGridBinding): RecyclerView.ViewHolder(binding.root)
}