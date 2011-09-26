; Project Euler Problem #5

(defn divides? [m n] (= 0 (rem n m)))
    
(defn div-by-range? [n min max]
  (loop [i min]
    (if (divides? i n)
      (if (= i max)
        true
        (recur (inc i)))
      false)))
    
(loop [n 1]
	 (if (div-by-range? n 1 20)
	     n
	     (recur (inc n))))