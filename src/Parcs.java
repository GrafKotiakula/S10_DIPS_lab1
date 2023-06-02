import parcs.AM;
import parcs.AMInfo;

import java.math.BigDecimal;

public class Parcs implements AM {

    @Override
    public void run(AMInfo amInfo) {
        Dto dto = (Dto) amInfo.parent.readObject();
        BigDecimal result = BigDecimal.ZERO;
        for(Integer index: dto.getIndexes()) {
            BigDecimal tmp = dto.getMatrix().get(0, index);
            if(index % 2 != 0) {
                tmp = tmp.negate();
            }
            result = result.add(tmp.multiply(dto.getMatrix().cutoutElement(0, index).findDet()));
        }
        amInfo.parent.write(result);
    }
}
