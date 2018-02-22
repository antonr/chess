(ns chess.core
  (:require [chess.fen :as fen]
            [chess.format :as format]
            [chess.analyze :as analyze]
            [clojure.string :as str]))

(defn -main [fen depth]
  (let [board (vec (fen/parse fen))
        solution (analyze/analyze board true true 2)]
    (println
      (str/join "\n"
           (concat
             (format/board board)
             [""]
             ["Solution:"]
             (format/moves board solution))))))
