 (ns web-scraping.utils)

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))