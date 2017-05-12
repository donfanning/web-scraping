(defproject web-scraping "0.1.0-SNAPSHOT"
  :description "A web-scraping application for Pindrop"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [http-kit "2.1.18"]
                 [compojure "1.6.0"]
                 [cheshire "5.7.1"]
                 [ring/ring-json "0.4.0"]]
  :plugins [[lein-ring "0.11.0"]]
  :ring {:handler web-scraping.handler/app}
  :main ^:skip-aot web-scraping.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
