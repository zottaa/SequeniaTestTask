package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.screens.list.FilmsListScreen
import com.example.presentation.theme.SequeniaTestTaskTheme

private const val FILM_ID = "filmId"

class FragmentFilmList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_film_fragment, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.composeViewList)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SequeniaTestTaskTheme {
                    FilmsListScreen(navigateToFilmDetails = { id ->
                        findNavController().navigate(
                            R.id.details_film_fragment,
                            bundleOf(FILM_ID to id)
                        )
                    })
                }
            }
        }
        return view
    }
}