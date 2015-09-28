(ns chess.board)

(def empty-cell
  :.)

(defn index2coords [index]
  [(mod index 8) (quot index 8)])

(defn coords2index [[x y]]
  (+ x (* y 8)))

(defn valid-coords? [coords]
  (every? #(>= % 0) coords))

(defn king-index [board white]
  (.indexOf board (if white :K :k)))

(defn king-coords [board white]
  (index2coords (king-index board white)))

(defn apply-move [board move]
  (let [from (coords2index (:from move))
        to (coords2index (:to move))
        piece (get board from)
        result (-> (assoc board from empty-cell)
                   (assoc to piece))]
    result))
