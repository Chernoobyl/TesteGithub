package br.com.chernoobyl.testegithub.presentation.shared

import com.bumptech.glide.RequestManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object GlideInstance : KoinComponent {
    val instance: RequestManager by inject()
}