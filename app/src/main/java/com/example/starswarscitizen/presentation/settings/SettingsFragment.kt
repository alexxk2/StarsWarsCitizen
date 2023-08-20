package com.example.starswarscitizen.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starswarscitizen.R
import com.example.starswarscitizen.app.App
import com.example.starswarscitizen.app.IS_DARK_THEME
import com.example.starswarscitizen.app.SHARED_PREFS
import com.example.starswarscitizen.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDarkThemeSwitcher()

        binding.darkThemeSwitcher.setOnCheckedChangeListener { _, isChecked ->
            (activity?.applicationContext as App).switchNightMode(isChecked)
        }

    }

    private fun setDarkThemeSwitcher() {
        val sharedPrefs = activity?.getSharedPreferences(SHARED_PREFS, 0)
        val isDarkTheme = sharedPrefs?.getBoolean(IS_DARK_THEME, false)
        binding.darkThemeSwitcher.isChecked = isDarkTheme!!

        binding.darkThemeMessage.text = if (isDarkTheme) {
            requireContext().getString(R.string.light_side_of_the_force)
        } else {
            requireContext().getString(R.string.dark_side_of_the_force)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}