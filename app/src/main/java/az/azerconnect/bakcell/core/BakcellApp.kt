package az.azerconnect.bakcell.core

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import az.azerconnect.bakcell.BuildConfig
import az.azerconnect.bakcell.di.appModule
import az.azerconnect.data.di.appModuleApis
import az.azerconnect.data.di.appModuleRepos
import az.azerconnect.data.persistence.SessionManager
import com.samid.filecompress.FileCompress
import com.semid.filechooser.FileChooserFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BakcellApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        init()
        initFiles()
        initKoin()
    }

    private fun init() {
        SessionManager.init(applicationContext)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun initFiles() {
        FileCompress.init(this)
        FileCompress.instance.deleteCompressedFiles()
        FileChooserFragment.deleteTakeFiles(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BakcellApp)
            androidFileProperties()
            modules(listOf(appModule, appModuleApis, appModuleRepos))
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}