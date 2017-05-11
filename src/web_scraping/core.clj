(ns web-scraping.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]))

(def post-content
  {:area-code nil
   :phone-number nil
   :reports nil
   :content nil})


(def ^:dynamic *base-url* "https://s3.us-east-2.amazonaws.com/gsd-auth-callinfo/callnotes.html")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn posts []
  (html/select (fetch-url *base-url*) [:div.oos_preview]))

(defn area-code []
  (into [] (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewFooter :span :span]))))

(defn phone-number []
  (into [] (map html/text (html/select (fetch-url *base-url*) [:h4.oos_previewHeader]))))

(defn amount-of-reports []
  (into [] (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewSide]))))

(defn comment-contents []
  (into [] (map html/text (html/select (fetch-url *base-url*) [:div.oos_previewBody]))))


(defn get-dom
  []
  (html/html-snippet
    (:body @(http/get *base-url*))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
