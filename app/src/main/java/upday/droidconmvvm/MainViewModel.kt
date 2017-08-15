package upday.droidconmvvm

import rx.Observable
import rx.subjects.BehaviorSubject
import upday.droidconmvvm.datamodel.IDataModel
import upday.droidconmvvm.model.Language
import upday.droidconmvvm.schedulers.ISchedulerProvider

/**
 * View model for the main activity.
 */
class MainViewModel(private val mDataModel: IDataModel,
                    private val mSchedulerProvider: ISchedulerProvider) {

    private val mSelectedLanguage = BehaviorSubject.create<Language>()

    val greeting: Observable<String>
        get() = mSelectedLanguage
                .observeOn(mSchedulerProvider.computation())
                .map<Language.LanguageCode>({ it.code })
                .flatMap<String>({ mDataModel.getGreetingByLanguageCode(it) })

    val supportedLanguages: Observable<List<Language>>
        get() = mDataModel.supportedLanguages

    fun languageSelected(language: Language?) {
        mSelectedLanguage.onNext(language)
    }

}
