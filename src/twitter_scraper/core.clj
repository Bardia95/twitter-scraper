(ns twitter-scraper.core
  (:require [libpython-clj.require :refer [require-python]]
            [libpython-clj.python :refer [py. py.. py.-
                                          set-attr! call-attr] :as py]))

;; Call to initialize python
(py/initialize!)
;; Require python modules
(require-python 'twint
                '(twint run Config))
;; Get tweets from specified user
(defn tweets->json [username limit output]
  (let [q (-> twint/Config
              (set-attr! "Username" username)
              (set-attr! "Limit" limit)
              (set-attr! "Output" output)
              (set-attr! "Store_json" true))]
    (call-attr twint/run "Search" q)))
