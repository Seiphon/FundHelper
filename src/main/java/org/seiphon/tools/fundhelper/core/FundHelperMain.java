package org.seiphon.tools.fundhelper.core;

import java.io.IOException;

public class FundHelperMain {
    public static void main(String[] args) {

        try {
//            String result = GetFundDataUtil.getDetailByFundCode("001538");
            String result = GetStockDataUtil

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
