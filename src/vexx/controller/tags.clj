(ns vexx.controller.tags
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface-2 :as if-2]
   [vexx.controller.key-handler :as kh]

   [vexx.model.debug :as dbg]
   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]

   )
  )

(defn add-watch-tags-data
  "
  To add a watch on vol/tags-data (a ref)
  to update the tags textfield view (input arg)
  "
  [the-widget]
  ;(dbg/p)
  (add-watch (vol/tags-data)
             nil
             (fn [_ _ _ d]
               (ss/config! the-widget :text d))))

;(ss/config! (ss/text "asdf") :text "new")

(defn- listener-keypressed
  [e]
  (dbg/p)
  (if (kh/is-enter e)
    (.consume e)
    (println "listener-keypressed: something else pressed")))

(defn- listener-keytyped
  "
  Listener (key-typed) for the JTextField widget
  for the tags of selected item in the listbox
  Handles:
    Enter or Ctrl+S - to save the content of the text-field
  into the corresponding data-item's :tags
  "
  [e]
  (dbg/p)
  (if (kh/is-enter-or-ctrl-s e)
    (let [w-tf (.getSource e)]
      (dbg/p (ss/text w-tf))
      (if-2/update-tags-data (ss/text w-tf))
      (.setBackground w-tf java.awt.Color/LIGHT_GRAY)
      ;(println "listener-keytyped: Enter pressed, consume it!")
      (.consume e) ;not to print \newline in the jTextfield
      )
    (let [w-tf (.getSource e)]
      ;(println "view.tags/listener-keytyped: Something else pressed")
      (.setBackground w-tf java.awt.Color/WHITE)
      )))

(defn add-behavior
  []
  (dbg/p)
  (let [w (widgets/get-w :tf-tags)]
    (ss/listen w
               :key-typed listener-keytyped
               :key-pressed listener-keypressed
               )))
;(add-behavior)
