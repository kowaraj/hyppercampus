(ns vexx.model.data
  (:require
   [vexx.model.debug :as dbg]
   )
  )

;; (comment "----------------------------------------------------- listbox data -- ")

;; (def m-listbox-data (ref []))
;; (defn listbox-data [] m-listbox-data)
;; (defn listbox-data-set [v-of-dicts]
;;   (dosync (ref-set m-listbox-data v-of-dicts)))
;; ;;(listbox-data-set [{:name "a" :index 1} {:name "b" :index 2}])




(comment "-------------------------------------------------------- db -- ")

(def m-db (ref 
           {:root {:nodes {}
                   }
            :info "no info available"
            }))

(defn db [] m-db)
;@(db)
;(keys (:nodes (:root @(db))))



