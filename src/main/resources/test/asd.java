package test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class asd {


    public static void main(String[] args) {
        List<Integer> hill = List.of(0, 1, 2, 3, 7, 3, 5, 7, 4, 2, 7, 1, 0, 1, 2, 3, 7, 3, 5, 7, 4, 2, 7, 1);
        testA(hill, 6);
        testB(hill);

    }

    public static int testA(List<Integer> slope, int i) {
        var left = countToLeft(slope, i);
        var right = countToRight(slope, i);

        if (left < right) {
            return right;
        } else {
            return left;
        }
    }

    public static int testB(List<Integer> slope) {
        int highestTop = 0;
        int longestSlope = 0;

        slope.forEach(s -> {

        });


        for (int i = 0; i < slope.size(); i++) {


            int left = countToLeft(slope, i);
            int right = countToRight(slope, i);
            int biggestNumber = (left > right) ? left : right;
            System.out.println(" .." + biggestNumber);

            if (longestSlope < biggestNumber) {
                longestSlope = biggestNumber;
                highestTop = i;
            }

        }
        return 1;

    }


    public int optimalStartingPoint(List<Integer> slope) {


        int higestTop = 0;
        int longestSlope = 0;

        for (int i = 0; i < slope.size(); i++) {

            int right = countToRight(slope, i);
            int left = countToLeft(slope, i);
            int longestSide = Math.max(right, left);

            if (longestSlope > longestSide) {
                longestSlope = longestSide;
                higestTop = i;

            }

        }

        return higestTop;


    }


    public static int countToLeft(List<Integer> hill, int startPosition) {


        int totalDistanceToReturn = 0;
        int startPositionValue = hill.get(startPosition);
        boolean first = true;

        for (int i = startPosition - 1; i >= 0; i--) {
            if (startPositionValue > hill.get(i)) {
                if (first) {
                    totalDistanceToReturn = startPositionValue;
                    first = false;
                }
                totalDistanceToReturn = totalDistanceToReturn + hill.get(i);
                startPositionValue = hill.get(i);
            } else {
                break;
            }
        }
        return totalDistanceToReturn;
    }

    public static int countToRight(List<Integer> hill, int startPosition) {
        int totalDistanceToReturn = 0;
        int startPositionValue = hill.get(startPosition);

        boolean first = true;

        for (int i = startPosition + 1; i < hill.size(); i++) {

            if (startPositionValue > hill.get(i)) {
                if (first) {
                    totalDistanceToReturn = startPositionValue;
                    first = false;
                }
                totalDistanceToReturn += hill.get(i);
                startPositionValue = hill.get(i);
            } else {
                break;
            }
        }
        return totalDistanceToReturn;
    }
}