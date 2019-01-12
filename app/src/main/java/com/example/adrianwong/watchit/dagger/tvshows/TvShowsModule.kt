package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListAdapter
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule(private val view: IContentListContract.View,
                    private val viewModel: IContentListContract.TvShowListViewModel) {

    @Provides
    @TvShowsScope
    fun providesGetPopularTvShows(tvShowRepository: ITvShowRepository): GetPopularTvShows {
        return GetPopularTvShows(tvShowRepository)
    }

    @Provides
    @TvShowsScope
    fun providesSearchTvShow(tvShowRepository: ITvShowRepository): SearchTvShow {
        return SearchTvShow(tvShowRepository)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListAdapter(tvShowListLogic: TvShowListLogic): TvShowListAdapter {
        return TvShowListAdapter(tvShowListLogic)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListLogic(getPopularTvShows: GetPopularTvShows, searchTvShow: SearchTvShow): TvShowListLogic {
        return TvShowListLogic(DispatcherProvider, view, viewModel, getPopularTvShows, searchTvShow)
    }
}