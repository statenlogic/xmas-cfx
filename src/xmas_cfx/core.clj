(ns xmas-cfx.core
  (:require [clojurefx.factory :refer [compile]]
            [clojurefx.clojurefx :refer [run-now]])
  (:import [javafx.scene.control Button Label]
           [javafx.scene.layout VBox HBox]
           [javafx.stage StageBuilder]
           [javafx.scene Scene]))


(def graph (compile [VBox {:id "TopLevelVBox"
                           :children [Label {:text "Hi!"}
                                      Label {:text "I'm ClojureFX!"}
                                      HBox {:id "HorizontalBox"
                                            :children [Button {:text "Alright."}]}]}]))

(def ui-root (atom nil))

(def stage
  (run-now
   (doto
       (.build (StageBuilder/create))
     (.setTitle "Motivations editor")
     (.setScene (Scene. (reset! ui-root graph))))))

(defn start []
  (do
    
    (run-now (.show stage))))

(defn -main
  [& args]
  (start))
