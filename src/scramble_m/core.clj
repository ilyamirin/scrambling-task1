(ns scramble-m.core
  (:require [clojure.set]))

(defn scramble
  "Returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false."
  [str1 str2]
  (def freq1 (frequencies str1))
  (def freq2 (frequencies str2))
  ;;TODO: Bloom filters
  (if-not (clojure.set/subset? (set str2) (set (keys freq1)))
    false
    (nil? (some false? (map #(>= (get freq1 %) (get freq2 %)) (keys freq2))))))

(scramble "abcdef" "abc")