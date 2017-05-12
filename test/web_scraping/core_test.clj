(ns web-scraping.core-test
  (:require [clojure.test :refer :all]
            [web-scraping.core :refer :all]
            [web-scraping.utils :refer :all]))

(deftest area-codes-test
  (testing "Testing if area codes are scraped from website and contain no empty values."
    (let [unformatted-area-codes (pull-area-codes)]
      (is (not (every? clojure.string/blank? unformatted-area-codes)))
      (is (every? string? unformatted-area-codes))

      (testing "Testing if area codes are formatted into numeric values correctly."
         (let [formatted-area-codes (format-area-codes unformatted-area-codes)]
           (is (every? not-nil? formatted-area-codes))
           (is (every? number? formatted-area-codes)))))))

(deftest phone-numbers-test
  (testing "Testing if phone numbers are scraped from website and contain no empty values."
    (let [phone-numbers (phone-numbers)]
      (is (not (every? clojure.string/blank? phone-numbers)))
      (is (every? string? phone-numbers)))))

(deftest report-amounts-test
  (testing "Testing if report amounts are scraped from website as numeric values and contain no empty values."
    (let [report-amounts (pull-report-amounts)]
      (is (every? not-empty report-amounts))
      (is (every? number? report-amounts)))))

(deftest comment-contents-test
  (testing "Testing if comment contents are scraped from website as String values and contain no empty values."
    (let [comment-contents (pull-comment-contents)]
      (is (not (every? clojure.string/blank? comment-contents)))
      (is (every? string? comment-contents)))))

(deftest full-post-test
  (testing "Testing to determine whether full posts are created with no empty values."
    (let [posts (posts)]
      (is (every? not-empty posts))
      (is (every? :area-code posts))
      (is (every? :phone-number posts))
      (is (every? :reports posts))
      (is (every? :content posts)))))

(deftest filter-post-test
  (testing "Testing to determine whether filtering works."
    (let [posts (posts)
          first-post (first posts)
          area-code (:area-code first-post)
          filtered-post (first (filter-posts area-code))]
      (is (= filtered-post first-post)))))