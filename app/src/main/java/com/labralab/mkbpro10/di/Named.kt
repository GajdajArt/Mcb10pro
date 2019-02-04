package com.labralab.mkbpro10.di

interface Schedulers {

    companion object {

        public const val IO_SCHEDULER = "IO_SCHEDULER"

        public const val UI_SCHEDULER = "UI_SCHEDULER"

        public const val SINGLE_THREAD_SCHEDULER = "SINGLE_THREAD_SCHEDULER"
    }
}