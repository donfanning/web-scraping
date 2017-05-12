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
  [area-code phone-number report-amounts comment-content]
  (assoc post-content :area-code area-code
                      :phone-number phone-number
                      :reports report-amounts
                      :content comment-content))

(def ^:dynamic *base-url* "https://s3.us-east-2.amazonaws.com/gsd-auth-callinfo/callnotes.html")

(defn fetch-url
  [url]
  (html/html-resource (java.net.URL. url)))

(defn pull-area-codes
  []
  (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewFooter :span :span :a])))

(defn format-area-codes
  [area-codes-collection]
  (map utils/parse-int (map #(re-find (re-pattern "\\d+") %) area-codes-collection)))

(defn area-codes
  []
  (generate-string (hash-map :area-codes (format-area-codes (pull-area-codes)))))

(defn pull-phone-numbers
  []
  (map html/text (html/select (fetch-url *base-url*) [:h4.oos_previewHeader])))

(defn phone-numbers
  []
  (generate-string (hash-map :phone-numbers (pull-phone-numbers))))

(defn pull-report-amounts
  []
  (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewSide])))

(defn report-amounts
  []
  (generate-string (hash-map :report-amounts (pull-report-amounts))))

(defn pull-comment-contents
  []
  (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewBody])))

(defn comment-contents
  []
  (generate-string (hash-map :comment-contents (pull-comment-contents))))

(defn posts
  []
  (map create-post (format-area-codes (pull-area-codes)) (pull-phone-numbers) (pull-report-amounts) (pull-comment-contents)))

(defn filter-posts
  [area-code]
  (filter #(= (:area-code %) area-code) (posts)))

(defn filtered-posts
  [area-code]
  (generate-string (filter-posts area-code)))