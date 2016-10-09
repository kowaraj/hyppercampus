;; For the tabbed panel
;; To display the content of selected node (main listbox)

(ns vexx.controller.tpane
  (:require
   [seesaw.core :as ss]

   [vexx.controller.interface-2 :as if-2]
   [vexx.controller.key-handler :as kh]

   [vexx.model.data :as data]
   [vexx.model.vol :as vol]
   [vexx.model.widgets :as widgets]
   [vexx.model.debug :as dbg]

   )
  )

(defn- tf--listener-keyreleased
  "
  Listener (key-released) for the JTextField widget
  Handles:
    Ctrl+S - to save the content of the text-field
  into the corresponding data-item of db
  "
  [e]
  (if (kh/is-ctrl-s e)
    (let [w-tf (.getSource e)
          ;; tab-name (name (ss/config w-tf :id)) ; get the name of the _TAB_ from the button's _ID_
          ;; tf-content (ss/text w-tf)
          ]
      (println "Ctrl+S pressed, saving the doc... For"); tab=" tab-name)
      (println "---> " w-tf); tab=" tab-name)
      (if-2/update-content-data  (ss/text w-tf))
      ;;(.setBackground w-tf java.awt.Color/LIGHT_GRAY)
      (.setBackground w-tf (java.awt.Color. 240 240 240))
      )
    (let [w-tf (.getSource e)]
      (.setBackground w-tf java.awt.Color/WHITE))))
;;;    (println "something else pressed")))



(defn- populate-content-panel
  [p data]
  ;(dbg/p data)
  (let [
        tf (ss/text :id (:name data)
                    :text (:content data)
                    :editable? true
                    :multi-line? true
                    :wrap-lines? true
                    :columns 10
                    :rows 10)
        ]
    ;;(.setBackground tf java.awt.Color/LIGHT_GRAY)
    (.setBackground tf (java.awt.Color. 240 240 240))
    
    (ss/listen tf :key-released #(tf--listener-keyreleased %))
    (.removeAll p)
    (.add p tf)
    ;(println "re-do the panel")
    (.revalidate p)
    (.repaint p)

    ;;(ss/pack!

;;     (def p (ssm/mig-panel
;;         :id :ppp :items [ [t]]))
;; (def f (ss/frame :size [1400 :by 800] :content p))
;; (.add p t)
;; (ss/pack! f)
;; (ss/show! f)

    ))

(defn add-watch-content-data
  "
  To add a watch on vol/kids-data (a ref)
  to update the second listbox view (input arg)
  "
  [the-widget]
  ;(dbg/p)
  (add-watch (vol/content-data)
             nil
             (fn [_ _ _ d]
               (populate-content-panel the-widget d))))


(defn listener-selection
  [e]
;  (data/list-selection-set-name (ss/selection e))

;  (let [sel-data (data/sel-item-data) ]
    ;(c-tpane/add-tabs sel-data)
;    )
)

(defn add-behavior
  []
;;  (ss/listen (widgets/get-w :content-panel)
  ;;            :selection listener-selection
             ;; :selection #(listener-selection % tpane tf-tags)
             ;; :key-released #(listener-keyreleased % tpane)
             ;; :focus-lost #(listener-focuslost %)
             ;; :focus-gained #(listener-focusgained %)
             );)
