(ns vexx.controller.key-handler
  (:require
   [seesaw.core :as ss]

))

(defn is-enter
  [e]
  (= (.getKeyChar e) \newline))

(defn is-del
  [e]
  (= java.awt.event.KeyEvent/VK_DELETE (.getKeyCode e)))

(defn is-ctrl-s
  [e]
  (and (== (.getModifiers e) java.awt.event.InputEvent/CTRL_MASK)
       (== (.getKeyCode e) java.awt.event.KeyEvent/VK_S)))

(defn is-ctrl-r
  [e]
  (and (== (.getModifiers e) java.awt.event.InputEvent/CTRL_MASK)
       (== (.getKeyCode e) java.awt.event.KeyEvent/VK_R)))

(defn is-left
  [e]
  (= java.awt.event.KeyEvent/VK_LEFT (.getKeyCode e)))

(defn is-right
  [e]
  (= java.awt.event.KeyEvent/VK_RIGHT (.getKeyCode e)))



(defn is-enter-or-ctrl-s
  [e]
  (if (or (is-enter e)
          (is-ctrl-s))
    true
    false))
