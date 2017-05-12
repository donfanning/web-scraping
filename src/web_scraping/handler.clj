(ns web-scraping.handler
  (:use compojure.core)
  (:use cheshire.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [web-scraping.core]))

(defroutes app-routes
           (GET "/" [] "Hello World")
           (GET "/posts" [] "Posts")
           (GET "/posts/:area-code" [area-code] area-code)
           (GET "/area-code" [] "Area Codes")
           (GET "/phone-number" [] "Phone Numbers")
           (GET "/amount-comments" [] "Amount of Comments")
           (GET "/comment-content" [] "Comment Content")
           (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

