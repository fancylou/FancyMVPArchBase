package lou.fancy.mvp.architecture

import lou.fancy.mvp.base.BasePresenterImpl

/**
 * Created by fancylou on 10/17/17.
 */


class MainPresenter: BasePresenterImpl<MainContract.MainView>(), MainContract.Presenter {
    override fun loadData() {
        mView?.showView()
    }
}