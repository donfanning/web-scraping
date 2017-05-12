(ns web-scraping.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [cheshire.core :refer :all]
            [web-scraping.utils :as utils]))


(def post-content
  {:area-code nil
   :phone-number nil
   :reports nil
   :content nil})

(defn create-post
  [html-post])

(def ^:dynamic *base-url* "https://s3.us-east-2.amazonaws.com/gsd-auth-callinfo/callnotes.html")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn posts []
  (html/select (fetch-url *base-url*) [:div.oos_preview]))

(defn pull-area-codes []
  (into [] (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewFooter :span :span :a]))))

(defn format-area-codes [area-codes-collection]
  (hash-map :area-codes (map utils/parse-int (map #(re-find (re-pattern "\\d+") %) area-codes-collection))))

(defn area-codes []
  (generate-string (format-area-codes (pull-area-codes))))

(defn phone-numbers []
  (generate-string (hash-map :phone-numbers (map html/text (html/select (fetch-url *base-url*) [:h4.oos_previewHeader])))))

(defn report-amounts []
  (generate-string (hash-map :report-amounts (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewSide])))))

(defn comment-contents []
  (generate-string (hash-map :comment-contents (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewBody])))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
