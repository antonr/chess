(ns chess.format-test
  (:require [clojure.test :refer :all]
            [chess.format :refer :all]))

(def test-board
  [:. :. :. :k :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :K :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :R :. :. :. :. :P
   :. :. :. :. :. :. :. :.])

(deftest test-format-board
  (is (= (board test-board)
         ["   +---+---+---+---+---+---+---+---+"
          " 8 |   |   |   | k |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 7 |   |   |   |   |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 6 |   |   |   |   | K |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 5 |   |   |   |   |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 4 |   |   |   |   |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 3 |   |   |   |   |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          " 2 |   |   | R |   |   |   |   | P |"
          "   +---+---+---+---+---+---+---+---+"
          " 1 |   |   |   |   |   |   |   |   |"
          "   +---+---+---+---+---+---+---+---+"
          "     a   b   c   d   e   f   g   h"])))

(deftest test-format-moves
  (let [test-moves [{:move {:from [7 6] :to [7 5]}
                     :responses [{:move {:from [3 0] :to [4 0]}
                                  :responses [{:move {:from [2 6] :to [2 0]}
                                               :responses []}]}]}
                    {:move {:from [7 6] :to [7 4]}
                     :responses [{:move {:from [3 0] :to [4 0]}
                                  :responses [{:move {:from [2 6] :to [2 0]}
                                               :responses []}]}]}]]
  (is (= (moves test-board test-moves)
         ["1. h3 Ke8 2. Rc8#"
          "1. h4 Ke8 2. Rc8#"]))))
