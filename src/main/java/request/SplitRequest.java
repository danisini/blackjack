package request;

public class SplitRequest extends BaseRequest {
    private Double additionalStake;

    public Double getAdditionalStake() {
        return additionalStake;
    }

    public void setAdditionalStake(Double additionalStake) {
        this.additionalStake = additionalStake;
    }
}
