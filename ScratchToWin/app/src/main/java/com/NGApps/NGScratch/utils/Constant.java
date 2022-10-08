package com.NGApps.NGScratch.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.NGApps.NGScratch.App;
import com.NGApps.NGScratch.R;
import com.NGApps.NGScratch.services.PointsService;
import com.NGApps.NGScratch.sharedPref.PrefManager;
import java.util.regex.Pattern;

public class Constant {
    public static final String IS_LOGIN = "IsLogin";
    public static final String USER_BLOCKED = "user_blocked";
    public static final String USER_NAME = "user_name";
    public static final String USER_NUMBER = "user_number";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_POINTS = "user_points";
    public static final String USER_REFFER_CODE = "user_reffer_code";
    public static final String LAST_DATE = "Last_Date";
    public static final String LAST_TIME_ADD_TO_SERVER = "last_time_added";
    public static final String REFER_CODE = "refer_code";
    public static final String LAST_DATE_SCRATCH_SILVER = "last_date_scratch_silver";
    public static final String LAST_DATE_SCRATCH_PLATINUM = "last_date_scratch_platinum";
    public static final String LAST_DATE_SCRATCH_GOLD = "last_date_scratch_gold";
    public static final String USER_IMAGE = "user_image";
    public static final String SCRATCH_COUNT_SILVER = "silver_scratch";
    public static final String SCRATCH_COUNT_PLATINUM = "platinum_scratch";
    public static final String SCRATCH_COUNT_GOLD = "gold_scratch";
    public static final String USER_ID = "user_id";
    public static final String IS_UPDATE = "user_update";
    public static final String USER_PASSWORD = "password";
    private static PrefManager prefManager;
    public static InterstitialAd interstitial_Ad;
    public static com.facebook.ads.InterstitialAd interstitialAd;
    public static RewardedAd rewarded_ad;
    public static boolean isShowInterstitial = true;
    public static boolean isShowRewarded = true;
    public static boolean isShowfacebookRewarded = true;
    public static boolean isAttachedInterstitial = true;
    public static boolean isAttachedfacebookInterstitial = true;
    public static boolean isShowFacebookInterstitial = true;
    public static RewardedAdLoadCallback adLoadCallback;
    public static final String TAG = "Constant";
    public static InterstitialAdListener interstitialAdListener;
    public static RewardedVideoAd rewardedVideoAd;
    public static ProgressDialog alertDialog;


    public static void loadInterstitialAd() {
        String typeOfAds = App.getContext().getResources().getString(R.string.ad_network);
        if (typeOfAds.equals("facebook")) {
            if (interstitialAd == null) {
                interstitialAd = new com.facebook.ads.InterstitialAd(App.getContext(), App.getContext().getResources().getString(R.string.facebook_interstitial_ad_id));
                AttachFacebookListner();
                interstitialAd.loadAd(
                        interstitialAd.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());
                isAttachedfacebookInterstitial = false;
            } else {
                interstitialAd.loadAd();
            }
        } else if (typeOfAds.equals("admob")) {
            if (interstitial_Ad == null) {
                interstitial_Ad = new InterstitialAd(App.getContext());
                interstitial_Ad.setAdUnitId(App.getContext().getResources().getString(R.string.admob_interstitial_ad_id));
                interstitial_Ad.loadAd(new AdRequest.Builder().build());
                AttachListner();
                isAttachedInterstitial = false;
            } else if (interstitial_Ad.isLoading()) {
                Log.e("TAG", "loadInterstitialAd: isLoading");
            } else {
                interstitial_Ad.loadAd(new AdRequest.Builder().build());
            }
        }
    }

    public static void AttachFacebookListner() {
        if (isAttachedInterstitial) {
            interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                    Log.e(TAG, "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                    Log.e(TAG, "Interstitial ad dismissed.");
                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                    if (!isShowFacebookInterstitial) {
                        showInterstitialAds();
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {
                    Log.d(TAG, "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    Log.d(TAG, "Interstitial ad impression logged!");
                }
            };
        }
    }

    public static void AttachListner() {
        if (isAttachedInterstitial) {
            interstitial_Ad.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.e("adLoaded", "adLoaded: interstitial");
                    if (!isShowInterstitial) {
                        showInterstitialAds();
                    }
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    Log.e("onAdClosed", "onAdClosed: ");
                    loadInterstitialAd();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    Log.e("adOpened", "adOpened: ");
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Log.e("adError", "adError: " + loadAdError.toString());
                }
            });
        }
    }


    public static void hideProgressDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public static void showInterstitialAds() {
        String typeOfAds = App.getContext().getResources().getString(R.string.ad_network);
        if (typeOfAds.equals("admob")) {
            if (interstitial_Ad != null && interstitial_Ad.isLoaded()) {
                hideProgressDialog();
                interstitial_Ad.show();

                isShowInterstitial = true;
            } else {
                hideProgressDialog();
                isShowInterstitial = false;
            }
        } else if (typeOfAds.equals("facebook")) {
            if (interstitialAd != null && interstitialAd.isAdLoaded()) {
                hideProgressDialog();
                interstitialAd.show();
                isShowFacebookInterstitial = true;
            } else {
                hideProgressDialog();
                isShowFacebookInterstitial = false;
            }
        }
    }

    public static void showRewardedAds(final Activity context) {
        String typeOfAds = App.getContext().getResources().getString(R.string.ad_network);
        if (typeOfAds.equals("admob")) {
            if (rewarded_ad != null && rewarded_ad.isLoaded()) {
                RewardedAdCallback adCallback = new RewardedAdCallback() {
                    @Override
                    public void onRewardedAdOpened() {

                    }

                    @Override
                    public void onRewardedAdClosed() {

                        loadRewardedVideo(context);
                    }

                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem reward) {

                    }

                    @Override
                    public void onRewardedAdFailedToShow(AdError adError) {

                    }
                };
                rewarded_ad.show(context, adCallback);
                isShowRewarded = true;
            } else {
                isShowRewarded = false;
                Constant.showToastMessage(context, "Video Ad not Ready");
            }
        } else if (typeOfAds.equals("facebook")) {
            if (rewardedVideoAd != null && rewardedVideoAd.isAdLoaded()) {
                rewardedVideoAd.show();
                isShowfacebookRewarded = true;
            } else {
                isShowfacebookRewarded = false;
                Constant.showToastMessage(context, "Video Ad not Ready");
            }
        }
    }

    public static void loadRewardedVideo(final Activity activity) {
        String typeOfAds = App.getContext().getResources().getString(R.string.ad_network);
        if (typeOfAds.equals("admob")) {
            if (rewarded_ad != null) {
                try {
                    rewarded_ad = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rewarded_ad = new RewardedAd(activity, activity.getResources().getString(R.string.admob_rewarded_video_id));
            AttachedRewaredCallBack(activity);
            rewarded_ad.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        } else if (typeOfAds.equals("facebook")) {
            if (rewardedVideoAd != null) {
                try {
                    rewardedVideoAd = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rewardedVideoAd = new RewardedVideoAd(App.getContext(), activity.getResources().getString(R.string.facebook_rewarded_video_id));
            RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    Log.e(TAG, "Rewarded video ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {

                    Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
                    if (!isShowfacebookRewarded) {
                        showRewardedAds(activity);
                    }

                }

                @Override
                public void onAdClicked(Ad ad) {
                    Log.d(TAG, "Rewarded video ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    Log.d(TAG, "Rewarded video ad impression logged!");
                }

                @Override
                public void onRewardedVideoCompleted() {

                    Log.d(TAG, "Rewarded video completed!");
                }

                @Override
                public void onRewardedVideoClosed() {
                    Log.d(TAG, "Rewarded video ad closed!");
                }
            };
            rewardedVideoAd.loadAd(
                    rewardedVideoAd.buildLoadAdConfig()
                            .withAdListener(rewardedVideoAdListener)
                            .build());
        }
    }

    public static void AttachedRewaredCallBack(final Activity activity) {
        if (adLoadCallback != null) {
            adLoadCallback = null;
        }
        adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                if (!isShowRewarded) {
                    showRewardedAds(activity);
                }
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
            }
        };
    }

    public static void GotoNextActivity(Context context, Class nextActivity, String msg) {
        if (context != null && nextActivity != null) {
            if (msg == null) {
                msg = "";
            }
            Intent intent = new Intent(context, nextActivity);
            intent.putExtra("Intent", msg);
            context.startActivity(intent);
        }
    }

    public static boolean isValidEmailAddress(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        boolean isMatches = pattern.matcher(email).matches();
        Log.e("Boolean Value", "" + isMatches);
        return isMatches;
    }

    public static void showToastMessage(Context context, String message) {
        if (context != null && message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void setString(Context context, String preKey, String setString) {
        if (prefManager == null) {
            prefManager = new PrefManager(context);
        }
        prefManager.setString(preKey, setString);
    }

    public static String getString(Context context, String prefKey) {
        if (prefManager == null) {
            prefManager = new PrefManager(context);
        }
        return prefManager.getString(prefKey);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void addPoints(Context context, int points, int type) {
        if (context != null) {
            String po = Constant.getString(context, Constant.USER_POINTS);
            if (po.equals("")) {
                po = "0";
            }
            if (type == 1) {
                Constant.setString(context, Constant.USER_POINTS, po);
                Intent serviceIntent = new Intent(context, PointsService.class);
                serviceIntent.putExtra("points", Constant.getString(context, Constant.USER_POINTS));
                context.startService(serviceIntent);
            } else {
                int current_Points = Integer.parseInt(po);
                String total_points = String.valueOf(current_Points + points);
                Constant.setString(context, Constant.USER_POINTS, total_points);
                Intent serviceIntent = new Intent(context, PointsService.class);
                serviceIntent.putExtra("points", Constant.getString(context, Constant.USER_POINTS));
                context.startService(serviceIntent);
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showInternetErrorDialog(Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.show_points_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView imageView = dialog.findViewById(R.id.points_image);
        TextView title_text = dialog.findViewById(R.id.title_text_points);
        TextView points_text = dialog.findViewById(R.id.points);
        AppCompatButton add_btn = dialog.findViewById(R.id.add_btn);

        imageView.setImageResource(R.drawable.ic_internet);
        title_text.setText(msg);
        points_text.setVisibility(View.GONE);
        add_btn.setText(context.getResources().getString(R.string.okk));

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showBlockedDialog(final Context context, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.show_points_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView imageView = dialog.findViewById(R.id.points_image);
        TextView title_text = dialog.findViewById(R.id.title_text_points);
        TextView points_text = dialog.findViewById(R.id.points);
        AppCompatButton add_btn = dialog.findViewById(R.id.add_btn);

        imageView.setImageResource(R.drawable.ic_close);
        title_text.setText(msg);
        points_text.setVisibility(View.GONE);
        add_btn.setText(context.getResources().getString(R.string.okk));

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void referApp(Context context, String refer_code) {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(R.string.share_text) + "' " + refer_code + " '" + " Download Link = " + " https://play.google.com/store/apps/details?id=" + context.getPackageName());
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        } catch (Exception e) {
            Log.e("TAG", "referApp: " + e.getMessage());
        }
    }
}
