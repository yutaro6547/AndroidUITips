package www.rozkey59.tokyo.androiduitips.password

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import www.rozkey59.tokyo.androiduitips.R
import www.rozkey59.tokyo.androiduitips.databinding.FragmentPasswordBinding

class PasswordFragment: Fragment() {

    private lateinit var binding: FragmentPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        with(binding) {
            button.setOnClickListener {
                if (passwordForm.transformationMethod is PasswordTransformationMethod) {
                    passwordForm.transformationMethod = HideReturnsTransformationMethod()
                    button.text = "Hide"
                } else {
                    passwordForm.transformationMethod = PasswordTransformationMethod()
                    button.text = "Show"
                }
                passwordForm.setSelection(passwordForm.text.lastIndex + 1)
            }
        }
    }
}