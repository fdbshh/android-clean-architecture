package com.marcobrenes.mobileui.injection

import android.app.Application
import com.marcobrenes.githubtrending.domain.repository.ProjectsRepository
import com.marcobrenes.mobileui.TestApplication
import com.marcobrenes.mobileui.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UIModule::class,
    TestRemoteModule::class])
interface TestApplicationComponent {

    fun projectsRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}