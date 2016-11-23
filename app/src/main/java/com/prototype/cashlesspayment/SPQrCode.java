package com.prototype.cashlesspayment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by
 * Name         : Ihksan Sukmawan
 * Email        : iksandecade@gmail.com
 * Company      : Meridian.Id
 * Date         : 21/11/16
 * Project      : QRcode
 */

public class SPQrCode {

    public static final String SALDO = "SALDO";

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setSALDO(int saldo, Context context) {
        getSharedPreferences(context).edit().putInt(SALDO, saldo).commit();
    }

    public static int getSaldo(Context context) {
        return getSharedPreferences(context).getInt(SALDO, 0);
    }
}
