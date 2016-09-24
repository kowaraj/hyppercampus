(ns vexx.controller.json
  (:require
   [clojure.data.json :as json]
   [vexx.model.data :as m-data]
   ))

(def db-filename "data/db.json")
;(slurp db-filename)

(defn load-from-file []
  "
  Items in file are strings, when loaded to db transformed to keywords
  "
  (let [json-str (slurp db-filename)
        loaded-db (json/read-str json-str :key-fn keyword)
        ]
    (m-data/db-set loaded-db)));;(dosync (ref-set m-data/db loaded-db))))
;(load-from-file)


;; (defn- backup-old-file
;;   []
;;   (let [data-json (json/write-str @m-data/db)
;;         date-time (.format (java.text.SimpleDateFormat. "yyyyMMdd_HHmm") (java.util.Date.))
;;         filename (clojure.string/join "_" [db-filename date-time])
;;         ]
;;     (println "fn = " filename)
;;     (spit filename data-json)))


(defn save-to-file 
  "
  Items in db are keywords, when dumped to file transformed to strings
  "
  []
  (let [data-json (json/write-str @(m-data/db))]
    ;;;(backup-old-file)
    (spit db-filename data-json)))
;(save-to-file)



;(ch/parse-string (ch/generate-string x))
;; (defn ch-save-to-file []
;;   (let [data-json (ch/generate-string  @vm/db  {:pretty true})]
;;     (spit db-filename data-json)))
;; ;(ch-save-to-file)


