package dk.osl.intelligentbil.Retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oliver on 11-05-2018.
 */

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://130.226.195.249:3000/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {


            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json)")
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();


            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

