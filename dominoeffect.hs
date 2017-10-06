import Data.Char
import Data.List
import System.IO

type Bone               = (Int, Int)
type Pos                = (Int, Int)
type GridBoneLocations  = [(Pos, Pos, Int, Int)]
type Row                = [Int]
type Grid               = [Row]

cls :: IO ()
cls = putStr "\ESC[2J"

width :: Int
width = 8

height :: Int
height = 7

board1 :: Grid
board1 = [row0, row1, row2, row3, row4, row5, row6, row7]
                 where
                    row0 = [0,1,2,3,4,5,6,7,8]
                    row1 = [1,5,4,3,6,5,3,4,6]
                    row2 = [2,0,6,0,1,2,3,1,1]
                    row3 = [3,3,2,6,5,0,4,2,0]
                    row4 = [4,5,3,6,2,3,2,0,6]
                    row5 = [5,4,0,4,1,0,0,4,1]
                    row6 = [6,5,2,2,4,4,1,6,5]
                    row7 = [7,5,5,3,6,1,2,3,1]

putGrid :: Grid -> IO()
putGrid = putStrLn . unlines . concat . map showRow
          -- where
          --    bar = [replicate ((width * 4) - 1) ' ']

showRow :: [Int] -> [String]
showRow = interleave space . map show 
          where
             space = " "

showInt :: Int -> [String]
showInt 0 = ["   ", " 0 ", "   "]
showInt 1 = ["   ", " 1 ", "   "]
showInt 2 = ["   ", " 2 ", "   "]
showInt 3 = ["   ", " 3 ", "   "]
showInt 4 = ["   ", " 4 ", "   "]
showInt 5 = ["   ", " 5 ", "   "]
showInt 6 = ["   ", " 6 ", "   "]

interleave :: a -> [a] -> [a]
interleave x [] = []
interleave x [y] = [y]
interleave x (y:ys) = y : x : interleave x ys

solutionSpace :: Grid -> GridBoneLocations
solutionSpace board = (solutionSubSpace board) ++ (solutionSubSpace (transpose board))

solutionSubSpace :: Grid -> GridBoneLocations
solutionSubSpace = concat . map handleRow

handleRow :: Row -> GridBoneLocations
handleRow row = makePairs n xs
            where 
               n  = head row
               xs = tail row

--TODO fix row, col -> col, row
                
makePairs :: Int -> Row -> GridBoneLocations
makePairs n []          = []
makePairs n [x]         = []
makePairs 0 _           = []
makePairs n (x:y:xs)    = ((n, indexX (x:y:xs)), (n, indexY (x:y:xs)), x , y) : makePairs n (y:xs)
                             where
                                indexX xs = 9 - length xs
                                indexY xs = 10 - length xs

transposeBoard :: Grid -> Grid
transposeBoard board = tail (transpose board)



