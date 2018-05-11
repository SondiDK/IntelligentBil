package dk.osl.intelligentbil.testretro;

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
private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjNzBkY2Y2MC0yYWMw" +
        "LTAxMzYtZjc5Ni0wYTU4NjQ2MTIzMDUiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTI" +
        "0NjY1NjIwLCJwdWIiOiJzZW1jIiwidGl0bGUiOiJ2YWluZ2xvcnkiLCJhcHAiOiJnbG9yeXN0Y" +
        "XRkZGQiLCJzY29wZSI6ImNvbW11bml0eSIsImxpbWl0IjoxMH0.F2bUydBWf7DtbqL-KEbSY" +
        "36kkcndNr7z9fJzLPfwSjk";
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

          /*
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();

*/
            retrofit = new retrofit2.Retrofit.Builder()
                    //.client(client)//
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

