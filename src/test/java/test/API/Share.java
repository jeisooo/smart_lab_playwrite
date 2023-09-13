package test.API;
import com.google.gson.annotations.SerializedName;
public class Share {
    @SerializedName("Id")
    private int id;

    @SerializedName("Ticker")
    private String ticker;

    @SerializedName("Price")
    private Integer price;
}
