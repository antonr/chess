(ns chess.fen)

(defn char-digit [c]
  (Character/getNumericValue c))

(defn char-digit? [c]
  (< -1 (char-digit c) 10))

(defn parse-char [c]
  (cond (char-digit? c) (repeat (char-digit c) :.)
        :else           (keyword (str c))))

(defn parse-board [str]
  (->> (filter #(not= \/ %) str)
       (map parse-char)
       (flatten)))

(defn parse [str]
  (let [parts (.split str " ")
        board (first parts)]
       (parse-board board)))
