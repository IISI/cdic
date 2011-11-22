package tw.com.citi.cdic.batch.item.file.mapping;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import tw.com.citi.cdic.batch.model.Guarantor;

/**
 * @author Chih-Liang Chang
 * @since 2011/10/3
 */
public class GuarantorFieldSetMapper implements FieldSetMapper<Guarantor> {

    @Override
    public Guarantor mapFieldSet(FieldSet fieldSet) throws BindException {
        Guarantor item = new Guarantor();
        item.setCustNo(fieldSet.readString("custNo"));
        item.setUdf6(fieldSet.readString("udf6"));
        item.setUdf7(fieldSet.readString("udf7"));
        item.setUdf8(fieldSet.readString("udf8"));
        item.setUdf9(customerNoTransformer(fieldSet.readString("udf9")));
        item.setUdf10(fieldSet.readString("udf10"));
        item.setUdf11(Double.valueOf(fieldSet.readString("udf11")));
        item.setUdf12(fieldSet.readString("udf12"));
        item.setUdf13(fieldSet.readString("udf13"));
        item.setUdf14(fieldSet.readString("udf14"));
        item.setUdf15(customerNoTransformer(fieldSet.readString("udf15")));
        item.setUdf16(fieldSet.readString("udf16"));
        item.setUdf17(fieldSet.readString("udf17"));
        item.setUdf18(Double.valueOf(fieldSet.readString("udf18")));
        item.setUdf19(fieldSet.readString("udf19"));
        item.setUdf20(fieldSet.readString("udf20"));
        item.setUdf21(fieldSet.readString("udf21"));
        item.setUdf22(customerNoTransformer(fieldSet.readString("udf22")));
        item.setUdf23(fieldSet.readString("udf23"));
        item.setUdf24(fieldSet.readString("udf24"));
        item.setUdf25(Double.valueOf(fieldSet.readString("udf25")));
        item.setUdf26(fieldSet.readString("udf26"));
        item.setUdf27(fieldSet.readString("udf27"));
        item.setUdf28(fieldSet.readString("udf28"));
        item.setUdf29(customerNoTransformer(fieldSet.readString("udf29")));
        item.setUdf30(fieldSet.readString("udf30"));
        item.setUdf31(fieldSet.readString("udf31"));
        item.setUdf32(Double.valueOf(fieldSet.readString("udf32")));
        item.setUdf33(fieldSet.readString("udf33"));
        item.setUdf34(fieldSet.readString("udf34"));
        item.setUdf35(fieldSet.readString("udf35"));
        item.setUdf36(customerNoTransformer(fieldSet.readString("udf36")));
        item.setUdf37(fieldSet.readString("udf37"));
        item.setUdf38(fieldSet.readString("udf38"));
        item.setUdf39(Double.valueOf(fieldSet.readString("udf39")));
        item.setUdf40(fieldSet.readString("udf40"));
        item.setUdf41(fieldSet.readString("udf41"));
        item.setUdf42(fieldSet.readString("udf42"));
        item.setUdf43(customerNoTransformer(fieldSet.readString("udf43")));
        item.setUdf44(fieldSet.readString("udf44"));
        item.setUdf45(fieldSet.readString("udf45"));
        item.setUdf46(Double.valueOf(fieldSet.readString("udf46")));
        item.setUdf47(fieldSet.readString("udf47"));
        item.setUdf48(fieldSet.readString("udf48"));
        item.setUdf49(fieldSet.readString("udf49"));
        item.setUdf51(customerNoTransformer(fieldSet.readString("udf51")));
        item.setUdf52(fieldSet.readString("udf52"));
        item.setUdf53(fieldSet.readString("udf53"));
        item.setUdf54(Double.valueOf(fieldSet.readString("udf54")));
        item.setUdf55(fieldSet.readString("udf55"));
        item.setUdf56(fieldSet.readString("udf56"));
        item.setUdf57(fieldSet.readString("udf57"));
        item.setUdf58(customerNoTransformer(fieldSet.readString("udf58")));
        item.setUdf59(fieldSet.readString("udf59"));
        item.setUdf60(fieldSet.readString("udf60"));
        item.setUdf61(Double.valueOf(fieldSet.readString("udf61")));
        item.setUdf62(fieldSet.readString("udf62"));
        item.setUdf63(fieldSet.readString("udf63"));
        item.setUdf64(fieldSet.readString("udf64"));
        item.setUdf65(customerNoTransformer(fieldSet.readString("udf65")));
        item.setUdf66(fieldSet.readString("udf66"));
        item.setUdf67(fieldSet.readString("udf67"));
        item.setUdf68(Double.valueOf(fieldSet.readString("udf68")));
        item.setUdf69(fieldSet.readString("udf69"));
        item.setUdf70(fieldSet.readString("udf70"));
        item.setUdf71(fieldSet.readString("udf71"));
        item.setUdf72(customerNoTransformer(fieldSet.readString("udf72")));
        item.setUdf73(fieldSet.readString("udf73"));
        item.setUdf74(fieldSet.readString("udf74"));
        item.setUdf75(Double.valueOf(fieldSet.readString("udf75")));
        item.setUdf76(fieldSet.readString("udf76"));
        item.setUdf77(fieldSet.readString("udf77"));
        item.setUdf78(fieldSet.readString("udf78"));
        item.setUdf79(customerNoTransformer(fieldSet.readString("udf79")));
        item.setUdf80(fieldSet.readString("udf80"));
        item.setUdf81(fieldSet.readString("udf81"));
        item.setUdf82(Double.valueOf(fieldSet.readString("udf82")));
        item.setUdf83(fieldSet.readString("udf83"));
        item.setUdf84(fieldSet.readString("udf84"));
        item.setUdf85(fieldSet.readString("udf85"));
        item.setUdf86(customerNoTransformer(fieldSet.readString("udf86")));
        item.setUdf87(fieldSet.readString("udf87"));
        item.setUdf88(fieldSet.readString("udf88"));
        item.setUdf89(Double.valueOf(fieldSet.readString("udf89")));
        item.setUdf90(fieldSet.readString("udf90"));
        item.setUdf91(fieldSet.readString("udf91"));
        item.setUdf92(fieldSet.readString("udf92"));
        item.setUdf93(customerNoTransformer(fieldSet.readString("udf93")));
        item.setUdf94(fieldSet.readString("udf94"));
        item.setUdf95(fieldSet.readString("udf95"));
        item.setUdf96(Double.valueOf(fieldSet.readString("udf96")));
        item.setUdf97(fieldSet.readString("udf97"));
        item.setUdf98(fieldSet.readString("udf98"));
        item.setUdf99(fieldSet.readString("udf99"));
        item.setUdf100(customerNoTransformer(fieldSet.readString("udf100")));
        item.setUdf101(fieldSet.readString("udf101"));
        item.setUdf102(fieldSet.readString("udf102"));
        item.setUdf103(Double.valueOf(fieldSet.readString("udf103")));
        item.setUdf104(fieldSet.readString("udf104"));
        item.setUdf105(fieldSet.readString("udf105"));
        item.setUdf106(fieldSet.readString("udf106"));
        item.setUdf107(customerNoTransformer(fieldSet.readString("udf107")));
        item.setUdf108(fieldSet.readString("udf108"));
        item.setUdf109(fieldSet.readString("udf109"));
        item.setUdf110(Double.valueOf(fieldSet.readString("udf110")));
        item.setUdf111(fieldSet.readString("udf111"));
        return item;
    }

    private String customerNoTransformer(String customerNo) {
        if (customerNo == null) {
            return null;
        }
        if ("".equals(customerNo.trim())) {
            return null;
        }
        try {
            Integer.parseInt(customerNo.trim());
        } catch (NumberFormatException e) {
            return null;
        }
        customerNo = StringUtils.rightPad(customerNo, 9, "0");
        return customerNo;
    }

}
