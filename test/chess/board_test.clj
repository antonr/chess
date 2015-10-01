(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer :all]))

(def test-board
  [:. :. :. :. :k :r :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :K :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :R :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest tells-if-coords-are-valid
  (is (and (valid-coords? [3 3])
           (not (valid-coords? [-1 0]))
           (not (valid-coords? [0 -1])))))

(deftest returns-white-king-coords
  (is (= (king-coords test-board true) [4 2])))

(deftest returns-black-king-coords
  (is (= (king-coords test-board false) [4 0])))

(deftest returns-white-pieces
  (is (= (pieces test-board true) [[:K [4 2]] [:R [0 6]]])))

(deftest returns-black-pieces
  (is (= (pieces test-board false) [[:k [4 0]] [:r [5 0]]])))

(deftest applies-moves
  (is (= (apply-move test-board { :from [4 2] :to [3 2] })
         [:. :. :. :. :k :r :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :K :. :. :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.
          :R :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.])))

(deftest side-is-true-for-white-upper-case
  (is (= (side? :K true) true)))

(deftest side-is-false-for-white-lower-case
  (is (= (side? :k true) false)))

(deftest side-is-false-for-white-dots
  (is (= (side? :. true) false)))

(deftest side-is-true-for-black-lower-case
  (is (= (side? :k false) true)))

(deftest side-is-false-for-black-upper-case
  (is (= (side? :K false) false)))

(deftest side-is-false-for-black-dots
  (is (= (side? :. false) false)))

(deftest rook-is-true-for-lower-r
  (is (rook? :r)))

(deftest rook-is-true-for-upper-r
  (is (rook? :R)))

(deftest rook-is-false-for-everything-else
  (is (not (rook? :-))))

(deftest king-is-true-for-lower-k
  (is (king? :k)))

(deftest king-is-true-for-upper-k
  (is (king? :K)))

(deftest king-is-false-for-everything-else
  (is (not (king? :-))))

(deftest nothing-between-handles-straight-vertical-lines
  (is (nothing-between? test-board [4 0] [4 2])))

(deftest nothing-between-handles-reverse-vertical-lines
  (is (not (nothing-between? test-board [4 6] [4 0]))))

(deftest nothing-between-handles-straight-horizontal-lines
  (is (not (nothing-between? test-board [0 2] [7 2]))))

(deftest nothing-between-handles-reverse-horizontal-lines
  (is (nothing-between? test-board [5 1] [0 1])))

(deftest nothing-between-handles-straight-diagonal-lines
  (is (nothing-between? test-board [0 0] [7 7])))

(deftest nothing-between-handles-reverse-diagonal-lines
  (is (not (nothing-between? test-board [0 6] [5 1]))))

(deftest same-color-is-false-for-white-and-black
  (is (not (same-color? test-board [4 0] [4 2]))))

(deftest same-color-is-false-for-white-and-empty-cell
  (is (not (same-color? test-board [4 2] [4 1]))))

(deftest same-color-is-false-for-black-and-empty-cell
  (is (not (same-color? test-board [4 0] [4 1]))))

(deftest same-color-is-true-for-white-and-white
  (is (same-color? test-board [4 2] [0 6])))

(deftest same-color-is-true-for-black-and-black
  (is (same-color? test-board [4 0] [5 0])))
