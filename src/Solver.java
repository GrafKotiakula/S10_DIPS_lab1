import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    public static void main(String[] args) throws IOException{
        int threadsCount;
        try(BufferedReader reader = new BufferedReader( new FileReader(new File("conf.txt")) )) {
            threadsCount = Integer.parseInt(reader.readLine());
        }
        Matrix matrix = new Matrix(new File("matrix.txt"));
        task curtask = new task();
        curtask.addJarFile("Parcs.jar");
        AMInfo info = new AMInfo(curtask, null);

        long start = System.nanoTime();

        List<List<Integer>> indexes = divideIndexes(threadsCount, matrix.getSize());
        List<channel> channelList = new ArrayList<>();
        for(int i = 0; i < threadsCount; ++i) {
            point p = info.createPoint();
            channel c = p.createChannel();
            channelList.add(c);
            p.execute("Parcs");
            c.write(new Dto(indexes.get(i), matrix));
        }
        BigDecimal result = BigDecimal.ZERO;
        for(int i = 0; i < threadsCount; ++i) {
            result = result.add((BigDecimal) channelList.get(i).readObject());
        }

        long end = System.nanoTime();
        double time = (end - start) / 1e6;
        System.out.println("Determinant is: " + result);
        System.out.println("Time is: " + (time) + "ms");

        curtask.end();

    }

    private static List<List<Integer>> divideIndexes(int threads, int size) {
        int a = size / threads;
        int b = size % threads;

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < threads; ++i) {
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < a; ++j) {
                tmp.add(i * a + j);
            }
            result.add(tmp);
        }

        for(int i = 0; i < b; ++i) {
            result.get(i).add(size - b + i);
        }

        return result;
    }
}
