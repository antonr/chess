(ns chess.rules-test
  (:require [clojure.test :refer :all]
            [chess.rules :refer :all]))

(def test-board
  [:. :. :. :. :k :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :K :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :R :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest includes-all-white-king-moves
  (is (= (moves test-board true) [{ :from [4 2] :to [3 2] }
                                  { :from [4 2] :to [5 2] }
                                  { :from [4 2] :to [3 3] }
                                  { :from [4 2] :to [4 3] }
                                  { :from [4 2] :to [5 3] }])))

(deftest includes-all-black-king-moves
  (is (= (moves test-board false) [{ :from [4 0] :to [3 0] }
                                   { :from [4 0] :to [5 0] }])))
