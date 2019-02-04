package com.example.adrianwong.watchit.contentdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentDetailsViewModel(override val favouriteState: MutableLiveData<Boolean> = MutableLiveData()) :
    ViewModel(), IContentDetailsContract.ViewModel