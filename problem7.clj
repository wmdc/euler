;Project Euler Problem 7
;Fun fact: without recur this causes a stack overflow!
(defn n-prime [x n target]
	    (if (and (= n target) (isPrime? x))
		     x
		(recur (inc x)
			 (if (isPrime? x)
			     (inc n)
			     n)
			 target)))
       
(n-prime 1 0 10001)