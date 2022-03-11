package az.azerconnect.bakcell.ui.launch.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.azerconnect.bakcell.databinding.FragmentGamesBinding
import az.azerconnect.bakcell.ui.base.BaseFragment

class IntroFragment : BaseFragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentGamesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}