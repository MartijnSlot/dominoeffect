import Data.Char
import Data.List
import System.IO
-- import Data.Tuple.Select


type Bone               = (Int, Int)
type Pos                = (Int, Int)
type GridBoneLocation   = (Pos, Pos, Int, Int)
type Row                = [Int]
type Grid               = [Row]

cls :: IO ()
cls = putStr "\ESC[2J"

width :: Int
width = 8

height :: Int
height = 7

board1 :: Grid --input including header row and columns
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


solutionSpace :: Grid -> [GridBoneLocation]
solutionSpace board = (solutionSubSpace True board) ++ (solutionSubSpace False (transpose board))

solutionSubSpace :: Bool -> Grid -> [GridBoneLocation]
solutionSubSpace rows | rows      = concat . map (handlePairing rows)
                      | otherwise = concat . map (handlePairing False)

handlePairing :: Bool -> Row -> [GridBoneLocation]
handlePairing rows row = makePairs rows n xs
                         where 
                            n  = head row
                            xs = tail row
                
makePairs :: Bool -> Int -> Row -> [GridBoneLocation]
makePairs _ n []           = []
makePairs _ n [x]          = []
makePairs _ 0 _            = []
makePairs True n (x:y:xs)  = ((n, indexX (x:y:xs)), (n, indexY (x:y:xs)), x , y) : makePairs True n (y:xs)
                             where
                                indexX xs = (width + 1) - length xs
                                indexY xs = (width + 2) - length xs
makePairs False n (x:y:xs) = ((indexX (x:y:xs), n), (indexY (x:y:xs), n), x , y) : makePairs False n (y:xs)
                             where
                                indexX xs = (height + 1) - length xs
                                indexY xs = (height + 2) - length xs

transposeBoard :: Grid -> Grid
transposeBoard board = tail (transpose board) -- tail to remove header column

matchBoneToLocations :: Bone -> [GridBoneLocation] -> Int -- check possiblities for a bone
matchBoneToLocations _ []         = 0
matchBoneToLocations (a,b) (x:xs) = filter (==True) (matchTuples a b x : matchBoneToLocations (a,b) xs) --length?

matchTuples :: Int -> Int -> GridBoneLocation -> Bool
matchTuples a b x | getPipsFromLocation x == (a,b) = True
                  | getPipsFromLocation x == (b,a) = True
                  | otherwise = False

getPipsFromLocation :: GridBoneLocation -> (Int,Int)
getPipsFromLocation (_,_,a,b) = (a,b)

neighbours :: GridBoneLocation -> [GridBoneLocation]
neighbours gridboneloc = [gridboneloc, gridboneloc]

--IO GEBEUREN

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
