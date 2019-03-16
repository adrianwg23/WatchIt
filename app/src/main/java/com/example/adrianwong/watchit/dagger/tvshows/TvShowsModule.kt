package com.example.adrianwong.watchit.dagger.tvshows

import com.example.adrianwong.domain.repository.ITvShowRepository
import com.example.adrianwong.domain.usecases.GetPopularTvShows
import com.example.adrianwong.domain.usecases.SearchTvShow
import com.example.adrianwong.watchit.contentlist.ContentListAdapter
import com.example.adrianwong.watchit.contentlist.IContentListContract
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListFragment
import com.example.adrianwong.watchit.contentlist.tvshowlist.TvShowListLogic
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TvShowsModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @TvShowsScope
        fun providesGetPopularTvShows(tvShowRepository: ITvShowRepository): GetPopularTvShows {
            return GetPopularTvShows(tvShowRepository)
        }

        @JvmStatic
        @Provides
        @TvShowsScope
        fun providesSearchTvShow(tvShowRepository: ITvShowRepository): SearchTvShow {
            return SearchTvShow(tvShowRepository)
        }

        @JvmStatic
        @Provides
        @TvShowsScope
        fun providesTvShowListAdapter(tvShowListLogic: IContentListContract.Logic,
                                      view: IContentListContract.View): ContentListAdapter {
            return ContentListAdapter(tvShowListLogic, view as TvShowListFragment)
        }
    }

    @Binds
    @TvShowsScope
    abstract fun bindsIContentListContractLogic(tvShowListLogic: TvShowListLogic): IContentListContract.Logic
}