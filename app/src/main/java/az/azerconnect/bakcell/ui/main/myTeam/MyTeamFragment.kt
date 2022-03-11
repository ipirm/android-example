package az.azerconnect.bakcell.ui.main.myTeam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.azerconnect.bakcell.databinding.FragmentMyTeamBinding
import az.azerconnect.bakcell.ui.base.BaseFragment

class MyTeamFragment : BaseFragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMyTeamBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}