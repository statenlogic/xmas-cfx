(ns xmas-cfx.core
  (:require [clojurefx.factory :refer [compile]]
            [clojurefx.clojurefx :refer [run-now]]
            [clojurefx.protocols :as protocols])
  (:import [javafx.scene.control Button Label TextField TextArea CheckBox ComboBox]
           [javafx.scene.layout VBox HBox]
           [javafx.stage StageBuilder]
           [javafx.scene Scene]
           [javafx.event Event ActionEvent EventTarget]
           (javafx.scene.image Image ImageView)))

(def ui-root (atom nil))

(defn make-stage
  [graph]
  (run-now
   (doto
       (.build (StageBuilder/create))
     (.setTitle "Xmas Card editor")
     (.setScene (Scene. (reset! ui-root graph))))))

(defn start [graph]
  (do
    
    (run-now (.show (make-stage graph)))))

(defn make-graph
  []
  (let [button (compile [Button {:text "Alright."}])
        text-field (compile [TextField {}])
        image (Image. "card.jpg" true)
        image-view (ImageView. image)
        _ (protocols/set-action! button (fn [event] (prn (protocols/source event) "text is" (.getText text-field))))
        graph (compile [VBox {:id       "TopLevelVBox"
                              :children [Label {:text "Hi!"}
                                         Label {:text "I'm ClojureFX!"}
                                         image-view
                                         text-field
                                         HBox {:id       "HorizontalBox"
                                               :children [button]}]}])]
    graph))

(defn -main
  [& args]
  (start (make-graph))
  )

