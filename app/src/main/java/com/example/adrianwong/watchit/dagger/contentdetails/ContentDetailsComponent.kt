package com.example.adrianwong.watchit.dagger.contentdetails

import com.example.adrianwong.watchit.dagger.MainComponent
import dagger.Component

@ContentDetailsScope
@Component(modules = [ContentDetailsModule::class], dependencies = [MainComponent::class])
interface ContentDetailsComponent {

}