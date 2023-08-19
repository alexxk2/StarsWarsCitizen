package com.example.starswarscitizen.presentation.favourite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starswarscitizen.R
import com.example.starswarscitizen.databinding.FragmentFavouriteBinding
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.presentation.favourite.models.FavouritesScreenState
import com.example.starswarscitizen.presentation.favourite.view_model.FavouriteViewModel
import com.example.starswarscitizen.presentation.search.ui.SearchAdapter
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouriteViewModel by viewModel()
    private lateinit var favouriteAdapter: SearchAdapter
    private lateinit var badge: BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        badge = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            ?.getBadge(R.id.favouriteFragment)!!

        setRecyclerView()
        viewModel.getFavourites()

        viewModel.listOfFavourite.observe(viewLifecycleOwner){listOfFavourites->
            favouriteAdapter.submitList(listOfFavourites)
            badge.number = listOfFavourites.size
        }

        viewModel.screenState.observe(viewLifecycleOwner){screenState->
            manageScreenContent(screenState)
        }

    }

    private fun setRecyclerView() {
        favouriteAdapter =
            SearchAdapter(requireContext(), object : SearchAdapter.SearchActionListener {

                override fun onItemClick(starWarsItem: StarWarsItem) {

                }

                override fun onAddFavouriteClick(starWarsItem: StarWarsItem) {

                }

                override fun onRemoveFavouriteClick(starWarsItem: StarWarsItem) {
                    viewModel.removeFromFavourites(starWarsItem)
                }
            })
        binding.recyclerView.adapter = favouriteAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }


    private fun manageScreenContent(screenState: FavouritesScreenState) {
        with(binding) {
            when (screenState) {
                FavouritesScreenState.Content -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    emptyLayout.visibility = View.GONE
                }

                FavouritesScreenState.Empty -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }

                FavouritesScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                }

                FavouritesScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    emptyLayout.visibility = View.GONE
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}