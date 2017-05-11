(defproject web-scraping "0.1.0-SNAPSHOT"
  :description "A web-scraping application for Pindrop"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [http-kit "2.1.18"]]
  :main ^:skip-aot web-scraping.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
