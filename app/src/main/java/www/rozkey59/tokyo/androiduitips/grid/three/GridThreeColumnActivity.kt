package www.rozkey59.tokyo.androiduitips.grid.three

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ActivityGridThreeColumnBinding
import www.rozkey59.tokyo.androiduitips.grid.GridListData
import www.rozkey59.tokyo.androiduitips.grid.two.GridTwoAdapter

class GridThreeColumnActivity: AppCompatActivity() {

    private lateinit var binding: ActivityGridThreeColumnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid_three_column)
        binding.apply {
            val adapter = GridThreeAdapter(createDataList())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(
                this@GridThreeColumnActivity, 3, GridLayoutManager.VERTICAL, false
            )
            recyclerView.addItemDecoration(
                GridThreeItemDecoration(resources.getDimension(R.dimen.carousel_margin).toInt())
            )
            adapter.setOnItemClickListener(object : GridThreeAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int, data: GridListData) {
                    Toast.makeText(this@GridThreeColumnActivity, data.label, Toast.LENGTH_SHORT).show()
                }
            })
            adapter.setImageIdListener(object : GridThreeAdapter.OnSetImageIdListener{
                override fun onSetImageId(): Int {
                    return getRandomImage()
                }
            })
        }
    }

    private fun createDataList() : List<GridListData> {
        val dataList = mutableListOf<GridListData>()
        for(i in 0 until 30) {
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