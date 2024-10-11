package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.screens.details.FilmDetailsScreen
import com.example.presentation.screens.details.FilmDetailsViewModel
import com.example.presentation.screens.list.FilmsListScreen
import com.example.presentation.theme.SequeniaTestTaskTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFilmDetails : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.details_film_fragment, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.composeViewDetails)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SequeniaTestTaskTheme {
                    FilmDetailsScreen {
                        findNavController().popBackStack()
                    }
                }
            }
        }
        return view
    }
}