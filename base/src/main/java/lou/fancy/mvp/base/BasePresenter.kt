package lou.fancy.mvp.base

/**
 * Created by fancylou on 10/17/17.
 */

interface BasePresenter<in V: BaseView> {
    fun attachView(view: V)
    fun detachView()
}