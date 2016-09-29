(ns vexx.controller.key-handler
  (:require
   [seesaw.core :as ss]

))

(defn is-typed--enter
  [e]
  (= (.getKeyChar e) \newline))

(defn is-pressed--ctrl-s
  [e]
  (and (== (.getModifiers e) java.awt.event.InputEvent/CTRL_MASK)
       (== (.getKeyCode e) java.awt.event.KeyEvent/VK_S)))



(defn is-enter-or-ctrl-s
  [e]
  (let [ch (.getKeyChar e)]
    (if (or (= ch \newline)
            (and (== (.getModifiers e) java.awt.event.InputEvent/CTRL_MASK)
                 (== (.getKeyCode e) java.awt.event.KeyEvent/VK_S)))
      true
      false)))
