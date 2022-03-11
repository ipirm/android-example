package az.azerconnect.bakcell.ui.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.FloatingWindow
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.databinding.ActivityMainBinding
import az.azerconnect.bakcell.ui.EventBus
import az.azerconnect.bakcell.ui.base.BaseActivity
import az.azerconnect.bakcell.utils.KeyboardEventListener
import az.azerconnect.bakcell.utils.StatusBarUtil
import az.azerconnect.bakcell.utils.extensions.findNavHostNavController

class MainActivity : BaseActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavHostNavController(R.id.nav_host_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucent(this)
        setContentView(binding.root)

        initEventBus()
        initNavController()

        KeyboardEventListener(binding.navHostMain, this)
    }

    private fun initEventBus() {
        EventBus.navigateToSplash.observe(this) {
            findNavController(binding.root.id)
                .navigate(R.id.action_global_splash)
        }
    }

    private fun initNavController() {
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(
                setOf(
                    R.id.gamesFragment,
                    R.id.myTeamFragment,
                    R.id.notificationsFragment,
                    R.id.stadiumsFragment
                )
            )
        )

        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(addOnDestinationChangedListener)
    }

    private val addOnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination !is FloatingWindow) {
                binding.bottomNav.isVisible = when (destination.id) {
                    R.id.gamesFragment,
                    R.id.myTeamFragment,
                    R.id.notificationsFragment,
                    R.id.stadiumsFragment -> true
                    else -> false
                }
            }
        }

    override fun getContentView() = binding.root
    override fun getToolbar() = binding.toolbar
}