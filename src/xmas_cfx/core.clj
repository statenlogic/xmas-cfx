(ns xmas-cfx.core
  (:require [clojurefx.factory :refer [compile]]
            [clojurefx.clojurefx :refer [run-now]]
            [clojurefx.protocols :as protocols])
  (:import [javafx.scene.control Button Label TextField TextArea CheckBox ComboBox]
           [javafx.scene.layout VBox HBox StackPane]
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
        image (Image. "card.jpg" 100.0 100.0 false false)
        image-view (ImageView. image)
        _ (protocols/set-action! button (fn [event] (prn (protocols/source event) "text is" (.getText text-field))))
        #_(stack-pane) #_(compile [StackPane {:id       "the stack pane"
                                              :children [image-view
                                                         Label {:text "Message here"}]}])

        graph (compile [VBox {:id       "TopLevelVBox"
                              :children [Label {:text "Xmas Card Maker"}
                                         Label {:text "Choose greeting message:"}
                                         StackPane {:id       "the stack pane"
                                                    :children [ImageView {:image???? image}
                                                               Label {:text "Message here"}]}
                                         text-field
                                         HBox {:id       "HorizontalBox"
                                               :children [button]}]}])]
    graph))

(defn -main
  [& args]
  (start (make-graph))
  )

