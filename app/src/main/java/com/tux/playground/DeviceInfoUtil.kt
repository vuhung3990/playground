package com.tux.playground

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.support.annotation.RequiresPermission
import android.telephony.*

/**
 * get android os version
 * @see Build.VERSION_CODES
 */
fun getOsVersion(): String = Build.VERSION.SDK_INT.toString()

/**
 * no connection or cannot get signal strength (-127 dBm)
 */
const val NO_SIGNAL: Int = -127

/**
 * entry class for connection
 * @param type connection type such as WIFI, MOBILE_3G...
 * @param strength signal strength in dMb
 */
data class Connection(val type: String, val strength: Int)

/**
 * get connection type such as: WIFI, MOBILE_3G...
 *
 * https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
 * @see detectMobileNetworkType
 */
@RequiresPermission(
    allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE])
@SuppressLint("MissingPermission")
fun getNetworkType(context: Context): Connection {
  val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
  val isConnected: Boolean = activeNetwork?.isConnected == true

  return if (!isConnected) Connection("NO_CONNECTION", NO_SIGNAL)
  else when (activeNetwork?.type) {
    ConnectivityManager.TYPE_WIFI -> {
      val signalStrength = getSignalStrength(context, true)
      Connection("WIFI", signalStrength)
    }
    ConnectivityManager.TYPE_MOBILE -> detectMobileNetworkType(context)
    else -> Connection("UNKNOWN", NO_SIGNAL)
  }
}

/**
 * get mobile connection type
 *
 * https://stackoverflow.com/questions/9283765/how-to-determine-if-network-type-is-2g-3g-or-4g
 */
@RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
@SuppressLint("MissingPermission")
private fun detectMobileNetworkType(context: Context): Connection {
  val mTelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
  val networkType = mTelephonyManager.networkType

  val signalStrength = getSignalStrength(context, false)
  return when (networkType) {
    TelephonyManager.NETWORK_TYPE_GPRS,
    TelephonyManager.NETWORK_TYPE_EDGE,
    TelephonyManager.NETWORK_TYPE_CDMA,
    TelephonyManager.NETWORK_TYPE_1xRTT,
    TelephonyManager.NETWORK_TYPE_IDEN -> Connection("MOBILE_2G", signalStrength)

    TelephonyManager.NETWORK_TYPE_UMTS,
    TelephonyManager.NETWORK_TYPE_EVDO_0,
    TelephonyManager.NETWORK_TYPE_EVDO_A,
      /**
      From this link https://en.wikipedia.org/wiki/Evolution-Data_Optimized ..NETWORK_TYPE_EVDO_0 & NETWORK_TYPE_EVDO_A
      EV-DO is an evolution of the CDMA2000 (IS-2000) standard that supports high data rates.

      Where CDMA2000 https://en.wikipedia.org/wiki/CDMA2000 .CDMA2000 is a family of 3G[1] mobile technology standards for sending voice,
      data, and signaling data between mobile phones and cell sites.
       */
    TelephonyManager.NETWORK_TYPE_HSDPA,
    TelephonyManager.NETWORK_TYPE_HSUPA,
    TelephonyManager.NETWORK_TYPE_HSPA,
    TelephonyManager.NETWORK_TYPE_EVDO_B,
    TelephonyManager.NETWORK_TYPE_EHRPD,
    TelephonyManager.NETWORK_TYPE_HSPAP
      //Log.d("Type", "3g");
      //For 3g HSDPA , HSPAP(HSPA+) are main  networktype which are under 3g Network
      //But from other constants also it will 3g like HSPA,HSDPA etc which are in 3g case.
      //Some cases are added after  testing(real) in device with 3g enable data
      //and speed also matters to decide 3g network type
      //https://en.wikipedia.org/wiki/4G#Data_rate_comparison
    -> Connection("MOBILE_3G", signalStrength)
    TelephonyManager.NETWORK_TYPE_LTE
      //No specification for the 4g but from wiki
      //I found(LTE (Long-Term Evolution, commonly marketed as 4G LTE))
      //https://en.wikipedia.org/wiki/LTE_(telecommunication)
    -> Connection("MOBILE_4G", signalStrength)
    else -> Connection("MOBILE_UNKNOWN", NO_SIGNAL)
  }
}

/**
 * get current battery level from 0.0 to 1.0 (full)
 */
fun getBatteryLevel(context: Context): String {
  val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
  // cause receiver is null, no need to unregister
  val batteryStatus = context.registerReceiver(null, iFilter)
  val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
  val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

  return "%.2f".format(level / scale.toFloat())
}

/**
 * get signal strength of wifi or mobile data in dBm
 */
@SuppressLint("MissingPermission")
@RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
private fun getSignalStrength(context: Context, isWifi: Boolean): Int {
  if (isWifi) {
    // wifi
    val wifiManager = context.applicationContext.getSystemService(
        Context.WIFI_SERVICE) as WifiManager
    wifiManager.connectionInfo.bssid
    return wifiManager.connectionInfo.rssi
  } else {
// mobile
//    if (ContextCompat.checkSelfPermission(this,
//            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//      ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
//          99)
//      return NO_SIGNAL
//    }
    var strength = NO_SIGNAL
    try {
      val telephonyManager = context.applicationContext.getSystemService(
          Context.TELEPHONY_SERVICE) as TelephonyManager
      val type = telephonyManager.phoneType
      if (type == TelephonyManager.PHONE_TYPE_GSM) {
        val cellinfogsm = telephonyManager.allCellInfo[0]
        if (cellinfogsm is CellInfoGsm) {
          strength = cellinfogsm.cellSignalStrength.dbm
        }
        if (cellinfogsm is CellInfoLte) {
          strength = cellinfogsm.cellSignalStrength.dbm
        }
        if (cellinfogsm is CellInfoCdma) {
          strength = cellinfogsm.cellSignalStrength.dbm
        }
        if (cellinfogsm is CellInfoWcdma) {
          strength = cellinfogsm.cellSignalStrength.dbm
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return strength
  }
}