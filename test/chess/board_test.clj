(ns chess.board-test
  (:require [clojure.test :refer :all]
            [chess.board :refer :all]))

(def test-board
  [:. :. :. :. :k :. :. :.
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

(deftest applies-moves
  (is (= (apply-move test-board { :from [4 2] :to [3 2] })
         [:. :. :. :. :k :. :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :K :. :. :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.
          :R :. :. :. :. :. :. :.
          :. :. :. :. :. :. :. :.])))
