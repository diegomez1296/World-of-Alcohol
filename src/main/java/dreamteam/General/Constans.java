package dreamteam.General;

import java.text.DecimalFormat;

public class Constans {

    public static final String APP_URL = "http://127.0.0.1:8080";
    public static final int AMOUNT_ALCOHOL_IN_ROW = 4;

    public static String priceFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }
}
