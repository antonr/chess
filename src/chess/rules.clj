(ns chess.rules
  (:require [chess.board :as board :refer :all]))

(defn move-within-board? [move]
  (board/valid-coords? (:to move)))

(defn all-possible-king-moves [[x y]]
  (for [dx (range -1 2)
        dy (range -1 2)
        :when (not (and (= dx 0)
                        (= dy 0)))]
   { :from [x y] :to [(+ x dx) (+ y dy)] }))

(defn all-possible-rook-moves [[x y]]
  (flatten (for [d (range -7 8)
                 :when (not (= d 0))]
             [{ :from [x y] :to [(+ x d) y] }
              { :from [x y] :to [x (+ y d)] }])))

(defn piece-moves [[piece coords]]
  (cond (board/king? piece) (all-possible-king-moves coords)
        (board/rook? piece) (all-possible-rook-moves coords)))

(defn all-possible-moves [board white]
  (filter move-within-board? (flatten (map piece-moves (board/pieces board white)))))

(defn check? [board white]
  (let [king-coords (board/king-coords board white)]
    (some #(= king-coords (:to %)) (all-possible-moves board (not white)))))

(defn valid-move? [board white move]
  (not (check? (board/apply-move board move) white)))

(defn validate [board white moves]
  (filter #(valid-move? board white %) moves))

(defn moves [board white]
  (validate board white (all-possible-moves board white)))
