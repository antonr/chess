(ns chess.analyze-test
  (:require [clojure.test :refer :all]
            [chess.analyze :refer :all]))

(def test-board
  [:. :. :. :k :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :K :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :R :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(def letters "abcdefgh")

(defn format-cell [[x, y]]
  [(get letters x) (- 8 y)])

(defn format-move [move]
  (concat (format-cell (:from move))
          '(\-)
          (format-cell (:to move))))

(defn format-tree [node depth]
  (cons (apply str (concat (repeat depth "..... ")
                           (format-move (:move node))
                           (if (:checkmate node) '(\X) nil)))
        (map #(format-tree % (+ depth 1)) (:responses node))))

(deftest analyzes-moves
  (is (= (map #(format-tree % 0) (analyze test-board true 2)) [])))
