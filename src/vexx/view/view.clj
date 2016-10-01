(ns vexx.view.view
  (:require
   [seesaw.core :as ss]
   [seesaw.dev  :as ssd]
   [seesaw.mig  :as ssm]
   [seesaw.bind :as b]

   [vexx.view.make :as v-make]
   [vexx.controller.listbox :as c-listbox]
   [vexx.controller.textin :as c-textin]
   [vexx.controller.tags :as c-tags]
   [vexx.controller.buttons :as c-buttons]
   [vexx.controller.interface :as interface]
))

(comment
  (ns vexx.view.view (:require
   [vexx.view.make :as v-make]

   [vexx.controller.listbox :as c-listbox]
   [vexx.controller.kids :as c-kids]
   [vexx.controller.textin :as c-textin]
   [vexx.controller.interface :as interface]
   [vexx.controller.tpane :as tpane]
   [vexx.controller.buttons :as c-buttons]
   [vexx.controller.utils :as c-utils]
   [vexx.controller.json :as c-json]

   [vexx.model.vol]
   [vexx.model.data]
   [vexx.model.db]
   [vexx.model.path]
   [vexx.model.item]
   [vexx.model.widgets]
:reload))
  )

(ss/native!)

(defn add-behavior
  [root]
  (let [p (ss/select root [:#panel])
        xl (ss/select p [:#xlist])
        text-in (ss/select p [:#text-in])
        cp (ss/select root [:#content-panel])
        ]
    ;; add behavior
    (c-textin/add-behavior)
    (c-listbox/add-behavior)
    (c-tags/add-behavior)
    (c-buttons/add-behavior p)
    root))

(defn make-content
  []
  (let [lp (ssm/mig-panel
            :id :panel
            :items[
                   ;;row
                   ["Propeller"        "split, span, gaptop 10"]

                   ;;row
                   [(v-make/make-button-save)]
                   [(v-make/make-button-load)]
                   [(ss/toggle :id :pencil  :class :tool :text "Pencil" )]
                   [(ss/combobox :id :stroke :class :style
                                 :model [1 2 3 5 8 13 21]) "wrap"]

                   ;;row
                   ["New item:" "gaptop 10"]
                   [(v-make/make-textin) "span, wrap"]
                   
                   ;;row
                   ["Search by tags:" "gaptop 10"]
                   [(ss/text :id :tf-search
                             :text "" :editable? true :columns 25) "span, wrap"]
                   
                   ;;row
                   [(v-make/make-listbox) "span, split 2"] 
                   [(v-make/make-kids) "wrap"]

                   ;;row
                   ["Tags:" "gaptop 2"]
                   [(v-make/make-tags) "wrap"]
                   
                   ]) ; end-of-left-panel
        rp (v-make/make-content-panel) ; end-of-right-panel
        ] ;end-of-let
    (ss/top-bottom-split 
     (ss/left-right-split lp rp)
     (ss/text :id :text-log
              :text "Logging.." :editable? true :columns 10
              :multi-line? true :wrap-lines? true :rows 3)
     )))

(defn make-frame []
  (ss/frame
   :title "Vexx"
   :size [1400 :by 800]
   :content (make-content)))

(defn create-view []
  (let [f (make-frame)]
    (-> f
        add-behavior
        (ss/pack!)
        (ss/show!))
    (interface/add-watchers)
    f))
;(def x (create-view))

