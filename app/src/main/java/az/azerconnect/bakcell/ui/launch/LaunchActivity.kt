package az.azerconnect.bakcell.ui.launch

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.databinding.ActivityLaunchBinding
import az.azerconnect.bakcell.ui.base.BaseActivity
import az.azerconnect.bakcell.utils.KeyboardEventListener
import az.azerconnect.bakcell.utils.StatusBarUtil
import az.azerconnect.bakcell.utils.extensions.findNavHostNavController

class LaunchActivity : BaseActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) { ActivityLaunchBinding.inflate(layoutInflater) }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavHostNavController(R.id.nav_host_launch) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucent(this)
        setContentView(binding.root)

        initNavController()
        KeyboardEventListener(binding.root, this)
    }

    private fun initNavController() {
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.splashFragment))
        )
    }

    override fun getContentView() = binding.root
    override fun getToolbar() = binding.toolbar
}