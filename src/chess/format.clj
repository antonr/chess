(ns chess.format
  (:require [chess.board :as board]
            [clojure.string :as str]))

(def letters "abcdefgh")

(defn split-rows [cells]
  (partition 8 cells))

(defn add-line-numbers [rows]
  (map #(cons (str %1) %2) (iterate dec 8) rows))

(defn add-column-borders-to-cell [cell]
  (cond (= cell :.) (str "   |")
        :else (str " " (name cell) " |")))

(defn add-column-borders-to-row [cells]
  (map add-column-borders-to-cell cells))

(defn add-column-borders [rows]
  (map add-column-borders-to-row rows))

(defn add-line-borders [rows]
  (let [border (concat "   +" (repeat 8 "---+"))]
    (cons border (interpose border rows))))

(defn add-column-letters [rows]
  (concat rows [(cons "     " (interpose "   " letters))]))

(defn add-newlines [rows]
  (mapcat #(concat % '("\n")) rows))

(defn board [cells]
  (->>
    (split-rows cells)
    (add-line-numbers)
    (add-column-borders)
    (add-column-letters)
    (add-line-borders)
    (concat)
    (map #(apply str %))))

(defn piece [p]
  (cond (board/king? p) "K"
        (board/queen? p) "Q"
        (board/rook? p) "R"
        :else ""))

(defn move [board move]
  (let [from (:from move)
        from-index (board/coords2index from)
        p (get board from-index)
        [x y] (:to move)
        col (get letters x)
        row (- 8 y)]
    (str (piece p) col row)))

(defn move-with-responses [moves board item]
  (let [move-str (move board (:move item))
        new-moves (conj moves move-str)
        responses (:responses item)]
    (if (empty? responses)
      (str/join " "
        (map (fn [n [w b]]
               (str
                 (inc n) ". " w
                 (if (empty? b)
                   "#" ; FIXME: checkmate should be a part of move
                   (str " " b))))
          (range)
          (partition 2 2 nil new-moves)))
      (map #(move-with-responses new-moves board %) (:responses item)))))

(defn moves [board moves]
  (flatten (map #(move-with-responses [] board %) moves)))
