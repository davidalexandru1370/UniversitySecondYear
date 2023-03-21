using ChainOfResposability;
using ChainOfResposability.Handler;

namespace Program
{
    public static class main
    {
        public static void Main()
        {
            ValidatePaymentHandler validatePaymentHandler = new();
            validatePaymentHandler
                .link(new AmountValidation())
                .link(new CardValidationHandler());
            
            PaymentGateway gateway = new PaymentGateway(validatePaymentHandler);
            
            gateway.makePayment("1234");
        }
    }
}

