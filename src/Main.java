import models.Congresso;

public class Main {
    public static void main(String[] args) {

        Congresso cbpoo = Congresso.getInstance();

        cbpoo.inscreverParticipanteMock();

    }
}