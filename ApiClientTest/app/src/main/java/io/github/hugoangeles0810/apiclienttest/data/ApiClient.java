package io.github.hugoangeles0810.apiclienttest.data;

import android.support.annotation.VisibleForTesting;
import com.google.gson.Gson;
import io.github.hugoangeles0810.apiclienttest.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

  private static final String BASE_URL ="http://narnia.com/";

  private static IApiClient INSTANCE = null;

  public static IApiClient getInstance() {
    return getInstance(BASE_URL);
  }

  public static IApiClient getInstance(String baseUrl) {
    if (INSTANCE == null) {
      INSTANCE = createApiClient(baseUrl);
    }

    return INSTANCE;
  }

  @VisibleForTesting
  public static void destroyInstance() {
    INSTANCE = null;
  }

  private static IApiClient createApiClient(String baseUrl) {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .build();

    return retrofit.create(IApiClient.class);
  }

}
