package lou.fancy.mvp.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by fancylou on 2017-10-17.
 * Email: FancyLou@outlook.com
 */

abstract class BaseMVPFragment<P : BasePresenter<V>, in V : BaseView> : Fragment(), BaseView {

    abstract protected var mPresenter: P
    abstract fun layoutResId(): Int
    abstract fun afterViewCreated()
    open fun initInCreated() = {}

    override fun getContext(): Context = activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
        initInCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutResId(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViewCreated()
    }
}