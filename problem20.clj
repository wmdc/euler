;Problem 20 - find the sum of digits in 100!

(def factorial
  (fn [n]
    (loop [cnt n acc 1]
       (if (zero? cnt)
            acc
          (recur (dec cnt) (* acc cnt))))))


(let [x (atom 0)]
	   (doseq [i (str (factorial 100))]
		  (reset! x (+ @x (- (int i) (int \0)))))
	   @x)