(ns scramble-m.core-test
  (:require [clojure.test :refer :all]
            [scramble-m.core :refer :all]))

(deftest scramble-function-test
  (testing "Some of the simplest scrambling examples don't work."
    (is (true? (scramble "algorithm" "logarithm")))
    (is (true? (scramble "algorithm" "loga")))
    (is (false? (scramble "algorithm" "logariphm")))
    (is (true? (scramble "algorithmeee" "logarithmee")))
    (is (false? (scramble "algorithme" "logarithmee")))
    (is (false? (scramble "abcdef" "abcc")))
    (is (false? (scramble "algorithme" "logarithmee")))
    (is (false? (scramble "test" "logariphmee")))))

