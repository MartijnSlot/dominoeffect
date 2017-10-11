import Data.List
import Data.Maybe
import System.IO
import Control.Monad

type Bone               = ((Int, Int), Int)
type Grid               = [[Int]]

cls :: IO ()
cls = putStr "\ESC[2J"

width :: Int
width = 8

height :: Int
height = 7

inp :: [Int]
inp = [5,4,3,6,5,3,4,6,0,6,0,1,2,3,1,1,3,2,6,5,0,4,2,0,5,3,6,2,3,2,0,6,4,0,4,1,0,0,4,1,5,2,2,4,4,1,6,5,5,5,3,6,1,2,3,1]

out :: [Int]
out = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

st :: [Bone]
st = [((0,0),1), ((1,1),8), ((2,3),15), ((3,6),22),
      ((0,1),2), ((1,2),9), ((2,4),16), ((4,4),23),
      ((0,2),3), ((1,3),10), ((2,5),17), ((4,5),24),
      ((0,3),4), ((1,4),11), ((2,6),18), ((4,6),25),
      ((0,4),5), ((1,5),12), ((3,3),19), ((5,5),26),
      ((0,5),6), ((1,6),13), ((3,4),20), ((5,6),27),
      ((0,6),7), ((2,2),14), ((3,5),21), ((6,6),28)]

findFree :: [Int] -> Int -- find next free spot on resultmatrix
findFree = findFree' 0

findFree' :: Int -> [Int] -> Int
findFree' n (x:xs) | xs == []  = (-1)
                   | x == 0    = n
                   | otherwise = findFree' (n+1) xs

findBoneValue :: (Int,Int) -> [Bone] -> Int --for a location on the board, find the corresponding bone value
findBoneValue (a,b) []                                                          = 0
findBoneValue (a,b) (((p, q), r):xs) | (a == p && b == q) || (a == q && b == p) = r
                                     | otherwise                                = findBoneValue (a,b) xs

matchBone :: (Int,Int) -> Bone -> Bool -- matches a position to a bone
matchBone (a,b) ((p,q),_) = (a == p && b == q) || (a == q && b == p)

matchBoneValue :: Int -> Bone -> Bool -- matches an int to a bone
matchBoneValue n ((_,_),p) = n == p

getValue :: (Int, Int) -> [Int] -> (Int, Int) -- translate 'free locations' to values from inputboard
getValue (a,b) xs = ((xs !! a), (xs !! b))

getBoneValue :: Bone -> Int
getBoneValue ((a,b),c) = c

removeBone :: Int -> [Bone] -> [Bone] -- remove bones from bonelist
removeBone n xs = [a | a <- xs, snd a /= n]

replaceBone :: Int -> Int -> [Int] -> [Int] -- replace bone on board
replaceBone index newVal (x:xs) | index == 0 = newVal:xs
                                | otherwise = x:replaceBone (index - 1) newVal xs

moves :: Int -> [Int] -> [(Int,Int)] -- get all moves for a position (side/bottom of board)
moves n xs | (n + 1) `mod` width == 0            = [(n, (n + width))]
           | (n + 1) + width >= length xs        = [(n, (n + 1))]
           | otherwise                           = [(n, (n + 1)), (n, (n + width))]

chop :: Int -> [a] -> [[a]] --for visual representation
chop n [] = []
chop n xs = take n xs : chop n (drop n xs)

solve :: [Int] -> [Int] -> [Bone] -> [Int]
solve inp out []                                       = out
solve inp out st  | boneValue1 /= 0 && boneValue2 /= 0 = solve inp replace1 (removeBone boneValue1 st) ++ solve inp replace2 (removeBone boneValue2 st)
                  | boneValue1 /= 0                    = solve inp replace1 (removeBone boneValue1 st)
                  | otherwise                          = []
                  where
                     replace1   = replaceBone (snd (head freemoves)) boneValue1 (replaceBone (fst (head freemoves)) boneValue1 out)
                     replace2   = replaceBone (snd (last freemoves)) boneValue2 (replaceBone (fst (last freemoves)) boneValue2 out) 
                     boneValue1 = if length freemoves < 1 then 0 else findBoneValue (getValue (head freemoves) inp) st
                     boneValue2 = if length freemoves < 2 then 0 else findBoneValue (getValue (last freemoves) inp) st
                     freemoves  = moves (findFree out) out

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