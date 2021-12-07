package com.example.aac.photopage.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.aac.R
import com.example.aac.databinding.PhotoListItemBinding
import com.example.aac.photopage.data.model.Photo

class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PhotoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = DataBindingUtil.inflate<PhotoListItemBinding>(LayoutInflater.from(parent.context),
            R.layout.photo_list_item,parent,false)
        return PhotoViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class PhotoViewHolder(private val binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val photoImageView: ImageView = binding.photoImageView
        fun bind(photo: Photo?) {
            binding.photo = photo
//            Glide.with(binding.photoImageView).load("http://goo.gl/gEgYUd").error(R.drawable.ic_baseline_add_24).into(photoImageView)
            val url = GlideUrl(photo?.thumbnailUrl, LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build());
            Glide.with(binding.photoImageView).load(url).error(R.drawable.ic_baseline_add_24).into(photoImageView)
        }

        companion object {
            fun create(binding: PhotoListItemBinding): PhotoViewHolder {
                return PhotoViewHolder(binding)
            }
        }
    }

    class PhotoComparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }
}