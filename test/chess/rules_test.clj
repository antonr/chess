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
   :. :R :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest includes-all-white-moves
  (is (= (moves test-board true) [{ :from [4 2] :to [3 2] }
                                  { :from [4 2] :to [3 3] }
                                  { :from [4 2] :to [4 3] }
                                  { :from [4 2] :to [5 2] }
                                  { :from [4 2] :to [5 3] }
                                  { :from [1 6] :to [1 0] }
                                  { :from [1 6] :to [1 1] }
                                  { :from [1 6] :to [1 2] }
                                  { :from [1 6] :to [1 3] }
                                  { :from [1 6] :to [1 4] }
                                  { :from [1 6] :to [0 6] }
                                  { :from [1 6] :to [1 5] }
                                  { :from [1 6] :to [2 6] }
                                  { :from [1 6] :to [1 7] }
                                  { :from [1 6] :to [3 6] }
                                  { :from [1 6] :to [4 6] }
                                  { :from [1 6] :to [5 6] }
                                  { :from [1 6] :to [6 6] }
                                  { :from [1 6] :to [7 6] }])))

(deftest includes-all-black-moves
  (is (= (moves test-board false) [{ :from [4 0] :to [3 0] }
                                   { :from [4 0] :to [5 0] }])))
