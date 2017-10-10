import Data.List
import Data.Maybe
import System.IO

type Bone               = ((Int, Int), Int)
type Grid               = [[Int]]

cls :: IO ()
cls = putStr "\ESC[2J"

width :: Int
width = 8

height :: Int
height = 7

inputboard :: [Int]
inputboard = [5,4,3,6,5,3,4,6,0,6,0,1,2,3,1,1,3,2,6,5,0,4,2,0,5,3,6,2,3,2,0,6,4,0,4,1,0,0,4,1,5,2,2,4,4,1,6,5,5,5,3,6,1,2,3,1]

resultboard :: [Int]
resultboard = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

bones :: [Bone]
bones = [((0,0),1), ((1,1),8), ((2,3),15), ((3,6),22),
         ((0,1),2), ((1,2),9), ((2,4),16), ((4,4),23),
         ((0,2),3), ((1,3),10), ((2,5),17), ((4,5),24),
         ((0,3),4), ((1,4),11), ((2,6),18), ((4,6),25),
         ((0,4),5), ((1,5),12), ((3,3),19), ((5,5),26),
         ((0,5),6), ((1,6),13), ((3,4),20), ((5,6),27),
         ((0,6),7), ((2,2),14), ((3,5),21), ((6,6),28)]

findFree :: [Int] -> Int
findFree = findFree' 0

findFree' :: Int -> [Int] -> Int
findFree' n (x:xs) | x == 0    = n
                   | otherwise = findFree' (n+1) xs

findBoneValue :: Int -> Int -> [Bone] -> Int
findBoneValue a b (((p, q), r):xs) | (a == p && b == q) || (a == q && b == p) = r
                                   | otherwise                                = findBoneValue a b xs

matchBone :: Int -> Int -> Bone -> Bool
matchBone a b ((p,q),_) = (a == p && b == q) || (a == q && b == p)

matchBoneValue :: Int -> Bone -> Bool
matchBoneValue n ((_,_),p) = n == p

removeBone :: Int -> [Bone] -> [Bone] -- remove bones from bonelist
removeBone n xs = [a | a <- xs, snd a /= n]

replaceBone :: Int -> Int -> [Int] -> [Int] -- replace bone on board
replaceBone n newVal x:xs | n == 0 = newVal:xs
                     | otherwise = x:replaceBone (n - 1) newVal xs

moves :: Int -> [Int] -> [(Int,Int)] -- get all moves for a position (side/bottom of board)
moves n xs | (n + 1) `mod` width == 0            = [((xs !! n), (xs !! n + width))]]
           | (n + 1) `div` width == (height + 1) = [((xs !! n), (xs !! n + 1))]
           | otherwise                           = [((xs !! n), (xs !! n + 1)), ((xs !! n), (xs !! n + width))]

chop :: Int -> [a] -> [[a]] --for visual representation
chop n [] = []
chop n xs = take n xs : chop n (drop n xs)