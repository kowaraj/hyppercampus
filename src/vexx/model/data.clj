(ns vexx.model.data
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.item :as item]
   )
  )

;; (comment "----------------------------------------------------- listbox data -- ")

;; (def m-listbox-data (ref []))
;; (defn listbox-data [] m-listbox-data)
;; (defn listbox-data-set [v-of-dicts]
;;   (dosync (ref-set m-listbox-data v-of-dicts)))
;; ;;(listbox-data-set [{:name "a" :index 1} {:name "b" :index 2}])




(comment "-------------------------------------------------------- db -- ")

(def m-db (ref (item/make-new-item "root")))

(defn db-set
  [data-dict]
  (dosync (ref-set m-db data-dict)))


(defn db [] m-db)
;@(db)
;(keys (:nodes (:root @(db))))
;(:content (:data (:root-667 (:nodes (:root @(db))))))
;(:content (:data (:333 (:nodes (:root @(db))))))


