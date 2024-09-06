package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartRequest extends BaseRequest {
    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("stake")
    private Double stake;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }
}
