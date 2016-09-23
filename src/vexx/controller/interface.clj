(ns vexx.controller.interface
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.utils :as utils]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]
   [vexx.model.path :as path]
   [vexx.model.vol :as vol]

   ))

(defn get-item
  "Get item  - from db if found, else default item"
  [item-name]
  (dbg/p item-name)
  (db/get-db-node (data/db) (path/go-level-down (keyword item-name))))


(defn add-item
  "Add item to db"
  [item-name]
  (dbg/p item-name)
  (db/add-db-node (data/db) @(path/current-path) item-name))



(defn update-listbox-data
  "
  Takes the root-node and updates the listbox data
  "
  []
  (let [root-node (db/get-db-node (data/db) @(path/current-path))]
    (vol/listbox-data-set (vol/listbox-data-make root-node))))


(defn update-kids-data
  "
  Reads the selected node and updates the rest of view
  "
  []
  (dbg/p)
  (let [node (db-hl/get-node)]
    (vol/kids-data-set (vol/kids-data-make node))))


(defn update-tabs
  []
  )
(defn update-content
  []
  (dbg/p)
  (let [node (db/get-db-node-kids (data/db)
                                  @(path/current-path)
                                  (vol/list-selection-get-name))]
    (vol/kids-data-set (vol/kids-data-make node))))
  )
(defn callback-db-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-db-changed !")
  (update-listbox-data)
  (update-kids-data)
  )


(defn callback-path-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-path-changed !")
  (update-listbox-data)
  (update-kids-data)
  )

(defn callback-list-selection-changed
  "
  Update the ref to data structures.
  The view will be updated by those data structure watchers
  "
  [_ _ _ _]
  ;;(println "! callback-list-selection-changed")
  (update-kids-data) 
  (update-tags)
  (update-content)
  )
  

(defn add-watchers
  []

  (add-watch (data/db)
             nil
             callback-db-changed)

  (add-watch (path/current-path)
             nil
             callback-path-changed)

  (add-watch (vol/list-selection)
             nil
             callback-list-selection-changed)
  )
;(add-watchers)





