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

abstract class BaseMVPFragmentInViewPager<P : BasePresenter<V>, in V : BaseView> : Fragment(), BaseView {

    abstract protected var mPresenter: P
    abstract fun layoutResId(): Int
    abstract fun lazyLoad()
    abstract fun afterViewCreated()
    open fun initInCreated() = {}

    override fun getContext(): Context = activity



    /**
     * fragment 是否已经建好UI
     */
    protected var isViewInit = false
    /** Fragment当前状态是否可见 */
    protected var isViewVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //一定要调用，否则无法将菜单加入ActionItem
        setHasOptionsMenu(true)
        mPresenter.attachView(this as V)
        initInCreated()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutResId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInit = true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewInit = false
    }

    override fun onResume() {
        super.onResume()
        // 判断当前fragment是否显示
        if (isViewVisible) {
            fetchData()
        }
    }



    //对用户是否可见，在onCreateView之前调用
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isViewVisible = isVisibleToUser
        fetchData()
    }



    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }


    private fun fetchData() {
        if (isViewInit && isViewVisible) {
            lazyLoad()
        }
    }


}