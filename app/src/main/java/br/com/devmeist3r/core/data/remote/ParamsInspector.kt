package br.com.devmeist3r.core.data.remote

import br.com.devmeist3r.BuildConfig
import br.com.devmeist3r.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInspector: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .addQueryParameter(Constants.API_KEY_PARAM, BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}