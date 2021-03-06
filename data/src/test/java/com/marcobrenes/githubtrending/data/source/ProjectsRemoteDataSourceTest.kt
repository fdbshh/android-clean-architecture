package com.marcobrenes.githubtrending.data.source

import com.marcobrenes.githubtrending.data.model.ProjectEntity
import com.marcobrenes.githubtrending.data.repository.ProjectsRemote
import com.marcobrenes.githubtrending.data.test.factory.DataFactory
import com.marcobrenes.githubtrending.data.test.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ProjectsRemoteDataSourceTest {

    private val remote = mock<ProjectsRemote>()
    private val store = ProjectsRemoteDataSource(remote)

    @Test fun getProjectsCompletes() {
        stubRemoteGetProjects(Flowable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProjects(Flowable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveProjects(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }

    private fun stubRemoteGetProjects(stub: Flowable<List<ProjectEntity>>) {
        whenever(remote.getProjects()) doReturn stub
    }
}
