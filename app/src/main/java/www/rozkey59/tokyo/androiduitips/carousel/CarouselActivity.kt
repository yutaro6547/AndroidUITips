package www.rozkey59.tokyo.androiduitips.carousel

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ActivityCarouselBinding
import www.rozkey59.tokyo.androiduitips.grid.two.GridTwoAdapter

class CarouselActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCarouselBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel)
        binding.apply {
            val adapter = CarouselAdapter(createDataList())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(
                this@CarouselActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerView.addItemDecoration(
                CarouselItemDecoration(resources.getDimension(R.dimen.carousel_margin).toInt())
            )
            adapter.setOnItemClickListener(object : CarouselAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int, data: CarouselListData) {
                    Toast.makeText(this@CarouselActivity, data.title, Toast.LENGTH_SHORT).show()
                }
            })
            val adapter2 = CarouselAndSeeMoreAdapter(createImageDataList())
            recyclerView2.adapter = adapter2
            recyclerView2.layoutManager = LinearLayoutManager(
                this@CarouselActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerView2.addItemDecoration(
                CarouselItemDecoration(resources.getDimension(R.dimen.carousel_margin).toInt())
            )
//            val helper = LinearSnapHelper()
//            helper.attachToRecyclerView(recyclerView2)
            GravitySnapHelper(Gravity.START).attachToRecyclerView(recyclerView2)
            adapter2.setOnItemClickListener(object : CarouselAndSeeMoreAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int, data: CarouselListData) {
                    Toast.makeText(this@CarouselActivity, data.title, Toast.LENGTH_SHORT).show()
                }

                override fun onClickSeeMore(view: View, position: Int) {
                    Toast.makeText(this@CarouselActivity, "See More", Toast.LENGTH_SHORT).show()
                }
            })
            adapter2.setImageIdListener(object : CarouselAndSeeMoreAdapter.OnSetImageIdListener{
                override fun onSetImageId(): Int {
                    return getRandomImage()
                }
            })
        }
    }

    private fun createDataList() : List<CarouselListData> {
        val dataList = mutableListOf<CarouselListData>()
        for(i in 0 until 20) {
            dataList.add(
                CarouselListData(
                    image = resources.getDrawable(R.drawable.ic_android, null),
                    title = "Carousel Item $i",
                    color = getRandomColor()
                )
            )
        }
        return dataList
    }

    private fun createImageDataList() : List<CarouselListData> {
        val dataList = mutableListOf<CarouselListData>()
        for(i in 0 until 20) {
            dataList.add(
                CarouselListData(
                    image = resources.getDrawable(R.drawable.ic_android, null),
                    imageResId = getRandomImage(),
                    title = "Carousel Item $i",
                    color = getRandomColor()
                )
            )
        }
        return dataList
    }

    private fun getRandomColor(): Int {
        val colorList = resources.getIntArray(R.array.color_list)
        return colorList[(0 until colorList.size).random()]
    }

    private fun getRandomImage(): Int {
        val imageListArray = resources.obtainTypedArray(R.array.image_list)
        val drawableIds = mutableListOf<Int>()
        for (i in 0 until imageListArray.length()) {
            drawableIds.add(imageListArray.getResourceId(i, 0))
        }
        imageListArray.recycle()
        drawableIds.shuffle()
        return drawableIds[0]
    }
}