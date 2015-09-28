(ns chess.rules
  (:require [chess.board :as board :refer :all]))

(defn move-within-board? [move]
  (board/valid-coords? (:to move)))

(defn all-possible-moves [board white]
  (let [[x y] (board/king-coords board white)]
    (filter move-within-board? [{ :from [x y] :to [(- x 1) (- y 1)] }
                                { :from [x y] :to [x (- y 1)] }
                                { :from [x y] :to [(+ x 1) (- y 1)] }
                                { :from [x y] :to [(- x 1) y] }
                                { :from [x y] :to [(+ x 1) y] }
                                { :from [x y] :to [(- x 1) (+ y 1)] }
                                { :from [x y] :to [x (+ y 1)] }
                                { :from [x y] :to [(+ x 1) (+ y 1)] }])))

(defn check? [board white]
  (let [king-coords (board/king-coords board white)]
    (some #(= king-coords (:to %)) (all-possible-moves board (not white)))))

(defn valid-move? [board white move]
  (not (check? (board/apply-move board move) white)))

(defn validate [board white moves]
  (filter #(valid-move? board white %) moves))

(defn moves [board white]
  (validate board white (all-possible-moves board white)))
