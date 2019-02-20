package fr.openium.tppam.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.openium.tppam.R
import fr.openium.tppam.adapter.MovieListAdapter
import fr.openium.tppam.model.Info
import fr.openium.tppam.rest.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    var call: Call<Info>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        call = ApiHelper.api.getInfo()
        call!!.enqueue(object : Callback<Info> {
            override fun onFailure(call: Call<Info>, t: Throwable) {
                Timber.e(t)
                Toast.makeText(applicationContext, "Network error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Info>, response: Response<Info>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    displayMovie(response.body()!!)
                } else {
                    Timber.e(response.message())
                    Toast.makeText(applicationContext, "Network error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_map) {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
    }

    fun displayMovie(info: Info) {
        val movies = info.movieShowtimes.map { it.onShow.movie }
        val adapter = recyclerView.adapter
        if (adapter is MovieListAdapter) {
            adapter.updateMovies(movies)
        } else {
            recyclerView.adapter = MovieListAdapter(movies, {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)
            })
        }
    }
}
