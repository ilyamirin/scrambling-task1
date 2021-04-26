(ns scramble-m.core-test
  (:require [clojure.test :refer :all]
            [scramble-m.core :refer :all]))

(deftest scramble-function-basic-test
  (testing "Some of the simplest scrambling examples don't work."
    (is (true? (scramble "algorithm" "logarithm")))
    (is (true? (scramble "algorithm" "loga")))
    (is (false? (scramble "algorithm" "logariphm")))
    (is (true? (scramble "algorithmeee" "logarithmee")))
    (is (false? (scramble "algorithme" "logarithmee")))
    (is (false? (scramble "abcdef" "abcc")))
    (is (false? (scramble "algorithme" "logarithmee")))
    (is (false? (scramble "test" "logariphmee")))))

;;TODO: add errors and anything tests

(deftest scramble-function-speed-test
  (testing "One iteration must spend less time then 1 millisecond."
    (def spd (re-find #"[0-9]+\.[0-9]+" (with-out-str (time (scramble "algorithm" "logarithm")))))
  (is (<= (Double. spd) 0.1))))
