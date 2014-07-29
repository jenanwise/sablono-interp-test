(ns sablono-interp-test.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :refer-macros [html]]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"}))

(defn compiled
  [app owner]
  ;; This compiles down to
  ;; React.DOM.div(null, React.DOM.div(null), React.DOM.div(null))
  (html
   [:div [:div] [:div]]))

(defn interpreted
  [app owner]
  ;; This evaluates as
  ;; React.DOM.div(null, [React.DOM.div(null), React.DOM.div(null)])
  (html
   (let []
     [:div [:div] [:div]])))

(om/root
    ;; When using `interpreted`, React warns about dynamic children
    ;; under sablono <= 0.2.18, but `compiled` does not trigger the
    ;; warning.
    interpreted
    app-state
    {:target (. js/document (getElementById "app"))})

;; With React 0.9.0, the first render doesn't trigger the react
;; warning. Updating the state, even if it's not used, will trigger
;; the warning.
;;
;; With React 0.11.1, we don't have to update anything or even use om
;; -- just calling `(interpreted nil nil)` will trigger the warning.
(swap! app-state assoc-in [:text] "new message")
