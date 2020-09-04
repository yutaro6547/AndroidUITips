package www.rozkey59.tokyo.androiduitips.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentSampleScreen1Binding

class SampleScreen1Fragment: Fragment() {

    private lateinit var binding: FragmentSampleScreen1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample_screen1, container, false)
        return binding.root
    }
}