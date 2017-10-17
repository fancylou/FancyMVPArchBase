package lou.fancy.mvp.base

/**
 * Created by fancylou on 10/17/17.
 */

open class BasePresenterImpl<V: BaseView> : BasePresenter<V> {

    protected var mView: V? = null
    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}