; Problem 16

(use 'clojure.contrib.math)

(defn sum-digits [n]
  (reduce #(+ %1 (- (int %2) (int \0))) 0 (str n)))

(sum-digits (expt 2 1000))