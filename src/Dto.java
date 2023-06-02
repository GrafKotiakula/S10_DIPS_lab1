import java.io.Serializable;
import java.util.List;

public class Dto implements Serializable {
    private final List<Integer> indexes;
    private final Matrix matrix;

    public Dto(List<Integer> indexes, Matrix matrix) {
        this.indexes = indexes;
        this.matrix = matrix;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
