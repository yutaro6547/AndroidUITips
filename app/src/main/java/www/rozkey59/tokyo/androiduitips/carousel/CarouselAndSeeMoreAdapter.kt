package www.rozkey59.tokyo.androiduitips.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.rozkey59.tokyo.androiduitips.databinding.ItemCarouselImageCardBinding
import www.rozkey59.tokyo.androiduitips.databinding.ItemCarouselSeeMoreBinding

class CarouselAndSeeMoreAdapter(val dataList: List<CarouselListData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var listener: OnItemClickListener
    lateinit var imageListener: OnSetImageIdListener

    enum class ViewType(val id: Int) {
        Image(0) {
            override fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return CarouselImageViewHolder(
                    ItemCarouselImageCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int): RecyclerView.ViewHolder {
                return holder
            }

        },
        SeeMore(1) {
            override fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return CarouselSeeMoreViewHolder(
                    ItemCarouselSeeMoreBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int): RecyclerView.ViewHolder {
                return holder
            }

        };
        companion object {
            fun forId(id: Int): ViewType {
                for (viewType: ViewType in values()) {
                    if (viewType.id == id) {
                        return viewType
                    }
                }
                throw AssertionError("no enum found for the id. you forgot to implement?")
            }
        }

        abstract fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        abstract fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int): RecyclerView.ViewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        setOnItemClickListener(listener)
        setImageIdListener(imageListener)
        return ViewType.forId(viewType).createViewHolder(parent = parent, viewType = viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ViewType.forId(holder.itemViewType)) {
            ViewType.Image -> {
                // TODO: キャストやめたい
                val data = dataList[position]
                val viewHolder = holder as CarouselImageViewHolder
                viewHolder.binding.data = data
                holder.binding.image.setImageResource(imageListener.onSetImageId())
                viewHolder.binding.container.setOnClickListener {
                    listener.onClick(it, position, data)
                }
            }
            ViewType.SeeMore -> {
                val viewHolder = holder as CarouselSeeMoreViewHolder
                viewHolder.binding.container.setOnClickListener {
                    listener.onClickSeeMore(it, position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == dataList.lastIndex + 1) ViewType.SeeMore.id else ViewType.Image.id
    }


    override fun getItemCount(): Int {
        return dataList.size + 1
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int, data: CarouselListData)
        fun onClickSeeMore(view: View, position: Int)
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

    class CarouselImageViewHolder(val binding: ItemCarouselImageCardBinding): RecyclerView.ViewHolder(binding.root)
    class CarouselSeeMoreViewHolder(val binding: ItemCarouselSeeMoreBinding): RecyclerView.ViewHolder(binding.root)
}