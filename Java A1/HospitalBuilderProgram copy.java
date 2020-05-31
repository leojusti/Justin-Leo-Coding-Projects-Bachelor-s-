import java.util.Arrays;

/**
 * Created by justinleo on 2017-01-14.
 */
public class HospitalBuilderProgram {


    public static void main(String[] args) {
        int[][][] maps = {
                {
                        {2, 2},
                        {2, 8},
                        {5, 15},
                        {19, 1},
                        {17, 17}
                },

                {{1, 1}, {7, 19}, {13, 19}, {19, 7}, {19, 13}},
                {{0, 19}, {2, 19}, {4, 19}, {6, 19}, {18, 19}},
                {{7, 19}, {13, 19}, {19, 19}, {19, 13}, {19, 7}}
        };

        int mapCount = 4;
        int townCount = 5;
        int x2 = 0;
        int y2 = 0;
        int gridSize = 20;

        double sum = 0;
        double distance = 0;

        for (int i = 0; i < mapCount; i++) {

            //initial assumptions
            double minDistance = Double.MAX_VALUE;
            int minGridx = 0;
            int minGridY = 0;

            for (int x1 = 0; x1 < gridSize; x1++) {

                for (int y1 = 0; y1 < gridSize; y1++) {

                    double[] distanceArray = new double[townCount];

                    for (int j = 0; j < townCount; j++) {

                        distance = 0.0;
                        x2 = maps[i][j][0];
                        y2 = maps[i][j][1];

                        distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                        distanceArray[j] = distance;

                    }

                    Arrays.sort(distanceArray);
                    double max = distanceArray[4]; //current lowest

                    if (max < minDistance) {
                        minDistance = max;
                        minGridx = x1;
                        minGridY = y1;
                    }
                }
            }

            System.out.println("For map " + (i+1) + " hospital could be located at grid (" + minGridx + "," + minGridY +")");
        }
    }
}
//^ Method to find square with lowest max distance to an individual town