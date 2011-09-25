; Project Euler, Problem 19:
; How many Sundays fell on the first of the month 
; during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

(def months-in-year '(:jan :feb :mar :apr :may :jun :jul :aug :sep :oct :nov :dec))
(def days-of-week [:mon :tue :wed :thu :fri :sat :sun])
(def days-per-week 7)

(defn divisible? [num den] (= 0 (rem num den)))

(def days-in-month {:jan 31 :feb 28 :mar 31 
                    :apr 30 :may 31 :jun 30
                    :jul 31 :aug 31 :sep 30 
                    :oct 31 :nov 30 :dec 31}
					
(defn leap-year? [year]
  (cond (divisible? year 400) true
        (divisible? year 100) false
        (divisible? year 4) true
        :else false))

(let [weekday (atom 0) total (atom 0)]
  (doseq [year (range 1901 2001)]
    (doseq [month months-in-year]
      (doseq [day (range 1 (if  (and (= month :feb) (leap-year? year))
                                (+ 2 (days-in-month month)) 
                                (+ 1 (days-in-month month))))]
        (if (and (= day 1) (= @weekday 0)) 
          (swap! total inc))
        ;(println (days-of-week @weekday) month day year)
        (swap! weekday #(mod (inc %) days-per-week)))))
  @total)