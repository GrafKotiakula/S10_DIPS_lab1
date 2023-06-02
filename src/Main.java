import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(new File("data/matrix-1.txt"));
        System.out.println(m.findDet());

//        System.out.println(List.of(m.cutoutElement(new BigDecimal[]{
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(3),
//                BigDecimal.valueOf(4),
//                BigDecimal.valueOf(5)
//        }, 7)));
//        BigDecimal d = new BigDecimal("12345678901234567890123456789012345678901234567890");
//        System.out.println((new BigDecimal(7)).divide(new BigDecimal(9), 10, RoundingMode.HALF_UP));
//        System.out.println(d.add(new BigDecimal(1)));
//        System.out.println(d);
    }
}
