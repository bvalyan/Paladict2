package com.example.paladict2.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

import com.example.paladict2.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class JavaUtils {

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
