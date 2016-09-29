(ns vexx.controller.interface
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.utils :as utils]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]
   [vexx.model.db-hl :as db-hl]
   [vexx.model.path :as path]
   [vexx.model.path-hl :as path-hl]
   [vexx.model.vol :as vol]

   ))

;;;==================================
;;; obsolete, removed: Part I : read/write db
;;; replaced by db-hl, db/get-node...
;;;
;;;   (defn get-item...
;;;   (defn add-item...
;;;
;; Part II : update widget's (vol) data structures
;;
;;   (update-listbox-data) -> list-data

;;   --- the following 3 are replaced by: update-selected-node-view
;;   (update-kids-data)    -> kids-data
;;   (update-tags)         -> tags-data
;;   (update-content)      -> content-data
;;    ...
;;  - when called from watchers/callbacks of persistent data str.
;;
;; Part III : the callbacks
;;
;;   (add-watch (data/db) ...
;;   (add-watch (path/current-path)...
;;   (add-watch (vol/list-selection)...
;;
;; Part IV ---
;;   - to modify (update) db from the widgets



;; ;; ------------------------------------------------- part I ---
;; (defn get-item
;;   "Get item  - from db if found, else default item"
;;   [item-name]
;;   (dbg/p item-name)
;;   (db/get-db-node (data/db) (path/go-level-down (keyword item-name))))

;; (defn add-item
;;   "Add item to db"
;;   [item-name]
;;   (dbg/p item-name)
;;   (db/add-db-node (data/db) @(path/current-path) item-name))



;; ------------------------------------------------- part II ---


(defn update-listbox-data
  "
  Takes the root-node and updates the listbox data
  "
  []
;;;  (let [root-node (db/get-db-node-nodes (data/db) @(path/current-path))]
  (let [root-node (db-hl/get-root-node)]
    (vol/listbox-data-set (vol/listbox-data-make root-node))))


;; ;replaced by: update-selected-node-view
;; (defn update-kids-data
;;   "
;;   Read selected node and update the rest of view
;;   "
;;   []
;;   ;(dbg/p) 
;;   (let [node (db-hl/get-node-nodes)] ;;TODO: rename get-node-nodes - it get a node, not many nodes
;;     (vol/kids-data-set (vol/kids-data-make node))))


;; ;replaced by: update-selected-node-view
;; (defn update-tags
;;   []
;;   (let [node (db-hl/get-node)]
;;     (vol/content-data-set node)))

 
;; ;replaced by: update-selected-node-view
;; (defn update-content
;;   []
;;   ;(dbg/p)
;;   (let [node (db-hl/get-node)]
;;     (vol/content-data-set node))) 

(defn update-selected-node-view
  []
  "
  Read selected node and update the rest of view
  "
  []
  ;(dbg/p) 
  (let [root-node (db-hl/get-node)
        node (db-hl/get-node-nodes)] ;;TODO: rename get-node-nodes - it gets a node, not many nodes
    (vol/kids-data-set (vol/kids-data-make node))
    (vol/content-data-set root-node)
    (vol/tags-data-set root-node)))
  

;; ------------------------------------------------- part III ---

(defn callback-db-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-db-changed !")
  (update-listbox-data)
  (update-selected-node-view) ;(update-kids-data)
  )

(defn callback-path-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-path-changed !")
  (update-listbox-data)
  (update-selected-node-view) ;(update-kids-data)
  )

(defn callback-list-selection-changed
  "
  Update the ref to data structures.
  The view will be updated by those data structure watchers
  "
  [_ _ _ _]
  (println "! callback-list-selection-changed")
  (update-selected-node-view)
  ;; (update-kids-data) 
  ;; (update-content)
  ;; (update-tags)
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



;; ------------------------------------------------- part IV ---

;; to modify (update) db from the widgets

(defn update-content-data
  [tf-text]
  ;(dbg/p tf-text)
  ;; pre: some keys must be present
  (db/set-db-node-attr (data/db)
;                       (path-hl/get-node-path (vol/list-selection-get-name)
                       (path-hl/get-selected-node-path)
                       :data
                       {:name "some-def-name" :content tf-text}))

(defn jump-to-kids-of-sel-node
  []
  (path-hl/go-level-down-selnode))

(defn jump-to-parent-of-sel-node
  []
  (path/go-level-up))


(defn add-kid-to-selected-node
  [kid-dict]
;; pre: some keys must be present
  (dbg/p kid-dict)
  (let [n (:name kid-dict)
        t (:type kid-dict)]
    (db-hl/add-node n)))
;(db-hl/add-node "xxx")

  
(defn delete-selected-node
  []
  (dbg/p)
  (db-hl/del-selected-node)
  )

(defn update-tags-data
  [tags-str]
  (dbg/p)
  (db/set-db-node-attr (data/db)
                       (path-hl/get-selected-node-path)
                       :tags
                       tags-str))

