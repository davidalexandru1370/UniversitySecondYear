namespace ChainOfResposability.Handler;

public class AmountValidation : Handler
{


    public override bool handle(string cardNumber)
    {
        if (hasAmount(cardNumber))
        {
            return this.checkNext(cardNumber);
        }

        throw new Exception();
    }

    private bool hasAmount(string cardNumber)
    {
        if (cardNumber == "1234")
        {
            Console.WriteLine(String.Format("{0} has sufficient amount",cardNumber));
            return true;
        }

        return false;
    }
}