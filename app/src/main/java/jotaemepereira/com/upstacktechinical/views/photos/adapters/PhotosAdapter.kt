package jotaemepereira.com.upstacktechinical.views.photos.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import jotaemepereira.com.upstacktechinical.R
import jotaemepereira.com.upstacktechinical.model.Photo
import jotaemepereira.com.upstacktechinical.utils.listen
import kotlinx.android.synthetic.main.layout_item_photo.view.*
import com.ceylonlabs.imageviewpopup.ImagePopup



class PhotosAdapter(val photos: ArrayList<Photo>, val itemClickListener: (Photo) -> Unit): RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_photo, parent, false))
                .listen { position, _ ->
                    val item = photos[position]
                    itemClickListener(item)
                }
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBindViewHolder(photos[position])
    }

    fun updateResults(newPhotos: ArrayList<Photo>) {
        photos.clear()
        photos.addAll(newPhotos)

        notifyDataSetChanged()
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun onBindViewHolder(photo: Photo) {
            Picasso.get()
                    .load(photo.url)
                    .into(itemView.photo)
        }
    }
}