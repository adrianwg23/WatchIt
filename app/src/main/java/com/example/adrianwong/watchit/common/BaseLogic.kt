package com.example.adrianwong.watchit.common

import com.example.adrianwong.domain.DispatcherProvider
import kotlinx.coroutines.Job

abstract class BaseLogic(val dispatcher: DispatcherProvider) {
    protected lateinit var jobTracker: Job
}