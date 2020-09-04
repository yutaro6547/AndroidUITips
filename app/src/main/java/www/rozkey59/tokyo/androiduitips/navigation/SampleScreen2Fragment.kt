package www.rozkey59.tokyo.androiduitips.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentSampleScreen2Binding

class SampleScreen2Fragment: Fragment() {

    private lateinit var binding: FragmentSampleScreen2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_screen2, container, false)
        return binding.root
    }
}