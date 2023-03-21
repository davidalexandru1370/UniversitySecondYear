namespace ChainOfResposability.Handler;

public abstract class Handler
{
    private Handler next = null;


    public Handler link(Handler handler)
    {
        next = handler;
        return next;
    }

    public abstract bool handle(string cardNumber);

    public bool checkNext(string cardNumber)
    {
        if (next is null)
        {
            return true;
        }

        return next.handle(cardNumber);
    }
}