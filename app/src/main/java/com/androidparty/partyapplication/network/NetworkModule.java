package com.androidparty.partyapplication.network;

import android.content.Context;
import android.preference.PreferenceManager;

import com.androidparty.partyapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 12/20/2017.
 */
@Module
@Singleton
public class NetworkModule {

    public static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Provides
    @Singleton
    public Gson provideGson() {
        return gson;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor authInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        httpClient.retryOnConnectionFailure(false);
        httpClient.addInterceptor(authInterceptor);
        return httpClient.build();
    }

    @Provides
    @Singleton
    public Interceptor provideAuthInterceptor(Context context) {
        return (chain) -> {
            Request original = chain.request();
            String auth = PreferenceManager.getDefaultSharedPreferences(context).getString("token", null);
            Request.Builder builder = original.newBuilder();
            if (auth != null)
                builder.addHeader("Authorization", "Bearer " + auth);
            Request request = builder.method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient httpClient, Context context) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(context.getString(R.string.endpoint))
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    public AppRemoteApi provideAppRemoteApi(Retrofit retrofit) {
        return retrofit.create(AppRemoteApi.class);
    }
}
