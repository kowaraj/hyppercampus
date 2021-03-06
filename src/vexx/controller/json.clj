(ns vexx.controller.json
  (:require
   ;;[clojure.data.json :as json]
   [cheshire.core :as json-ch]
   [vexx.model.data :as m-data]
   ))

(def db-filename "data/db.json")
;(slurp db-filename)
  
(defn load-from-file 
  "
  Items in file are strings, when loaded to db transformed to keywords
  "
  ([f-name]
   (let [json-str (slurp f-name)
         ;;loaded-db (json/read-str json-str :key-fn keyword)
         loaded-db (json-ch/parse-string json-str true)
         ]
     (m-data/db-set loaded-db)))
  ([]
   (load-from-file db-filename))
  )
;;(dosync (ref-set m-data/db loaded-db))))
;(load-from-file "xxx")


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
  ([f-path]
   (let [;;data-json (json/write-str @(m-data/db))]
         data-json (json-ch/generate-string @(m-data/db) {:pretty true})]
    ;;;(backup-old-file)
     (spit f-path data-json)))
  ([]
   (save-to-file db-filename)))
;(save-to-file "xxx")



;(ch/parse-string (ch/generate-string x))
;; (defn ch-save-to-file []
;;   (let [data-json (ch/generate-string  @vm/db  {:pretty true})]
;;     (spit db-filename data-json)))
;; ;(ch-save-to-file)


