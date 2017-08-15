package upday.droidconmvvm.schedulers

import rx.Scheduler

/**
 * Allow providing different types of [Scheduler]s.
 */
interface ISchedulerProvider {

    fun computation(): Scheduler

    fun ui(): Scheduler
}
