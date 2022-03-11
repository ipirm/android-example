package az.azerconnect.bakcell.ui.launch.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import az.azerconnect.bakcell.databinding.FragmentSplashBinding
import az.azerconnect.bakcell.ui.base.BaseFragment
import az.azerconnect.bakcell.utils.getNavigationBarHeight
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment() {
    override val viewModel: SplashVM by viewModel()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSplashBinding.inflate(layoutInflater).apply {
            bgView.translationY = context.getNavigationBarHeight().toFloat()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.isUserLoggedIn
            .observe(viewLifecycleOwner) { loggedIn ->
                if (loggedIn)
                    navigateToMain()
                else
                    navigateToAuth()
            }

        return binding.root
    }

    private fun navigateToMain() {
        findNavController().navigate(SplashFragmentDirections.actionGlobalMainActivity())
        activity?.finish()
    }

    private fun navigateToAuth() {
        findNavController().navigate(SplashFragmentDirections.actionGlobalAuth())
    }

    override fun isLightStatusBarText() = false
    override fun hideActionBar() = true
}