package www.rozkey59.tokyo.androiduitips.main.ui.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import www.rozkey59.tokyo.androiduitips.databinding.FragmentBottomNavigationBinding

class BottomNavigationFragment: Fragment() {

    private lateinit var binding: FragmentBottomNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNavigationBinding.inflate(inflater)
        return binding.root
    }
}