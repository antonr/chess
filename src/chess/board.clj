(ns chess.board)

(def empty-cell :.)

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

(defn queen? [c]
  (= (Character/toLowerCase (to-char c)) \q))

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

(defn empty-cell? [c]
  (= c empty-cell))

(defn empty-coords? [board coords]
  (let [index (coords2index coords)
        piece (get board index)]
    (empty-cell? piece)))

(defn same-color? [board coords1 coords2]
  (let [index1 (coords2index coords1)
        index2 (coords2index coords2)
        piece1 (get board index1)
        piece2 (get board index2)]
    (or (and (white? piece1) (white? piece2))
        (and (black? piece1) (black? piece2)))))

(defn line-between [[x1 y1] [x2 y2]]
  (let [lx (- x2 x1)
        ly (- y2 y1)
        len (max lx ly (- lx) (- ly))
        dx (/ lx len)
        dy (/ ly len)
        f (fn [t] [(+ x1 (* t dx)) (+ y1 (* t dy))])]
    (map f (range 1 len))))

(defn nothing-between? [board from to]
  (every? #(empty-coords? board %) (line-between from to)))
