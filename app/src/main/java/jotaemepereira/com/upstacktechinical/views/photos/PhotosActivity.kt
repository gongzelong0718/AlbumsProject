package jotaemepereira.com.upstacktechinical.views.photos

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import jotaemepereira.com.upstacktechinical.R
import jotaemepereira.com.upstacktechinical.base.BaseActivity
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.model.Photo
import jotaemepereira.com.upstacktechinical.utils.SnackbarUtils
import jotaemepereira.com.upstacktechinical.views.photos.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.activity_albums.*
import javax.inject.Inject
import com.ceylonlabs.imageviewpopup.ImagePopup


class PhotosActivity: BaseActivity(), PhotosView {

    @Inject lateinit var presenter: PhotosPresenter

    private lateinit var album: Album
    private val photos = ArrayList<Photo>()

    companion object {
        private const val ALBUM = "album"

        fun newIntent(context: Context, album: Album): Intent =
                Intent(context, PhotosActivity::class.java).apply {
                    putExtra(ALBUM, album)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        graph.inject(this);
        presenter.attachView(this)
        album = intent.extras.get("album") as Album

        setupActionBar()
        setupRecyclerView()

        presenter.loadPhotos(album.id)
    }


    private fun setupActionBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = album.title
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        recycler.layoutManager = gridLayoutManager
        recycler.adapter =  PhotosAdapter(photos, { photo -> onClickedPhoto(photo)  })
    }

    private fun onClickedPhoto(photo: Photo) {
        val imagePopup = ImagePopup(this)
        imagePopup.backgroundColor = Color.BLACK
        imagePopup.isFullScreen = true
        imagePopup.isHideCloseIcon = false
        imagePopup.isImageOnClickClose = true
        imagePopup.initiatePopupWithGlide(photo.url)
        imagePopup.viewPopup()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPhotosLoaded(photos: List<Photo>) {
        val adapter = recycler.adapter as? PhotosAdapter
        adapter?.updateResults(ArrayList(photos))
    }

    override fun onError(errorMessage: String) {
        SnackbarUtils.showErrorMessage(errorMessage, findViewById(android.R.id.content))
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