(ns scramble-m.core
  (:use ring.middleware.params
        ring.util.response
        ring.adapter.jetty
        [clojure.data.json :as json]
        clojure.set))

(defn scramble
  "Returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false."
  [str1 str2]
  (def freq1 (frequencies (str str1)))
  (def freq2 (frequencies (str str2)))
  (if-not (and (not (empty? freq2)) (clojure.set/subset? (set str2) (set (keys freq1))))
    false
    (nil? (some false? (map #(>= (get freq1 %) (get freq2 %)) (keys freq2))))))

(defn handler [request]
  (def str1 (second (re-find #"str1=([^&#]*)" (:query-string request))))
  (def str2 (second (re-find #"str2=([^&#]*)" (:query-string request))))
  (if-not (or (nil? str1) (nil? str2))
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (str (json/write-str {:scrambled? (scramble str1 str2) :str1 str1 :str2 str2}))}
    {:status  400
     :headers {"Content-Type" "text/html"}
     :body    "Incorrect query string. Please use something like ?str1=algorithm&str2=logarithm"})
  )

;;(run-jetty handler {:port  3000 :join? false})