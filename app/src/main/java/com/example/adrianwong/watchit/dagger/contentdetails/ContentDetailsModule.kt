package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.watchit.contentdetails.ContentDetailsLogic
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import dagger.Module
import dagger.Provides

@Module
class ContentDetailsModule(private val view: IContentDetailsContract.View,
                           private val viewModel: IContentDetailsContract.ViewModel) {

    @Provides
    @ContentDetailsScope
    fun providesContentDetailsLogic(): IContentDetailsContract.Logic {
        return ContentDetailsLogic(DispatcherProvider, view, viewModel)
    }

}