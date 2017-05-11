(ns web-scraping.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]))

(def ^:dynamic *base-url* "https://s3.us-east-2.amazonaws.com/gsd-auth-callinfo/callnotes.html")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn get-posts []
  (map html/text (html/select (fetch-url *base-url*) [:li.oos_listItem :div])))

(defn get-dom
  []
  (html/html-snippet
    (:body @(http/get *base-url*))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
