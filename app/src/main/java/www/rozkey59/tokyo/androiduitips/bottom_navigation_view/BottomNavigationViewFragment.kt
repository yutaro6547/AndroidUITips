package www.rozkey59.tokyo.androiduitips.bottom_navigation_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentBottomNavigationViewBinding

class BottomNavigationViewFragment : Fragment() {

    private lateinit var binding: FragmentBottomNavigationViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_navigation_view, container, false)
        return binding.root
    }
}