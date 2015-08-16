package com.piasy.common.android.utils.provider;

import android.app.Application;
import com.piasy.common.Constants;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Piasy{github.com/Piasy} on 15/7/23.
 */
public class RestProvider {

    /*public static RestAdapter provideRestAdapter() {
        return RestAdapterHolder.sRestAdapter;
    }

    private static class RestAdapterHolder {
        // lazy instantiate
        private static volatile RestAdapter sRestAdapter =
                new RestAdapter.Builder().setEndpoint(Constants.GITHUB_SERVER_ENDPOINT)
                        .setConverter(new GsonConverter(GsonProvider.provideGson(
                                // not available here
                                // back to dcl singleton implementation :(
                        )))
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();
    }*/

    private static volatile RestAdapter sRestAdapter = null;

    public static RestAdapter provideRestAdapter(Application application) {
        if (sRestAdapter == null) {
            synchronized (RestProvider.class) {
                if (sRestAdapter == null) {
                    sRestAdapter =
                            new RestAdapter.Builder().setEndpoint(Constants.GITHUB_SERVER_ENDPOINT)
                                    .setConverter(new GsonConverter(
                                            GsonProvider.provideGson(application)))
                                    .setLogLevel(RestAdapter.LogLevel.FULL)
                                    .build();
                }
            }
        }

        return sRestAdapter;
    }
}