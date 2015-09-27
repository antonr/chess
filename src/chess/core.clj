(ns chess.core
  (:require [chess.format :as format :refer [board]]
            [chess.fen :as fen :refer [parse]]))

(defn -main [args]
  (println (format/board (fen/parse args))))
