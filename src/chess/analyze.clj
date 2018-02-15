(ns chess.analyze
  (:require [chess.board :as board :refer :all]
            [chess.rules :as rules :refer :all]))

(defn analyze-moves [board winning-side current-side depth]
  (let [opponent (not current-side)
        f (fn [move]
            (let [new-board (board/apply-move board move)
                  checkmate (rules/checkmate? new-board (not winning-side))]
              (if (or checkmate (= depth 0))
                {:move move
                 :responses []
                 :correct checkmate}
                (let [responses (analyze-moves new-board winning-side opponent (- depth 1))
                      correct (if (and winning-side current-side)
                                (every? #(:correct %) responses)
                                (not (every? #(not (:correct %)) responses)))]
                  {:move move
                   :responses responses
                   :correct correct}))))]
    (map f (rules/moves board current-side))))

(defn build-result [moves]
  (let [correct-moves (filter #(:correct %) moves)
        mapper (fn [move]
                  {:move (:move move)
                   :responses (build-result (:responses move))})]
    (map mapper correct-moves)))

(defn analyze [board winning-side current-side depth]
  (let [moves (analyze-moves board winning-side current-side depth)]
    (build-result moves)))
