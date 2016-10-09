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




;; ------------------------------------------------- part II ---


(defn update-listbox-data
  "
  Takes the root-node and updates the listbox data
  "
  [the-cause]
  (let [root-node (db-hl/get-root-node)]    
    (vol/listbox-data-set (vol/listbox-data-make root-node the-cause))))


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
    (vol/tags-data-set root-node)

    ;(dbg/p root-node)  
    (vol/debug-data-set root-node)
    ))


  

;; ------------------------------------------------- part III ---

(defn callback-db-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-db-changed !")
  (update-listbox-data :db-changed)
  (update-selected-node-view) ;(update-kids-data)
  )

(defn callback-path-changed
  [_ _ _ _]
  ;;;(vol/listbox-data-set (vol/get-current-listbox-data)))
  (println "! callback-path-changed !")
  (update-listbox-data :path-changed)
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

