package az.azerconnect.bakcell.ui.launch.auth.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import az.azerconnect.bakcell.databinding.FragmentLoginBinding
import az.azerconnect.bakcell.ui.base.BaseFragment
import az.azerconnect.bakcell.utils.extensions.handleProgress
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {
    override val viewModel by viewModel<LoginVM>()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClicks()
        subscribeToObservables()
    }

    private fun setClicks() {
        binding.registerBtn.setOnClickListener {

        }
    }

    private fun subscribeToObservables() {
        viewModel.uiState
            .asLiveData()
            .observe(viewLifecycleOwner) {
                binding.loginBtn.handleProgress(it, progressColor = Color.WHITE)
            }
    }

    override fun hideActionBar() = true
    override fun isLightStatusBarText() = false
}