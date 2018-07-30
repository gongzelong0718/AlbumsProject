package jotaemepereira.com.upstacktechinical.views.albums

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import jotaemepereira.com.upstacktechinical.R
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.base.BaseActivity
import jotaemepereira.com.upstacktechinical.utils.SnackbarUtils
import jotaemepereira.com.upstacktechinical.views.albums.adapters.AlbumsAdapter
import jotaemepereira.com.upstacktechinical.views.photos.PhotosActivity
import kotlinx.android.synthetic.main.activity_albums.*
import javax.inject.Inject

class AlbumsActivity : BaseActivity(), AlbumsView {

    @Inject lateinit var presenter: AlbumsPresenter

    private val albums = ArrayList<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        graph.inject(this);
        presenter.attachView(this)

        setupRecyclerView()

        presenter.loadAlbums()
    }

    private fun setupRecyclerView() {
        val linearLayout = LinearLayoutManager(this)
        recycler.layoutManager = linearLayout
        recycler.adapter =  AlbumsAdapter(albums, { album -> onClickedAlbum(album)  })
    }

    private fun onClickedAlbum(album: Album) {
        startActivity(PhotosActivity.newIntent(this, album))
    }

    override fun onError(errorMessage: String) {
        SnackbarUtils.showErrorMessage(errorMessage, findViewById(android.R.id.content))
    }

    override fun onAlbumsLoaded(albums: List<Album>) {
        val adapter = recycler.adapter as? AlbumsAdapter
        adapter?.updateResults(ArrayList(albums))
    }

    override fun showProgress(showProgress: Boolean) {
        when(showProgress) {
            true -> progress.visibility = View.VISIBLE
            false -> {
                progress.visibility = View.GONE
            }
        }
    }
}
