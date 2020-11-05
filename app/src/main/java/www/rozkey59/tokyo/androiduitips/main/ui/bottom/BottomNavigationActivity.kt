package www.rozkey59.tokyo.androiduitips.main.ui.bottom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.ActivityBottomNavigationBinding
import www.rozkey59.tokyo.androiduitips.main.ui.other.setupWithNavController

class BottomNavigationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
        val controller = binding.bottomNavigation.setupWithNavController(
            navGraphIds = listOf(R.navigation.navigation_home, R.navigation.navigation_mail, R.navigation.navigation_setting),
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host,
            intent = intent
        )
        controller.observe(this, Observer {
            setupActionBarWithNavController(it)
        })
        currentNavController = controller
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, BottomNavigationActivity::class.java)
    }
}