class PhoneNumber {
    private String number;
    private PhoneNumberCategory category;

    public PhoneNumber(String number, PhoneNumberCategory category) {
        this.number = number;
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public PhoneNumberCategory getCategory() {
        return category;
    }
}
