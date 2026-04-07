package org.dzair.mobile.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.dzair.mobile.MainViewModel
import org.dzair.mobile.databinding.FragmentComposeBinding
import org.dzair.mobile.ui.utils.AppTheme
import org.dzair.mobile.utils.applyWindowInsetsAsMargins
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ConnectFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModel()
    private var _viewBinding: FragmentComposeBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val composeView: ComposeView get() = viewBinding.composeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _viewBinding = FragmentComposeBinding.inflate(inflater, container, false)
        return composeView.apply { applyWindowInsetsAsMargins() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. On lance la connexion à ton serveur automatiquement en arrière-plan
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.switchServer("https://play.dzair.cloud")
        }

        // Apply window insets
        ViewCompat.requestApplyInsets(composeView)

        // 2. On affiche juste un message pour tes cousins, sans aucun bouton ni champ de texte !
        composeView.setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Connexion à Binatna...",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
