package com.example.adrianwong.data

import com.example.adrianwong.data.entities.MovieData
import com.example.adrianwong.data.entities.TvShowData
import com.example.adrianwong.data.mappers.MovieDataToMovieEntityMapper
import com.example.adrianwong.data.mappers.MovieEntityToMovieDataMapper
import com.example.adrianwong.data.mappers.TvShowDataToTvShowEntityMapper
import com.example.adrianwong.data.mappers.TvShowEntityToTvShowDataMapper
import com.example.adrianwong.domain.entities.MovieEntity
import com.example.adrianwong.domain.entities.TvShowEntity

fun MovieData.toMovieEntity() = MovieDataToMovieEntityMapper.mapFrom(this)

fun MovieEntity.toMovieData() = MovieEntityToMovieDataMapper.mapFrom(this)

fun TvShowData.toTvShowEntity() = TvShowDataToTvShowEntityMapper.mapFrom(this)

fun TvShowEntity.toTvShowData() = TvShowEntityToTvShowDataMapper.mapFrom(this)