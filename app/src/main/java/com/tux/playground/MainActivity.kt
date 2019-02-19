package com.tux.playground

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.SingleSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  var compositeGetWebTitle: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

//    GetWebTitle().execute("http://wakulive.xgg.jp/profiles/index/8a5811bb")

    wv.loadUrl("https://wakulive.biz/howto/manners")
    wv.settings.javaScriptEnabled = true
    wv.webViewClient = object : WebViewClient() {
      override fun onPageFinished(view: WebView?, url: String?) {
        compositeGetWebTitle = view.getWebTitle()
            .subscribe { value, _ ->
              Log.d("aaaaaaa", value)
            }
        super.onPageFinished(view, url)
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    compositeGetWebTitle?.dispose()
  }

  private fun WebView?.getWebTitle(): SingleSubject<String> {
    val source = SingleSubject.create<String>()
    this?.evaluateJavascript("javascript:document.getElementsByClassName('wv_title')[0].innerHTML") { wvTitle ->
      val value = wvTitle.replace("\"", "")
      if (value.isBlank()) {
        this.evaluateJavascript("javascript:document.getElementsByTagName('title')[0].innerHTML") { title ->
          val title1 = title.replace("\"", "")
          source.onSuccess(title1)
        }
      } else {
        source.onSuccess(value)
      }
    }
    return source
  }
}
