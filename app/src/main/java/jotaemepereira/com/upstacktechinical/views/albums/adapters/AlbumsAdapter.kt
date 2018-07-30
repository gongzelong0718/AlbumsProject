package jotaemepereira.com.upstacktechinical.views.albums.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jotaemepereira.com.upstacktechinical.R
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.utils.listen
import kotlinx.android.synthetic.main.layout_item_album.view.*


class AlbumsAdapter(val albums: ArrayList<Album>, val itemClickListener: (Album) -> Unit): RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_album, parent, false))
                .listen { position, _ ->
                    val item = albums[position]
                    itemClickListener(item)
                }
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.onBindViewHolder(albums[position])
    }

    fun updateResults(newAlbums: ArrayList<Album>) {
        albums.clear()
        albums.addAll(newAlbums)

        notifyDataSetChanged()
    }

    class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun onBindViewHolder(album: Album) {
            itemView.albumTitle.text = album.title
        }
    }
}