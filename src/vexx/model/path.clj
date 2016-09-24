(ns vexx.model.path
  (:require
   [vexx.model.debug :as dbg]
   )
  )

(comment "------------------------------------------------ path ---
 Current path in db
 Path for the root element of the main listbox
 - to add a child
 - to add a data
 - to add a sibling
")

(def m-current-path (ref [:root]))
;; (def m-current-path (ref [1 2 3]))
;; (def m-current-path (ref [:root]))

(defn current-path [] m-current-path)
;(current-path)

(defn is-at-root
  [path]
  (= (peek path) :root))


(defn- is-not-at-root
  ([]
  (not (= (peek @m-current-path) :root))))
        
(defn go-level-up
  []
  (if (is-not-at-root)
    (dosync (alter m-current-path pop))))
;(go-level-up)
;@m-current-path

(defn go-level-down
  [node-name] ; node-name, not item-name, means a keyword
  {:pre [(= (type node-name) clojure.lang.Keyword)]}
  (dosync (alter m-current-path conj node-name)))
;; (def m-current-path (ref [1 2 3]))
;(go-level-down 4)
;@m-current-path


(defn conj-node
  [path node-name]
  {:pre [(= (type node-name) java.lang.String)]}
  (conj path (keyword node-name)))


(defn nodes
  " path for a node's nodes
    - for path [1 2 3]
    - ret val will be [1 :nodes 2 :nodes 3 :nodes]
  " 
  [path]
  (interleave path (repeat :nodes)))

(defn node
  " path for a node itself
    - for path [1 2 3]
    - ret val will be [1 :nodes 2 :nodes 3 :nodes]
  " 
  [path]
  (pop (apply vector (interleave path (repeat :nodes)))))
;; (interleave [1 2 3] (repeat :nodes)) => (1 :nodes 2 :nodes 3 :nodes)
;; (pop [1 2 3])

(defn attr
  " path for a attribute (cut last :nodes)"
  [path]
  (pop (apply vector (interleave path (repeat :nodes)))))
;;(interleave [1 2 3] (repeat :nodes)))
;;(type (pop [1 2 3]))
;; (attr [1 2 3])
