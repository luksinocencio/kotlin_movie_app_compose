package br.com.devmeist3r.core.util

import br.com.devmeist3r.BuildConfig

fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"
fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"