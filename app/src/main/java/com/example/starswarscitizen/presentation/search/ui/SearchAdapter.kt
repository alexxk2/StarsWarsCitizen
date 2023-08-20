package com.example.starswarscitizen.presentation.search.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starswarscitizen.R
import com.example.starswarscitizen.databinding.SearchItemBinding
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.domain.models.StarWarsObject

class SearchAdapter(
    private val context: Context,
    private val actionListener: SearchActionListener

) : ListAdapter<StarWarsItem, SearchAdapter.SearchViewHolder>(DiffCallBack), View.OnClickListener {

    class SearchViewHolder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.addToFavouriteButton.setOnClickListener(this)
        binding.removeFromFavouriteButton.setOnClickListener(this)

        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            when (item.type) {
                StarWarsObject.People -> {
                    Glide.with(context)
                        .load(R.drawable.ic_people)
                        .placeholder(R.drawable.ic_placeholder)
                        .fitCenter()
                        .into(itemImage)

                    itemInfo1.text = context.getString(R.string.gender_field, item.infoField1)
                    itemInfo2.text = context.getString(R.string.starships_field, item.infoField2)

                }

                StarWarsObject.Planet -> {
                    Glide.with(context)
                        .load(R.drawable.ic_planet)
                        .placeholder(R.drawable.ic_placeholder)
                        .fitCenter()
                        .into(itemImage)

                    itemInfo1.text = context.getString(R.string.diameter_field, item.infoField1)
                    itemInfo2.text = context.getString(R.string.population_field, item.infoField2)

                }

                StarWarsObject.Starship -> {
                    Glide.with(context)
                        .load(R.drawable.ic_starship_3)
                        .placeholder(R.drawable.ic_placeholder)
                        .fitCenter()
                        .into(itemImage)

                    itemInfo1.text = context.getString(R.string.model_field, item.infoField1)
                    itemInfo2.text = context.getString(R.string.manufacturer_field, item.infoField2)
                    itemInfo3.text = context.getString(R.string.population_field, item.infoField3)

                }
            }
            itemName.text = item.name

            if (item.isFavourite){
                addToFavouriteButton.visibility = View.INVISIBLE
                removeFromFavouriteButton.visibility = View.VISIBLE
            }
            else{
                addToFavouriteButton.visibility = View.VISIBLE
                removeFromFavouriteButton.visibility = View.INVISIBLE
            }

            root.tag = item
            addToFavouriteButton.tag = item
            removeFromFavouriteButton.tag = item
        }

    }

    override fun onClick(v: View?) {
        val starWarsItem = v?.tag as StarWarsItem
        when (v.id) {
            R.id.add_to_favourite_button -> actionListener.onAddFavouriteClick(starWarsItem)
            R.id.remove_from_favourite_button -> actionListener.onRemoveFavouriteClick(starWarsItem)
            else -> actionListener.onItemClick(starWarsItem)
        }
    }

    interface SearchActionListener {
        fun onItemClick(starWarsItem: StarWarsItem)
        fun onAddFavouriteClick(starWarsItem: StarWarsItem)
        fun onRemoveFavouriteClick(starWarsItem: StarWarsItem)
    }


    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<StarWarsItem>() {

            override fun areItemsTheSame(oldItem: StarWarsItem, newItem: StarWarsItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: StarWarsItem, newItem: StarWarsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}