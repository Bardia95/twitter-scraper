(ns twitter-scraper.core
  (:require [libpython-clj.require :refer [require-python]]
            [libpython-clj.python :refer [py. py.. py.-] :as py]))

(require-python 'twint '(twint run Config))

(defn get-favorited-tweets [username limit]
  (let [c (libpython-clj.python/set-attr! twint/Config "Username" username)
        d (libpython-clj.python/set-attr! c "Limit" limit)]
    (call-attr twint/run "Favorites" d)))
