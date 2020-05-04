package com.softsquared.mangoplate.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.softsquared.mangoplate.config.XAccessTokenInterceptor;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApplicationClass extends Application {
    private static ApplicationClass instance;

    private static DisplayMetrics metrics;

    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
    public static MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    // 테스트 서버 주소
//    public static String BASE_URL = "http://apis.newvement.com/";
    // 실서버 주소
    public static String BASE_URL = "http://product.eunjiha.site/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "MANGO_PLATE_BY_YOONDY";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());

        if(sSharedPreferences == null)
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

        metrics = getResources().getDisplayMetrics();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    public static ApplicationClass getInstance() {
        if(instance == null)
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");

        return instance;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();
        }

        return retrofit;
    }

    public static int getScreenWidth() {
        return metrics == null ? 0 : metrics.widthPixels;
    }

    public static int getScreenHeight() {
        return metrics == null ? 0 : metrics.heightPixels;
    }

    public class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_ACCOUNT};
                }

                @Override
                public boolean isUsingWebviewTimer() { return false; }

                @Override
                public boolean isSecureMode() { return false; }

                @NotNull
                @Override
                public ApprovalType getApprovalType() { return ApprovalType.INDIVIDUAL; }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return ApplicationClass::getInstance;
        }
    }
}
