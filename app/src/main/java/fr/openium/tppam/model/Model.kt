package fr.openium.tppam.model


data class Info(val place: Place, val movieShowtimes: List<MovieShowTime>)

data class Place(val theater: Theater)
data class Theater(val name: String)

data class MovieShowTime(val onShow: OnShow)
data class OnShow(val movie: Movie)
data class Movie(val title: String, val poster: ApiImage)

data class ApiImage(val path: String, val href: String)