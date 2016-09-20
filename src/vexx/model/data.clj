(ns vexx.model.data
  (:require
   [vexx.model.debug :as dbg]
   )
  )


(comment "------------------------------------------------ db ---
 The db
")

(def m-db (ref 
           {:content {}
            :info "no info available"
            }))
;@db

(defn db
  []
  m-db)





        


(comment "--------------------------------------------------------
 Main listbox selection
 Data for the main listbox: selected name and index in the listbox
 - list-selection = getter, returns :name by default
 - list-selection-set-name = setter, sets the name of selected item
 - list-selection-set-index = setter, sets the index of selected item

")

(def m-list-selection (ref {:index 0 :name nil}))

(defn list-selection-get-name
  []
  (:name @m-list-selection))

(defn list-selection-set-name
  [sel-item-name]
  (dbg/p ": sel= " sel-item-name)
  (dosync (alter m-list-selection assoc :name sel-item-name)))
;; (list-selection-set-name "test name")
;; @m-list-selection

(defn list-selection-set-index
  [sel-item-index]
  (dosync (alter m-list-selection assoc :index sel-item-index)))
;; (list-selection-set-index 666)
;; @m-list-selection



(comment "--------------------------------------------------------
 Main listbox selection's data
 Get data for the selected item
")


(defn sel-item-data
  " Return the :data of the selected item"
  []
  ;; (let [sel-name (list-selection-get-name)
  ;;       sel-data (m-db/item-data sel-name)]
  ;;   sel-data)
  )





(comment "--------------------------------------------------------
 Children listbox
 Data getters for the children listbox:
 - return children of the selected name in main listbox
")


(defn children-list
  []
  ;; (let [sel-name (list-selection-get-name)
  ;;       sel-data (m-db/item-data sel-name)]
  ;;   )
  )
  
