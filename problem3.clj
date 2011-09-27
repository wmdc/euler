(def bign 600851475143)

(defn divides? [m n] (= 0 (rem n m)))

;(doseq [i (range bign 1 -1)]
;	(if (and (divides? i bign) (isPrime? i))
;	    (println i)))

(defn lazy-primes []
  (letfn [(enqueue [sieve n step]
            (let [m (+ n step)]
              (if (sieve m)
                (recur sieve m step)
                (assoc sieve m step))))
          (next-sieve [sieve candidate]
            (if-let [step (sieve candidate)]
              (-> sieve
                (dissoc candidate)
                (enqueue candidate step))
              (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
            (if (sieve candidate)
              (recur (next-sieve sieve candidate) (+ candidate 2))
              (cons candidate
                (lazy-seq (next-primes (next-sieve sieve candidate)
                            (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))
    
(loop [i (lazy-primes)]
  (if (> (first i) bign)
    :done
    (do (if (divides? (first i) bign)
          (println (first i)))
        (recur (rest lazy-primes)))))