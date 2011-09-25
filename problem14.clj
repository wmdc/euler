;Problem 14
;Note -- obviously you could do this way faster by reusing sequence counts once you got to a
;        number beyond which the sequence has already been evaluated.

(defn next-seq [n]
  (if (odd? n)
        (+ 1 (* 3 n))
        (/ n 2)))

(let [max (atom 0) n (atom 0)]
	   (doseq [i (range 1 1000000)]
		  (let [cur (count (take-while #(> % 1) (iterate next-seq i)))]
		       (if (> cur @max)
			   (do (reset! max cur)
			       (reset! n i)
             (println "new highest" @n)))))
	   @n)