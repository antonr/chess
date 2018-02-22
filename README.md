# chess

Chess puzzle solver

## Usage

```
$ lein run {FEN notation} {number of moves}

# i.e.
$ lein run "3k4/8/4K3/8/8/8/2R4P/8 w ---- - 0 1" 2
   +---+---+---+---+---+---+---+---+
 8 |   |   |   | k |   |   |   |   |
   +---+---+---+---+---+---+---+---+
 7 |   |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+---+
 6 |   |   |   |   | K |   |   |   |
   +---+---+---+---+---+---+---+---+
 5 |   |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+---+
 4 |   |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+---+
 3 |   |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+---+
 2 |   |   | R |   |   |   |   | P |
   +---+---+---+---+---+---+---+---+
 1 |   |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+---+
     a   b   c   d   e   f   g   h

Solution:
1. Rc1 Ke8 2. Rc8#
1. Rc3 Ke8 2. Rc8#
1. Rc4 Ke8 2. Rc8#
1. Rc5 Ke8 2. Rc8#
1. Rc6 Ke8 2. Rc8#
1. h3 Ke8 2. Rc8#
1. h4 Ke8 2. Rc8#
```

## License

Copyright © 2018 Anton Rogov

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
