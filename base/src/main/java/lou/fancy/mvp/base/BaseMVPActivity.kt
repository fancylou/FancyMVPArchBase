package lou.fancy.mvp.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import lou.fancy.mvp.base.ext.gone
import lou.fancy.mvp.base.ext.visible

/**
 * Created by fancylou on 10/17/17.
 */


abstract class BaseMVPActivity<P: BasePresenter<V>, in V:BaseView>: AppCompatActivity(), BaseView {

    val THIS_TAG = "BaseMVPActivity"
    abstract protected var mPresenter: P
    abstract fun layoutResId() : Int
    abstract fun afterSetContentView()
    open fun beforeSetContentView() = {}


    override fun getContext(): Context = this

    //Toolbar 标题栏
    protected var toolbar: Toolbar? = null

    val frame : FrameLayout by lazy { FrameLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        mPresenter.attachView(this as V)
        initBaseView()
        afterSetContentView()
    }

    private fun initBaseView() {
        val _contentView = layoutInflater.inflate(layoutResId(), null)
        frame.addView(_contentView)
        setContentView(frame)
    }

    fun setupToolBar(title: String = "", hasBackBtn: Boolean = true, titleInCenter: Boolean = true,
                     @ColorInt titleColor: Int = Color.WHITE, @ColorInt toolbarBackgroundColor: Int = ContextCompat.getColor(this, R.color.primary)) {
        try {
            if (toolbar == null) {
                toolbar = layoutInflater.inflate(R.layout.toolbar, null) as Toolbar
            }
            frame.addView(toolbar, ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT))
            val titleTV = toolbar?.findViewById<TextView>(R.id.title_toolbar)
            if (titleInCenter) {
                toolbar?.title = ""
                titleTV?.visible()
                titleTV?.text = title
                titleTV?.setTextColor(titleColor)
            }else {
                titleTV?.gone()
                toolbar?.title = title
                toolbar?.setTitleTextColor(titleColor)
            }
            setSupportActionBar(toolbar)
            toolbar?.setBackgroundColor(toolbarBackgroundColor)
            if (hasBackBtn) {
                toolbar?.setNavigationIcon(R.mipmap.ic_back_mtrl_white_alpha)
                toolbar?.setNavigationOnClickListener { finish() }
            }
        }catch (e: Exception) {
            Log.e(THIS_TAG, "setupToolBar exception", e)
            Toast.makeText(this, "setupToolBar has exception , please check log", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}