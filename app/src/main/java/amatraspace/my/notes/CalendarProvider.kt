package amatraspace.my.notes

import java.util.*
import kotlin.math.floor

class CalendarProvider() {
    private val calendar = GregorianCalendar.getInstance()
    private val currentYear = calendar.get(GregorianCalendar.YEAR)

    var monthNum = calendar.get(GregorianCalendar.MONTH)
        private set
    var year = currentYear
        private set
    var day = calendar.get(GregorianCalendar.DAY_OF_MONTH)
        private set
    var daysAmount = GregorianCalendar(year, monthNum, 1)
        .getActualMaximum(Calendar.DAY_OF_MONTH)
        private set
    private val dayOfWeek = GregorianCalendar(year, monthNum, 1)
        .get(GregorianCalendar.DAY_OF_WEEK)
    var firstDayOfFirstWeek = if (dayOfWeek == 1) 7 else dayOfWeek - 1
        private set


    constructor(month_par: Int): this() {
        var swipedYear = year

        when (month_par) {
            in Int.MIN_VALUE..-13 -> {
                val addition = if (month_par % 12 == 0) 0 else 1
                val years = floor(month_par / -12 + 0.999).toInt() + addition
                swipedYear = currentYear - years
                monthNum = 12 - ((month_par % 12) * -1)
                if (monthNum == 12) monthNum = 0
            }
            in -12..-1 -> {
                val years = 1
                swipedYear = currentYear - years
                monthNum = 12 - month_par * -1
            }
            in 0..11 -> {
                monthNum = month_par
                swipedYear = currentYear
            }
            in 12..Int.MAX_VALUE -> {
                val years = month_par / 12
                monthNum = month_par % 12
                swipedYear = currentYear + years
            }
        }

        calendar.set(swipedYear, monthNum, 1)
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH)
        year = swipedYear
        val daysCalendar = GregorianCalendar(swipedYear, monthNum, 1)
        daysAmount = daysCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val dayOfWeek = GregorianCalendar(year, monthNum, 1)
            .get(GregorianCalendar.DAY_OF_WEEK)
        firstDayOfFirstWeek = if (dayOfWeek == 1) 7 else dayOfWeek - 1
    }
}