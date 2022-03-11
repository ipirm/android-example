package az.azerconnect.bakcell.ui.main.stadiums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.azerconnect.bakcell.databinding.FragmentStadiumsBinding
import az.azerconnect.bakcell.ui.base.BaseFragment

class StadiumsFragment : BaseFragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentStadiumsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}