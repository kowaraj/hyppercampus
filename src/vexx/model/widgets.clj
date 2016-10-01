(ns vexx.model.widgets
  (:require
   [seesaw.core :as ss]

   [vexx.model.debug :as dbg]

;;;   [vexx.model.l-db :as m-db]
   )
  )


(def m-widgets (ref {}))
;; (keys  @m-widgets)

(defn add-w
  " Add a widget "
  [a-widget]
  (let [a-name (ss/config a-widget :id)]
    (println "add widget: (" a-name ") = " a-widget)
    (dosync (alter m-widgets assoc a-name a-widget))))

(defn get-w
  " Getter "
  [a-name]
  (dbg/p a-name)
  (let [w (a-name @m-widgets)]
    (if w
      w
      (println "__ERROR__: no widget found!"))))

