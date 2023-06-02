import java.io.*;
import java.math.BigDecimal;

public class Matrix implements Serializable {
    private BigDecimal[][] body;

    public Matrix(BigDecimal[][] body) {
        this.body = body.clone();
    }

    public Matrix(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int size = Integer.parseInt(reader.readLine());
            body = new BigDecimal[size][size];
            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    body[i][j] = new BigDecimal(reader.readLine());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BigDecimal[] cutoutElement(BigDecimal[] arr, int elem) {
        BigDecimal[] result = new BigDecimal[arr.length - 1];
        System.arraycopy(arr, 0, result, 0, elem);
        System.arraycopy(arr, elem + 1, result, elem, arr.length - elem - 1);
        return result;
    }

    public Matrix cutoutElement(int x, int y) {
        BigDecimal[][] result = new BigDecimal[body.length - 1][body.length - 1];
        for(int i = 0; i < y; ++i) {
            result[i] = cutoutElement(body[i], x);
        }
        for(int i = y + 1; i < body.length; ++i) {
            result[i - 1] = cutoutElement(body[i], x);
        }
        return new Matrix(result);
    }

    public BigDecimal findDet() {
        if(body.length == 1) {
            return body[0][0];
        }
        BigDecimal result = new BigDecimal(0);
        for(int i = 0; i < body.length; ++i) {
            BigDecimal coef = body[i][0];
            if(i % 2 != 0) {
                coef = coef.negate();
            }
            result = result.add(coef.multiply(cutoutElement(0, i).findDet()));
        }
        return result;
    }

    public BigDecimal get(int x, int y) {
        return body[y][x];
    }

    public int getSize() {
        return body.length;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < body.length; ++i) {
            for(int j = 0; j < body.length; ++j) {
                result.append(body[i][j]).append("  ");
            }
            result.append('\n');
        }
        return result.toString();
    }
}
