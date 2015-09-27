(ns chess.format)

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
  (concat rows [(cons "     " (interpose "   " "abcdefgh"))]))

(defn add-newlines [rows]
  (mapcat #(concat % '("\n")) rows))

(defn board [cells]
  (apply str (->> (split-rows cells)
                  (add-line-numbers)
                  (add-column-borders)
                  (add-column-letters)
                  (add-line-borders)
                  (add-newlines))))
