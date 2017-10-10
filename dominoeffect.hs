import Data.Char

import Data.Maybe

type Bone               = (Int, Int) --TODO add stone number (eventually for printing)
type Pos                = (Int, Int)
type GridBoneLocation   = (Pos, Pos, Bone)
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

bones :: [Bone]
bones = [(x,y) | x <- [0..6], y <- [x..6]]

solutionSpace :: Grid -> [GridBoneLocation]
solutionSpace board = do (solutionSubSpace True board) ++ (solutionSubSpace False (transpose board))
                         --remove positions for bones with 1 location

solutionSubSpace :: Bool -> Grid -> [GridBoneLocation]
solutionSubSpace rows | rows      = concat . map (handlePairing rows)
                      | otherwise = concat . map (handlePairing False)

handlePairing :: Bool -> Row -> [GridBoneLocation]
handlePairing rows row = makePairs rows n xs
                         where 
                            n  = head row
                            xs = tail row
                
-- function to pair up 2 positions and pips in a list of possible bone locations on the grid
-- True signifies pairing of rows, False signifies pairing of (transposed) columns
makePairs :: Bool -> Int -> Row -> [GridBoneLocation]
makePairs _ n []           = []
makePairs _ n [x]          = []
makePairs _ 0 _            = []
makePairs True n (x:y:xs)  = ((n, indexX (x:y:xs)), (n, indexY (x:y:xs)), (x,y)) : makePairs True n (y:xs)
                             where
                                indexX xs = (width + 1) - length xs
                                indexY xs = (width + 2) - length xs
makePairs False n (x:y:xs) = ((indexX (x:y:xs), n), (indexY (x:y:xs), n), (x,y)) : makePairs False n (y:xs)
                             where
                                indexX xs = (height + 1) - length xs
                                indexY xs = (height + 2) - length xs

countBonePossibilities :: [GridBoneLocation] -> Bone -> Int
countBonePossibilities locations bone = length (matchBoneToLocations locations bone) --HARDCODED board!

matchBoneToLocations :: [GridBoneLocation] -> Bone -> [GridBoneLocation] -- check locations for a bone
matchBoneToLocations [] _ = []
matchBoneToLocations xs a = catMaybes (map (matchTuples a) xs)

matchTuples :: Bone -> GridBoneLocation -> Maybe GridBoneLocation
matchTuples (a,b) x | getBone x == (a,b) = Just x
                    | getBone x == (b,a) = Just x
                    | otherwise = Nothing

data Tree a = Node a [Tree a] deriving Show

treeSome :: [GridBoneLocation] -> [Bone] -> Tree [GridBoneLocation]
treeSome g b = Node g [treeSome (emptyLocations g (filledLocations g b)) (remainingBones g b)]

remainingBones :: [GridBoneLocation] -> [Bone] -> [Bone]
remainingBones xs ys | length (getBonesWith1Location xs ys) /= 0  = unusedBones ys (getBonesWith1Location xs ys)
                     | length (getBonesWith2Locations xs ys) /= 0 = unusedBones ys (getBonesWith2Locations xs ys)
                     | otherwise                                  = unusedBones ys (getBonesWith3Locations xs ys)

filledLocations :: [GridBoneLocation] -> [Bone] -> [GridBoneLocation]
filledLocations locations = concat . map (matchBoneToLocations locations) . remainingBones locations

emptyLocations :: [GridBoneLocation] -> [GridBoneLocation] -> [GridBoneLocation]
emptyLocations currentSolutionSpace filledSpace = (currentSolutionSpace \\ filledSpace)

getBone :: GridBoneLocation -> Bone
getBone (_,_,(a,b)) = (a,b)

getBonesWith1Location :: [GridBoneLocation] -> [Bone] -> [Bone]
getBonesWith1Location locations unusedBones = [unusedBones !! x | x <- xs]
                        where 
                           xs = findIndices (==1) (map (countBonePossibilities locations) unusedBones)

getBonesWith2Locations :: [GridBoneLocation] -> [Bone] -> [Bone]
getBonesWith2Locations locations unusedBones = [unusedBones !! x | x <- xs]
                        where 
                           xs = findIndices (==2) (map (countBonePossibilities locations) unusedBones)

getBonesWith3Locations :: [GridBoneLocation] -> [Bone] -> [Bone]
getBonesWith3Locations locations unusedBones = [unusedBones !! x | x <- xs]
                        where 
                           xs = findIndices (==3) (map (countBonePossibilities locations) unusedBones)

unusedBones :: [Bone] -> [Bone] -> [Bone]
unusedBones currentBones usedBones = (currentBones \\ usedBones)

solve :: Grid -> IO ()
solve g = do cls
             putGrid g
             solve' (solutionSpace g) bones

solve' :: [GridBoneLocation] -> [Bone] -> IO ()
solve' g b | length b == 0 = putStr "gesolved ouwe!\n"
           | otherwise     = solve' newBoard newBones
                         where
                            newBoard = emptyLocations g (filledLocations g b)
                            newBones = remainingBones g b

main :: IO ()
main = do hSetBuffering stdout NoBuffering -- disable buffering, necessary to do some tricks
          solve board1

--IO GEBEUREN

putGrid :: Grid -> IO()
putGrid = putStrLn . insert17CharNewLine . concat . concat . map showRow

showRow :: [Int] -> [String]
showRow = interleave space . map show
          where
             space = " "

insert17CharNewLine :: [Char] -> [Char]
insert17CharNewLine xs | length xs < 8 = xs
                      | otherwise     = take 17 xs ++ "\n" ++ insert17CharNewLine (drop 17 xs)

interleave :: a -> [a] -> [a]
interleave x [] = []
interleave x [y] = [y]
interleave x (y:ys) = y : x : interleave x ys
-- 