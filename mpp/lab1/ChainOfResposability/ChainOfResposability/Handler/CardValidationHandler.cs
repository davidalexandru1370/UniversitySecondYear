namespace ChainOfResposability.Handler;

public class CardValidationHandler : Handler
{
    private readonly int CARD_LENGTH = 4;


    public override bool handle(string cardNumber)
    {
        if (checkLength(cardNumber))
        {
            Console.WriteLine("card {0} is valid",cardNumber);
            return this.checkNext(cardNumber);
        }

        throw new Exception();
    }


    private bool checkLength(string cardNumber)
    {
        if (cardNumber.Length == CARD_LENGTH)
        {
            return true;
        }

        return false;
    }
}