package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.watchit.contentdetails.ContentDetailsActivity
import com.example.adrianwong.watchit.contentdetails.IContentDetailsContract
import dagger.BindsInstance
import dagger.Subcomponent

@ContentDetailsScope
@Subcomponent(modules = [ContentDetailsModule::class])
interface ContentDetailsSubComponent {
    fun inject(contentDetailsActivity: ContentDetailsActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ContentDetailsSubComponent

        @BindsInstance
        fun view(view: IContentDetailsContract.View): Builder

        @BindsInstance
        fun viewModel(viewModel: IContentDetailsContract.ViewModel): Builder
    }
}