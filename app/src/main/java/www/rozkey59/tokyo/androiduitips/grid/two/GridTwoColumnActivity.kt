package www.rozkey59.tokyo.androiduitips.grid.two

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_grid_two_column.*
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ActivityGridTwoColumnBinding
import www.rozkey59.tokyo.androiduitips.grid.GridListData

class GridTwoColumnActivity: AppCompatActivity() {

    private lateinit var binding: ActivityGridTwoColumnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid_two_column)
        binding.apply {
            val adapter = GridTwoAdapter(createDataList())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(
                this@GridTwoColumnActivity, 2, GridLayoutManager.VERTICAL, false
            )
            recyclerView.addItemDecoration(
                GridTwoItemDecoration(resources.getDimension(R.dimen.carousel_margin).toInt())
            )
            adapter.setOnItemClickListener(object : GridTwoAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int, data: GridListData) {
                    Toast.makeText(this@GridTwoColumnActivity, data.label, Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setImageIdListener(object : GridTwoAdapter.OnSetImageIdListener{
                override fun onSetImageId(): Int {
                    return getRandomImage()
                }
            })
        }
    }

    private fun createDataList() : List<GridListData> {
        val dataList = mutableListOf<GridListData>()
        for(i in 0 until 20) {
            dataList.add(
                GridListData(
                    image = getRandomImage(),
                    label = "￥12$i"
                )
            )
        }
        return dataList
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