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
import javax.inject.Named

@Module
class TvShowsModule {

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
    fun providesTvShowListAdapter(tvShowListLogic: IContentListContract.Logic): TvShowListAdapter {
        return TvShowListAdapter(tvShowListLogic)
    }

    @Provides
    @TvShowsScope
    fun providesTvShowListLogic(getPopularTvShows: GetPopularTvShows, searchTvShow: SearchTvShow): IContentListContract.Logic {
        return TvShowListLogic(DispatcherProvider, getPopularTvShows, searchTvShow)
    }
}