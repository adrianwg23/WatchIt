package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.data.repository.TvShowRepositoryImpl
import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListVMFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule(private val view: IContentListContract.View,
                    private val viewModel: IContentListContract.TvShowListViewModel) {

    @Provides
    @TvShowsScope
    fun providesGetPopularTvShows(tvShowRepositoryImpl: TvShowRepositoryImpl): GetPopularTvShows {
        return GetPopularTvShows(tvShowRepositoryImpl)
    }

    @Provides
    @TvShowsScope
    fun providesSearchTvShow(tvShowRepositoryImpl: TvShowRepositoryImpl): SearchTvShow {
        return SearchTvShow(tvShowRepositoryImpl)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListLogic(getPopularTvShows: GetPopularTvShows, searchTvShow: SearchTvShow): TvShowListLogic {
        return TvShowListLogic(DispatcherProvider, view, viewModel, getPopularTvShows, searchTvShow)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListVMFactory(): TvShowListVMFactory = TvShowListVMFactory()
}