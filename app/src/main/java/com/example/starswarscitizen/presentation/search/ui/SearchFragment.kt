package com.example.starswarscitizen.presentation.search.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starswarscitizen.R
import com.example.starswarscitizen.databinding.FragmentSearchBinding
import com.example.starswarscitizen.domain.models.StarWarsItem
import com.example.starswarscitizen.presentation.search.models.SearchScreenState
import com.example.starswarscitizen.presentation.search.view_model.SearchViewModel
import com.example.starswarscitizen.presentation.search.view_model.SearchViewModel.Companion.SEARCH_DEBOUNCE_DELAY
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchResult: List<StarWarsItem>
    private lateinit var badge: BadgeDrawable
    private var toastJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        badge = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            ?.getBadge(R.id.searchFragment)!!

        setRecyclerView()

        /*binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    if (binding.searchEditText.text?.length!! < 2) {
                        Toast.makeText(requireContext(), R.string.toast_text, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.startSearch(binding.searchEditText.text.toString())
                    }

                    true
                }

                else -> false
            }

        }*/

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            searchAdapter.submitList(results)
            searchResult = results

            badge.number = results.size
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            manageScreenContent(screenState)
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(s)
                toastDebounce(s)

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setRecyclerView() {
        searchAdapter =
            SearchAdapter(requireContext(), object : SearchAdapter.SearchActionListener {

                override fun onItemClick(starWarsItem: StarWarsItem) {

                }

                override fun onAddFavouriteClick(starWarsItem: StarWarsItem) {
                    viewModel.addToFavourite(starWarsItem)
                }

                override fun onRemoveFavouriteClick(starWarsItem: StarWarsItem) {
                    viewModel.removeFromFavourite(starWarsItem)
                }
            })
        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun manageScreenContent(screenState: SearchScreenState) {
        with(binding) {
            when (screenState) {
                SearchScreenState.Content -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    introLayout.visibility = View.GONE
                    notFoundLayout.visibility = View.GONE
                    hideKeyboard()
                }

                SearchScreenState.Empty -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    introLayout.visibility = View.GONE
                    notFoundLayout.visibility = View.VISIBLE
                    hideKeyboard()
                }

                SearchScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    introLayout.visibility = View.GONE
                    notFoundLayout.visibility = View.GONE
                    hideKeyboard()
                }

                SearchScreenState.Intro -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    introLayout.visibility = View.VISIBLE
                    notFoundLayout.visibility = View.GONE
                }

                SearchScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    introLayout.visibility = View.GONE
                    notFoundLayout.visibility = View.GONE
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.setIntro()
    }


    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.searchConstraintLayout.windowToken, 0)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toastDebounce(s: CharSequence?) {
        if (!s.isNullOrEmpty() && s.length < 2) {

            toastJob?.cancel()

            toastJob = viewLifecycleOwner.lifecycleScope.launch {
                delay(SEARCH_DEBOUNCE_DELAY)
                hideKeyboard()
                Snackbar.make(
                    binding.searchConstraintLayout,
                    R.string.toast_text,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 3000L
    }


}