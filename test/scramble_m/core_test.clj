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

(deftest scramble-function-errors-test
  (testing "Scramble function must process empty parameters."
    (is (false? (scramble nil "logariphmee")))
    (is (false? (scramble "test" nil)))
    (is (false? (scramble "" "logariphmee")))))

(deftest scramble-function-speed-test
  (testing "One iteration must spend less time than 2 millisecond."
    (let [spd (re-find #"[0-9]+\.[0-9]+" (with-out-str (time (scramble "algorithm" "logarithm"))))]
      (is (<= (Double. spd) 0.2)))))

(deftest scramble-handler-basic-test
  (testing "Some of the simplest scrambling examples don't work throughout of WEB API."
    (let [response (handler {:query-string "?str1=algorithm&str2=logarithm"})]
      (is (= 200 (:status response)))
      (is (= "{\"scrambled?\":true,\"str1\":\"algorithm\",\"str2\":\"logarithm\"}" (:body response))))

    (let [response (handler {:query-string "?str1=algorithm&str2=logarithme"})]
      (is (= 200 (:status response)))
      (is (= "\"scrambled?\":false" (re-find #"\"scrambled\?\":false" (:body response)))))

    (let [response (handler {:query-string "?x=algorithm&y=logarithme"})]
      (is (= 400 (:status response))))
    (let [response (handler {:query-string "?x="})]
      (is (= 400 (:status response))))
    (let [response (handler {:query-string ""})]
      (is (= 400 (:status response))))
    ))