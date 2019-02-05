package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.domain.DispatcherProvider
import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.*
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic
import com.example.adrianwong.watchit.mappers.TvShowEntityToTvShowMapper
import com.example.adrianwong.watchit.mappers.TvShowToTvShowEntityMapper
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule(private val view: IContentListContract.View, private val viewModel: IContentListContract.ViewModel) {

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
    fun providesTvShowListAdapter(tvShowListLogic: IContentListContract.Logic): ContentListAdapter {
        return ContentListAdapter(tvShowListLogic, view as TvShowListFragment)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListLogic(getPopularTvShows: GetPopularTvShows): IContentListContract.Logic {
        return TvShowListLogic(DispatcherProvider, view, viewModel, getPopularTvShows)
    }
}