package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Guarantor;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/30
 */
public class GuarantorMapper implements RowMapper<Guarantor> {

    @Override
    public Guarantor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Guarantor item = new Guarantor();
        item.setCustNo(rs.getString("V_CUST_NO"));
        item.setUdf6(rs.getString("V_UDF_6"));
        item.setUdf7(rs.getString("V_UDF_7"));
        item.setUdf8(rs.getString("V_UDF_8"));
        item.setUdf9(rs.getString("V_UDF_9"));
        item.setUdf10(rs.getString("V_UDF_10"));
        item.setUdf11(rs.getDouble("V_UDF_11"));
        item.setUdf12(rs.getString("V_UDF_12"));
        item.setUdf13(rs.getString("V_UDF_13"));
        item.setUdf14(rs.getString("V_UDF_14"));
        item.setUdf15(rs.getString("V_UDF_15"));
        item.setUdf16(rs.getString("V_UDF_16"));
        item.setUdf17(rs.getString("V_UDF_17"));
        item.setUdf18(rs.getDouble("V_UDF_18"));
        item.setUdf19(rs.getString("V_UDF_19"));
        item.setUdf20(rs.getString("V_UDF_20"));
        item.setUdf21(rs.getString("V_UDF_21"));
        item.setUdf22(rs.getString("V_UDF_22"));
        item.setUdf23(rs.getString("V_UDF_23"));
        item.setUdf24(rs.getString("V_UDF_24"));
        item.setUdf25(rs.getDouble("V_UDF_25"));
        item.setUdf26(rs.getString("V_UDF_26"));
        item.setUdf27(rs.getString("V_UDF_27"));
        item.setUdf28(rs.getString("V_UDF_28"));
        item.setUdf29(rs.getString("V_UDF_29"));
        item.setUdf30(rs.getString("V_UDF_30"));
        item.setUdf31(rs.getString("V_UDF_31"));
        item.setUdf32(rs.getDouble("V_UDF_32"));
        item.setUdf33(rs.getString("V_UDF_33"));
        item.setUdf34(rs.getString("V_UDF_34"));
        item.setUdf35(rs.getString("V_UDF_35"));
        item.setUdf36(rs.getString("V_UDF_36"));
        item.setUdf37(rs.getString("V_UDF_37"));
        item.setUdf38(rs.getString("V_UDF_38"));
        item.setUdf39(rs.getDouble("V_UDF_39"));
        item.setUdf40(rs.getString("V_UDF_40"));
        item.setUdf41(rs.getString("V_UDF_41"));
        item.setUdf42(rs.getString("V_UDF_42"));
        item.setUdf43(rs.getString("V_UDF_43"));
        item.setUdf44(rs.getString("V_UDF_44"));
        item.setUdf45(rs.getString("V_UDF_45"));
        item.setUdf46(rs.getDouble("V_UDF_46"));
        item.setUdf47(rs.getString("V_UDF_47"));
        item.setUdf48(rs.getString("V_UDF_48"));
        item.setUdf49(rs.getString("V_UDF_49"));
        item.setUdf51(rs.getString("V_UDF_51"));
        item.setUdf52(rs.getString("V_UDF_52"));
        item.setUdf53(rs.getString("V_UDF_53"));
        item.setUdf54(rs.getDouble("V_UDF_54"));
        item.setUdf55(rs.getString("V_UDF_55"));
        item.setUdf56(rs.getString("V_UDF_56"));
        item.setUdf57(rs.getString("V_UDF_57"));
        item.setUdf58(rs.getString("V_UDF_58"));
        item.setUdf59(rs.getString("V_UDF_59"));
        item.setUdf60(rs.getString("V_UDF_60"));
        item.setUdf61(rs.getDouble("V_UDF_61"));
        item.setUdf62(rs.getString("V_UDF_62"));
        item.setUdf63(rs.getString("V_UDF_63"));
        item.setUdf64(rs.getString("V_UDF_64"));
        item.setUdf65(rs.getString("V_UDF_65"));
        item.setUdf66(rs.getString("V_UDF_66"));
        item.setUdf67(rs.getString("V_UDF_67"));
        item.setUdf68(rs.getDouble("V_UDF_68"));
        item.setUdf69(rs.getString("V_UDF_69"));
        item.setUdf70(rs.getString("V_UDF_70"));
        item.setUdf71(rs.getString("V_UDF_71"));
        item.setUdf72(rs.getString("V_UDF_72"));
        item.setUdf73(rs.getString("V_UDF_73"));
        item.setUdf74(rs.getString("V_UDF_74"));
        item.setUdf75(rs.getDouble("V_UDF_75"));
        item.setUdf76(rs.getString("V_UDF_76"));
        item.setUdf77(rs.getString("V_UDF_77"));
        item.setUdf78(rs.getString("V_UDF_78"));
        item.setUdf79(rs.getString("V_UDF_79"));
        item.setUdf80(rs.getString("V_UDF_80"));
        item.setUdf81(rs.getString("V_UDF_81"));
        item.setUdf82(rs.getDouble("V_UDF_82"));
        item.setUdf83(rs.getString("V_UDF_83"));
        item.setUdf84(rs.getString("V_UDF_84"));
        item.setUdf85(rs.getString("V_UDF_85"));
        item.setUdf86(rs.getString("V_UDF_86"));
        item.setUdf87(rs.getString("V_UDF_87"));
        item.setUdf88(rs.getString("V_UDF_88"));
        item.setUdf89(rs.getDouble("V_UDF_89"));
        item.setUdf90(rs.getString("V_UDF_90"));
        item.setUdf91(rs.getString("V_UDF_91"));
        item.setUdf92(rs.getString("V_UDF_92"));
        item.setUdf93(rs.getString("V_UDF_93"));
        item.setUdf94(rs.getString("V_UDF_94"));
        item.setUdf95(rs.getString("V_UDF_95"));
        item.setUdf96(rs.getDouble("V_UDF_96"));
        item.setUdf97(rs.getString("V_UDF_97"));
        item.setUdf98(rs.getString("V_UDF_98"));
        item.setUdf99(rs.getString("V_UDF_99"));
        item.setUdf100(rs.getString("V_UDF_100"));
        item.setUdf101(rs.getString("V_UDF_101"));
        item.setUdf102(rs.getString("V_UDF_102"));
        item.setUdf103(rs.getDouble("V_UDF_103"));
        item.setUdf104(rs.getString("V_UDF_104"));
        item.setUdf105(rs.getString("V_UDF_105"));
        item.setUdf106(rs.getString("V_UDF_106"));
        item.setUdf107(rs.getString("V_UDF_107"));
        item.setUdf108(rs.getString("V_UDF_108"));
        item.setUdf109(rs.getString("V_UDF_109"));
        item.setUdf110(rs.getDouble("V_UDF_110"));
        item.setUdf111(rs.getString("V_UDF_111"));
        return item;
    }

}
