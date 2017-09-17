package um.model;

public class DistributionPartnerContract {

    private String partner;
    private String usage;

    public DistributionPartnerContract(String partner, String usage) {
        this.partner = partner;
        this.usage = usage;
    }

    public String getPartner() {
        return partner;
    }

    public String getUsage() {
        return usage;
    }

    @Override
    public String toString() {
        return "DistributionPartnerContract{" +
                "partner='" + partner + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
