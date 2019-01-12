package com.example.adrianwong.watchit.dagger.modules

import android.content.Context
import com.example.adrianwong.watchit.dagger.MovieApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(context: Context) {

    private val appContext = context.applicationContext

    @Provides
    @MovieApplicationScope
    fun providesAppContext(): Context = appContext

}