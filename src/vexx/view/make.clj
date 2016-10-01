(ns vexx.view.make
  (:require [seesaw.core :as ss]
            [seesaw.dev  :as ssd]
            [seesaw.mig  :as ssm]
            [seesaw.bind :as b]

            [vexx.model.widgets :as widgets]
            [vexx.model.debug :as dbg]

            [vexx.controller.json :as c-json]
            [vexx.controller.listbox :as c-listbox]
            [vexx.controller.kids :as c-kids]
            [vexx.controller.tpane :as c-tpane]
            [vexx.controller.tags :as c-tags]

            ))

(defn make-listbox
  " Creates listbox widget
    Called from view/make-content
  "
  []
  (let [w (ss/listbox :id :xlist
                      :size [200 :by 400]
                      :model ["none"])]
    (widgets/add-w w)
    (c-listbox/add-watch-listbox-data w)
    w))


(defn make-kids
  " Create kids listbox
  "
  []
  (let [w (ss/listbox :id :xkids
                      :size [200 :by 400]
                      :model ["none"])]
    (widgets/add-w w)
    (c-kids/add-watch-kids-data w)
    w))


(defn make-tags
  " JTextField, contains item's tags (commas separated)"
  []
  (let [w (ss/text :id :tf-tags
                   :text ""
                   :editable? true
                   :multi-line? :true
                   :wrap-lines? true
                   :columns 30
                   :rows 1)]
    (widgets/add-w w)
    (c-tags/add-watch-tags-data w)
    w))


(defn make-tpane
  []
  (ss/tabbed-panel
            :id :tpane
            :placement :top
            :size [400 :by 500]
            :tabs [
                   {:title "tab1" :content "content of tab1"}
                   {:title "tab2" :content "content of tab2"}
                   ]))


(defn make-textin
  []
  (let [w (ss/text :id :text-in
           :text "" :editable? true :columns 28) ]
    (widgets/add-w w)
    w))


(defn make-button-save
  []
  (let [b (ss/button :id :buttonSave :class :tool :text "Save")]
    (widgets/add-w b)
    ;;(ss/listen b :action (fn [e] (c-json/save-to-file)))
    b))


(defn make-button-load
  []
  (let [b (ss/button :id :buttonLoad :class :tool :text "Load")]
    (widgets/add-w b)
    ;;(ss/listen b :action (fn [e] (c-json/load-from-file)))
    b))


(defn make-content-panel
  []
  (let [
        content-panel (ss/border-panel
           :id :content-panel
           :size [500 :by 500]
           :center (ss/label "default-center-label")
           )

        ]
    
    (widgets/add-w content-panel)
    (c-tpane/add-watch-content-data content-panel)
    content-panel))







