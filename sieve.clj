;sieve implementation from http://paste.lisp.org/display/69952

;switched java math to clojure-contrib for bignum support
;and removed int type-specific calls

;TODO get rid of int-array a
(use 'clojure.contrib.math)

(defn sieve [n]
  "Returns a list of all primes from 2 to n"
  (let [root (round (floor (sqrt n)))]
    (loop [i 3
           a (int-array n)
           result (list 2)]
      (if (>= i n)
        (reverse result)
        (recur (+ i 2)
               (if (< i root)
                 (loop [arr a
                        inc (+ i i)
                        j (* i i)]
                   (if (>= j n)
                     arr
                     (recur (do (aset arr j (int 1)) arr)
                            inc
                            (+ j inc))))
                 a)
               (if (zero? (aget a i))
                 (conj result i)
                 result))))))