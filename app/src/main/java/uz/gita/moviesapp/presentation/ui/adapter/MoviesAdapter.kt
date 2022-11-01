package uz.gita.moviesapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.moviesapp.BuildConfig.BASE_URL_IMAGE
import uz.gita.moviesapp.R
import uz.gita.moviesapp.data.sources.common.models.Results
import uz.gita.moviesapp.databinding.ItemMoviesBinding
import uz.gita.moviesapp.utils.SingleBlock
import uz.gita.moviesapp.utils.extension.usingJavaStringFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MoviesAdapter : PagingDataAdapter<Results, MoviesAdapter.ViewHolder>(MoviesCallBack) {

    object MoviesCallBack : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem == newItem


    }

    inner class ViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(): Results? = with(binding) {
            getItem(absoluteAdapterPosition)?.apply {
                val format = NumberFormat.getNumberInstance(Locale.getDefault()).format(vote_count)
                val format1 = DecimalFormat("#.#")
                Glide
                    .with(moviesImage)
                    .load(BASE_URL_IMAGE + poster_path)
                    .placeholder(R.drawable.placeholder)
                    .into(moviesImage)
                movieTitle.text = title
                movieDate.text = release_date
                moviesOverView.text = overview
                voteAverage.text = format1.format(vote_average)
                voteCount.text = format.toString()
                Glide
                    .with(bgIMage)
                    .load(BASE_URL_IMAGE + backdrop_path)
                    .placeholder(R.drawable.placeholder)
                    .into(bgIMage)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMoviesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}