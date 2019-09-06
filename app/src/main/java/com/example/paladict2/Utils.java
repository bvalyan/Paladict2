package com.example.paladict2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class Utils {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static String GetMD5Hash(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String createSignature(String endpoint){
        return GetMD5Hash(Constants.PALADINS_DEV_ID + endpoint + Constants.PALADINS_AUTH_KEY + getDate());
    }

    public static String createSessionURL(){
        String signature = createSignature("createsession");
        return  Constants.PALADINS_API_URI + "createsessionjson/" + Constants.PALADINS_DEV_ID + "/" + signature + "/" + getDate();
    }

    public static String getDate() {
        SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatUTC.format(Calendar.getInstance().getTime());
    }

    public static ProgressDialog getProgressDialog(Activity activity, String title, String message) {
        ProgressDialog dialog = new ProgressDialog(activity);
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            dialog.setMessage(message);
        }
        return dialog;
    }
}
