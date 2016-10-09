(ns vexx.model.db-ll
  (:require
   [vexx.model.data :as data]
   [vexx.model.item :as item]
   [vexx.model.path :as path]
   [vexx.model.debug :as dbg]
   ))

;;(defn is-path-valid [p] (not-any? #(= nil %) p))

(defn get-db-node-fn
  [db path]
  (get-in @db path))

;; (defn get-db-node
;;   [db path]
;;   (get-db-node-fn db (path/nodes path)))

;;(get-db-node (data/db) @(path/current-path))

;; (defn get-db-node-kids
;;   [db root-path node-name]
;;   {:pre [(= (type node-name) java.lang.String)]}
;;   (let [node-path (path/get-level-down root-path node-name)]
;;     (get-db-node-fn db (path/nodes node-path))))


;; (defn add-db-node-fn
;;   [db path node-name]
;;   {:pre [(= (type {}) clojure.lang.PersistentArrayMap)
;;          (= (type node-name) java.lang.String)]}
;;   ;;;(if (get-db-node-fn db (path/nodes (path/get-level-down path node-name))) ; check if exists
;;   (if (get-db-node-fn db (path/nodes (path-hl/get-node-pathh node-name))) ; check if exists
;;     @db
;;     (dosync (alter db
;;                    #(update-in % path
;;                                into (item/make-new-item node-name))))))

(defn add-db-node-fn
  [db path node-name]
  {:pre [(= (type {}) clojure.lang.PersistentArrayMap)
         (= (type node-name) java.lang.String)]}
  (dosync (alter db
                 #(update-in % path
                             into (item/make-new-item node-name)))))
;; (defn add-db-node
;;   [db path node-name]
;;   (dbg/p node-name path)
;;   (add-db-node-fn db (path/nodes path) node-name))

(defn set-db-node-fn
  [db path node-name node-value]
  {:pre [(= (type {}) clojure.lang.PersistentArrayMap)
         (= (type node-name) java.lang.String)]}
  (dosync (alter db
                 #(update-in % path
                             assoc node-name node-value))))


(defn set-db-node-attr-fn
  [db path attr-name attr-val]
  ;;(println "set-db-node-attr-fn: path = " path)
  (dosync (alter db
                 #(update-in % path
                             assoc attr-name attr-val))))
;; (defn set-db-node-attr
;;   [db path attr-name attr-val]
;;   (set-db-node-attr-fn db (path/attr path) attr-name attr-val))




(defn del-db-node-fn 
  [db path node-name]
  ;;(println "del-db-node-fn: path = " path node-name)
  (dosync (alter db
                 #(update-in % path
                             dissoc (keyword node-name)))))
;; (defn del-db-node
;;   [db path node-name]
;;   (del-db-node-fn db (path/nodes path) node-name))




(defn del-db-node-attr-fn
  [db path attr-name]
  (dosync (alter db
                 #(update-in % path
                             dissoc attr-name))))
;; (defn del-db-node-attr
;;   [db path attr-name]
;;   (del-db-node-attr-fn db (path/nodes path) attr-name))

(defn rename-db-node-fn
  [db path old-name new-name]
  (dbg/p path)
  (dbg/p old-name)
  (dbg/p new-name)
  (dbg/p "map = " {old-name new-name})
  (dosync (alter db
                 #(update-in % path
                             clojure.set/rename-keys
                             {
                              old-name
                              new-name}))))



  (def a 1)
  (def b 2)
  (type {a b})
  (into {} {a b})
