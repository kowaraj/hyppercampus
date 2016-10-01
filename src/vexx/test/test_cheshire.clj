(ns vexx.test.test-cheshire
  (:require
   [seesaw.core :as ss]
   [cheshire.core :as json-ch]

   ))

(comment 
(def sc (json-ch/generate-string {:foo "bar" :baz 5}))
(json-ch/parse-string sc     )
(json-ch/parse-string sc true)

)
