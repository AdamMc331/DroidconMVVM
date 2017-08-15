package upday.droidconmvvm.datamodel

import rx.Observable
import upday.droidconmvvm.model.Language

import upday.droidconmvvm.model.Language.LanguageCode

/**
 * Interface that handles our how data model should behave.
 */
interface IDataModel {

    /**
     * Returns a list of supported languages.
     */
    val supportedLanguages: Observable<List<Language>>

    /**
     * Returns a greeting for a given language.
     */
    fun getGreetingByLanguageCode(code: LanguageCode): Observable<String>
}
