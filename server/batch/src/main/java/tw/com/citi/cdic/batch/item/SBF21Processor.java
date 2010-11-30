package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A73;
import tw.com.citi.cdic.batch.model.SBF21Output;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBF21Processor implements ItemProcessor<A73, SBF21Output> {

    private int writeSampleFrequency = 1000;

    private int b73Count = 0;

    private int c73Count = 0;

    @Override
    public SBF21Output process(A73 item) throws Exception {
        SBF21Output out = null;
        String srNo = item == null ? " " : item.getSrNo();
        String ccyCode = item == null ? "   " : item.getCurrencyCode();
        if ("TWD".equals(ccyCode)) {
            out = createA73(item);
        } else {
            if ("8".equals(srNo.substring(0, 1))) {
                out = createC73(item);
            } else {
                out = createB73(item);
            }
        }
        return out;
    }

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

    private SBF21Output createA73(A73 item) {
        SBF21Output out = new SBF21Output();
        out.setType(SBF21Output.TYPE.A73);
        out.setRecord(item);
        return out;
    }

    private SBF21Output createB73(A73 item) {
        SBF21Output out = new SBF21Output();
        out.setType(SBF21Output.TYPE.B73);
        out.setRecord(item);
        b73Count++;
        if (b73Count % writeSampleFrequency == 1) {
            out.setWriteSample(true);
        } else {
            out.setWriteSample(false);
        }
        return out;
    }

    private SBF21Output createC73(A73 item) {
        SBF21Output out = new SBF21Output();
        out.setType(SBF21Output.TYPE.C73);
        out.setRecord(item);
        c73Count++;
        if (c73Count % writeSampleFrequency == 1) {
            out.setWriteSample(true);
        } else {
            out.setWriteSample(false);
        }
        return out;
    }

}
