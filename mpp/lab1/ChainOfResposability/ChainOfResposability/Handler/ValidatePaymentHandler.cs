namespace ChainOfResposability.Handler;

public class ValidatePaymentHandler : Handler
{
    public override bool handle(String cardNumber)
    {
        if (cardNumber == "1234")
        {
            Console.WriteLine(String.Format("Validation succesfully ✅"));
            return checkNext(cardNumber);
        }
        else
        {
            throw new Exception();
        }
    }
}