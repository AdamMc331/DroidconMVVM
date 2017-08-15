package upday.droidconmvvm.datamodel

import java.util.Arrays

import rx.Observable
import upday.droidconmvvm.model.Language

import upday.droidconmvvm.model.Language.LanguageCode
import java.util.concurrent.Callable

/**
 * Data model for languages.
 */
class DataModel : IDataModel {

    override val supportedLanguages: Observable<List<Language>>
        get() = Observable.fromCallable<List<Language>>({ this.languages })

    private val languages: List<Language>
        get() = Arrays
                .asList(Language("English", LanguageCode.EN),
                        Language("German", LanguageCode.DE),
                        Language("Slovakian", LanguageCode.HR))

    override fun getGreetingByLanguageCode(code: LanguageCode): Observable<String> {
        when (code) {
            Language.LanguageCode.DE -> return Observable.just("Guten Tag!")
            Language.LanguageCode.EN -> return Observable.just("Hello!")
            Language.LanguageCode.HR -> return Observable.just("Zdravo!")
            else -> return Observable.empty<String>()
        }
    }
}
