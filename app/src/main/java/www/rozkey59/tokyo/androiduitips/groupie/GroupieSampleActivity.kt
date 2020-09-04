package www.rozkey59.tokyo.androiduitips.groupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ActivityGroupieSampleBinding

class GroupieSampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupieSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_groupie_sample)
        val groupAdapter = GroupAdapter<ViewHolder>()
        binding.recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
        }
        groupAdapter.add(HeaderItem("Groupie Sample"))
        groupAdapter.add(GridItem(createCardList()))
        groupAdapter.add(CarouselItem(createCardList()))
        groupAdapter.add(GridItem(createCardList()))
    }

    private fun createCardList(): MutableList<CardItem> {
        val cards = mutableListOf<CardItem>()
        for (i in 0 until 21) {
            cards.add(CardItem())
        }
        return cards
    }

}