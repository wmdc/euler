; Project Euler Problem 12

(defn divides? [m n]
  (if (= 0 m)
    false
    (= 0 (rem n m))))

(defn num-divisors [n]
  (loop [i 0 total 0]
    (if (= i n)
      (inc total)
      (recur  (inc i) 
              (if (divides? i n)
                (inc total)
                total)))))
                
(loop [i 0 total 0]
  (if (> (num-divisors total) 500)
    total
    (recur (inc i) (+ i total))))