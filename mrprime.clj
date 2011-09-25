; Implementation of Miller-Rabin primality test
; from http://literateprograms.org/Miller-Rabin_primality_test_(Clojure)

(defn factorize-out [n x]
  (loop [acc n exp 0]
    (if (zero? (rem acc x))
      (recur (/ acc x) (inc exp))
      (hash-map :exponent exp :rest acc))))

(use '[clojure.contrib.math :only (expt)])
(defn expt-rem [n e m]
  (loop [r 1, b n, e e]
                (if (zero? e) r
                        (recur
                                (if (odd? e) (rem (* r b) m) r)
                                (rem (expt b 2) m)
                                (bit-shift-right e 1)))))

(defn miller-rabin? [accuracy num]
        (cond
                (even? num) (= num 2)
                (< num 2) 'false?
                :else
                (let
                        [m (factorize-out (dec num) 2)
                        d (:rest m)
                        s (:exponent m)
                        witness? (fn [r x]
                                (cond
                                        (or (= x 1)(> r (dec s))) 'false
                                        (= x (dec num)) 'true
                                        :else (recur (inc r)(rem (* x x) num))))
                        investigate? (fn [k]
                                (if (> k accuracy) 'true
                                        (let
                                                [a (+ 2 (rand-int (- num 4)))
                                                x (expt-rem a d num)]
                                                (if (or (= x 1)(= x (dec num))(witness? 1 (expt-rem x 2 num)))
                                                        (recur (inc k))
                                                        'false))))]
                        (investigate? 1))))

(def *prime-accuracy* 10)
(def isPrime? (partial miller-rabin? *prime-accuracy*))