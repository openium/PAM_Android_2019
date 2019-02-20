package fr.openium.tppam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.openium.tppam.R
import fr.openium.tppam.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

typealias OnMovieClickedListener = (Movie) -> Unit

class MovieListAdapter(private var movieList: List<Movie>, private val listener: OnMovieClickedListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.itemView.textViewTitle.text = movie.title
        Picasso.get().load(movie.poster.href).fit().centerInside().into(holder.itemView.imageView)
        holder.itemView.setOnClickListener {
            listener.invoke(movie)
        }
    }

    fun updateMovies(movies: List<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)