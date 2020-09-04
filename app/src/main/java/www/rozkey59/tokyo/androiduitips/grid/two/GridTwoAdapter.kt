package www.rozkey59.tokyo.androiduitips.grid.two

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_grid.view.*
import www.rozkey59.tokyo.androiduitips.databinding.ItemGridBinding
import www.rozkey59.tokyo.androiduitips.grid.GridListData

class GridTwoAdapter(val dataList: List<GridListData>): RecyclerView.Adapter<GridTwoAdapter.GridTwoViewHolder>() {

    lateinit var listener: OnItemClickListener
    lateinit var imageListener: OnSetImageIdListener

    override fun onBindViewHolder(holder: GridTwoViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.data = data
        holder.binding.image.setImageResource(imageListener.onSetImageId())
        holder.binding.container.setOnClickListener {
            listener.onClick(it, position, data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridTwoViewHolder {
        setOnItemClickListener(listener)
        setImageIdListener(imageListener)
        return GridTwoViewHolder(
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

    class GridTwoViewHolder(val binding: ItemGridBinding): RecyclerView.ViewHolder(binding.root)
}