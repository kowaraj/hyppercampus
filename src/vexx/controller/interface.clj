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
  []
  (let [node (db/get-db-node (data/db) @(path/current-path))]
    (vol/listbox-data-set (vol/listbox-data-make node))))


(defn update-kids-data
  []
  (dbg/p)
  (let [node (db/get-db-node-kids (data/db)
                                  @(path/current-path)
                                  (vol/list-selection-get-name))]
    (vol/kids-data-set (vol/kids-data-make node))))
;(db/get-db-node-kids (data/db) @(path/current-path) (vol/list-selection-get-name))
;(vol/kids-data-make {:2-1 {:nodes {}, :path nil, :link-to {}, :link-from {}}, :2-2 {:nodes {}, :path nil, :link-to {}, :link-from {}}})
;(vol/list-selection-get-name)
;(update-kids-data)

;;(map hash-map (range) [1 2 3])
;;(println (vol/listbox-data-make node))))
;;(db/get-db-node (data/db) @(path/current-path))
;;(keys (db/get-db-node (data/db) @(path/current-path)))
;;(update-listbox-data)
;;(:nodes (:root @(data/db)))
;(db/add-db-node (data/db) [:root] "1")
;(db/add-db-node (data/db) [:root] "2")
;(db/add-db-node (data/db) [:root :2] "2-1")
;(db/add-db-node (data/db) [:root :2] "2-2")
;;(update-listbox-data)
;;(update-listbox-data)
;;@(data/db)
;;@(path/current-path)
;;(path/go-level-down :2)
;;(update-listbox-data)
;;(path/go-level-up)
;; @(vol/listbox-data)

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



(defn add-watchers
  []

  (add-watch (data/db)
             nil
             callback-db-changed)

  (add-watch (path/current-path)
             nil
             callback-path-changed))
;(add-watchers)





