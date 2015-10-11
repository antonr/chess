(ns chess.analyze
  (:require [chess.board :as board :refer :all]
            [chess.rules :as rules :refer :all]))

(defn analyze [board white depth]
  (let [opponent (not white)
        f (fn [move]
            (let [new-board (board/apply-move board move)
                  checkmate (rules/checkmate? new-board opponent)]
              (if (or checkmate (= depth 0)) { :move move :checkmate checkmate :responses [] }
                  (let [responses (analyze new-board opponent (- depth 1))
                        checkmate (every? #(:checkmate %) responses)]
                    { :move move :checkmate checkmate :responses responses }))))]
  (map f (rules/moves board white))))
