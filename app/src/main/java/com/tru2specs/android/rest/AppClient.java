package com.tru2specs.android.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tru2specs.android.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tru2specs.android.util.Constants.BASE_URL;

/**
 * Created by GP00471911 on 02-03-2017.
 */

public class AppClient {
//        private static final String BASE_URL = "http://www.mocky.io/v2/";
//    private static final String BASE_URL = "http://104.211.185.234/services/";
//    private static final String BASE_URL_SERVICE = "services/";
//    private static final String BASE_URL = "http://1d981803.ngrok.io/Services/";


    private static APIService mService;

    public AppClient() {

//        Authenticator proxyAuthenticator=new Authenticator() {
//        }
//        Authenticator proxyAuthenticator = new Authenticator() {
//            @Override
//            public Request authenticate(Route route, Response response) throws IOException {
//                String credential = Credentials.basic(username, password);
//                return response.request().newBuilder()
//                        .header("Proxy-Authorization", credential)
//                        .build();
//            }
//        };
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
//                .proxyAuthenticator(proxyAuthenticator)
//                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();

        Retrofit retrofit = new Retrofit.Builder()

//                .baseUrl(Constants.BASE_URL + BASE_URL_SERVICE)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mService = retrofit.create(APIService.class);
    }

    public static APIService getApiService() {
        if (mService == null) {
            new AppClient();
        }
        return mService;
    }
}
