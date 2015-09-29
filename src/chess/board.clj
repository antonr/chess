(ns chess.board)

(def empty-cell
  :.)

(defn to-char [c]
  (get (name c) 0))

(defn white? [c]
  (Character/isUpperCase (to-char c)))

(defn black? [c]
  (Character/isLowerCase (to-char c)))

(defn side? [c white]
  (if white (white? c) (black? c)))

(defn rook? [c]
  (= (Character/toLowerCase (to-char c)) \r))

(defn king? [c]
  (= (Character/toLowerCase (to-char c)) \k))

(defn index2coords [index]
  [(mod index 8) (quot index 8)])

(defn coords2index [[x y]]
  (+ x (* y 8)))

(defn valid-coords? [coords]
  (every? #(>= 7 % 0) coords))

(defn king-index [board white]
  (.indexOf board (if white :K :k)))

(defn king-coords [board white]
  (index2coords (king-index board white)))

(defn pieces [board white]
  (map (fn [[index piece]] [piece (index2coords index)])
       (filter (fn [[index piece]] (side? piece white))
               (map-indexed vector board))))

(defn apply-move [board move]
  (let [from (coords2index (:from move))
        to (coords2index (:to move))
        piece (get board from)
        result (-> (assoc board from empty-cell)
                   (assoc to piece))]
    result))
