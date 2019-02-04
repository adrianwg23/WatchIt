package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.watchit.contentdetails.ContentDetailsActivity
import dagger.Subcomponent

@ContentDetailsScope
@Subcomponent(modules = [ContentDetailsModule::class])
interface ContentDetailsSubComponent {
    fun inject(contentDetailsActivity: ContentDetailsActivity)
}