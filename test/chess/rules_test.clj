(ns chess.rules-test
  (:require [clojure.test :refer :all]
            [chess.rules :refer :all]))

(def test-board-1
  [:. :R :r :. :k :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :K :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest includes-all-white-moves
  (is (= (moves test-board-1 true) [{ :from [1 0] :to [0 0] }
                                    { :from [1 0] :to [2 0] }
                                    { :from [1 0] :to [1 1] }
                                    { :from [1 0] :to [1 2] }
                                    { :from [1 0] :to [1 3] }
                                    { :from [1 0] :to [1 4] }
                                    { :from [1 0] :to [1 5] }
                                    { :from [1 0] :to [1 6] }
                                    { :from [1 0] :to [1 7] }
                                    { :from [4 2] :to [3 2] }
                                    { :from [4 2] :to [3 3] }
                                    { :from [4 2] :to [4 3] }
                                    { :from [4 2] :to [5 2] }
                                    { :from [4 2] :to [5 3] }])))

(deftest includes-all-black-moves
  (is (= (moves test-board-1 false) [{ :from [2 0] :to [1 0] }
                                     { :from [2 0] :to [3 0] }
                                     { :from [4 0] :to [3 0] }
                                     { :from [4 0] :to [5 0] }])))


(def test-board-2
  [:. :R :. :. :k :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :K :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(def test-board-3
  [:k :. :. :. :R :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :K :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(def test-board-4
  [:k :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :K :R :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest is-checkmate-if-check-and-no-moves
  (is (checkmate? test-board-2 false)))

(deftest is-not-checkmate-if-check-and-has-moves
  (is (not (checkmate? test-board-3 false))))

(deftest is-not-checkmate-if-no-check-and-no-moves
  (is (not (checkmate? test-board-4 false))))

(deftest is-stalemate-if-no-check-and-no-moves
  (is (stalemate? test-board-4 false)))

(deftest is-not-stalemate-if-checkmate
  (is (not (stalemate? test-board-2 false))))


(def test-board-5
  [:. :. :r :. :. :. :. :.
   :. :. :. :. :. :K :. :.
   :. :. :Q :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.
   :. :. :. :. :. :. :. :.])

(deftest knows-queen-moves
  (is (= (moves test-board-5 true) [{:from [5 1], :to [4 1]}
                                    {:from [5 1], :to [4 2]}
                                    {:from [5 1], :to [5 2]}
                                    {:from [5 1], :to [6 1]}
                                    {:from [5 1], :to [6 2]}
                                    {:from [2 2], :to [0 2]}
                                    {:from [2 2], :to [2 0]}
                                    {:from [2 2], :to [0 4]}
                                    {:from [2 2], :to [0 0]}
                                    {:from [2 2], :to [1 2]}
                                    {:from [2 2], :to [2 1]}
                                    {:from [2 2], :to [1 3]}
                                    {:from [2 2], :to [1 1]}
                                    {:from [2 2], :to [3 2]}
                                    {:from [2 2], :to [2 3]}
                                    {:from [2 2], :to [3 1]}
                                    {:from [2 2], :to [3 3]}
                                    {:from [2 2], :to [4 2]}
                                    {:from [2 2], :to [2 4]}
                                    {:from [2 2], :to [4 0]}
                                    {:from [2 2], :to [4 4]}
                                    {:from [2 2], :to [5 2]}
                                    {:from [2 2], :to [2 5]}
                                    {:from [2 2], :to [5 5]}
                                    {:from [2 2], :to [6 2]}
                                    {:from [2 2], :to [2 6]}
                                    {:from [2 2], :to [6 6]}
                                    {:from [2 2], :to [7 2]}
                                    {:from [2 2], :to [2 7]}
                                    {:from [2 2], :to [7 7]}])))
