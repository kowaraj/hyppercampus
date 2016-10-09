;; to modify (update) db from the widgets
(ns vexx.controller.interface-2
  (:require
   [seesaw.core :as ss]

   [vexx.model.debug :as dbg]
   [vexx.model.utils :as utils]
   [vexx.model.item :as item]
   [vexx.model.data :as data]
   [vexx.model.db :as db]
   [vexx.model.db-hl :as db-hl]
   [vexx.model.path :as path]
   [vexx.model.path-hl :as path-hl]
   [vexx.model.vol :as vol]
   )
  )


(defn get-new-name
  [e]
  (let [t (ss/text :text "") 
        result (javax.swing.JOptionPane/showInputDialog
                (ss/border-panel :size [100 :by 100])
                t
                "Input"
                javax.swing.JOptionPane/QUESTION_MESSAGE
                nil
                nil;(to-array [:text :pic :other])
                "Titan"
                )
        ]
    (let [a (ss/text t) ]
      (if result
        a ))))

(defn update-content-data
  [tf-text]
  (db/set-db-node-attr (data/db)
                       (path-hl/get-selected-node-path)
                       :data
                       {:name "some-def-name" :content tf-text}))

(defn jump-to-kids-of-sel-node
  []
  (vol/stack-go-level-down)
  (path-hl/go-level-down-selnode))

(defn jump-to-parent-of-sel-node
  []
  (vol/stack-go-level-up)
  (path/go-level-up))

(defn rename-sel-node
  [new-name]
  (dbg/p new-name)
  (db-hl/rename-selected-node new-name))

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

