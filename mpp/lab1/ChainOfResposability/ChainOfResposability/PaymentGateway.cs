namespace ChainOfResposability;
public class PaymentGateway
{
    private Handler.Handler _handler;

    public PaymentGateway(Handler.Handler handler)
    {
        _handler = handler;
    }

    public void makePayment(string cardNumber)
    {
        _handler.handle(cardNumber);
    }
}