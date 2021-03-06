(ns vexx.model.item
  (:require
   [vexx.model.debug :as dbg]
   [vexx.model.path :as path]

   [vexx.util.time :as time]
   ))

(defn make-new-item
  [item-name]
  {:pre [(= (type item-name) java.lang.String)]}
  (dbg/p @(path/current-path))
  {(keyword item-name) {:nodes {}
                        :path @(path/current-path)
                        :tags ""
                        :data {:name nil :content "default content"}
                        :link-to {}
                        :link-from {}
                        :ts (time/date)
                        }})
  
;; (defn- add-item-to-path
;;   [db item path]
;;   (db/add-element-to-path db item path))


;; (defn- add-item-fn
;;   [db item]
;;   (add-item-to-path db item (data/current-path)))


;; (defn- add-item
;;   "Add item to db"
;;   [item-name]
;;   (dbg/p item-name)
;;   (let [item (make-new-item item-name)]
;;     (add-item-fn data/db item)
;;     item))

;; (defn- get-item-fn
;;   [item-name]
;;   (let [path (data/current-path)]
;;     (search/search-itemname-in-path item-name path)))

  
;; (defn get-item
;;   "Get item from db if found or default"
;;   [item-name]
;;   (dbg/p item-name)
;;   (let [item (get-item-fn item-name)]
;;     (if item
;;       item
;;       (add-item item-name))))

;; (defn test-get-new-item
;;   [i-name]
;;   (make-new-item i-name))


