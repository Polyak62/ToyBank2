package entity;

public class Request {
        private int money;
        private TypeRequest typeRequest;
       // private Client client;
        private String numberRequest;

        public Request(int money, TypeRequest typeRequest, String numberRequest) {
            this.money = money;
            this.typeRequest = typeRequest;
            this.numberRequest = numberRequest;
        }


        public int getMoney() {
            return money;
        }

        public TypeRequest getTypeRequest() {
            return typeRequest;
        }


    @Override
    public String toString() {
        return "Request{" +
                "money=" + money +
                ", typeRequest=" + typeRequest +
                ", numberRequest='" + numberRequest + '\'' +
                '}';
    }
}
