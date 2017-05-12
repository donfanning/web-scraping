(ns web-scraping.handler
  (:use compojure.core)
  (:use cheshire.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [web-scraping.core :as core]))

(defroutes app-routes
           (GET "/" [] "Hello World")
           (GET "/posts" [] (core/posts))
           (GET "/posts/:area-code" [area-code] (core/filtered-posts area-code))
           (GET "/area-codes" [] (core/area-codes))
           (GET "/phone-numbers" [] (core/phone-numbers))
           (GET "/report-amounts" [] (core/report-amounts))
           (GET "/comment-contents" [] (core/comment-contents))
           (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

