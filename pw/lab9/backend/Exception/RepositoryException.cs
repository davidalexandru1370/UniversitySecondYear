namespace backend.Exception;

public class RepositoryException : System.Exception
{
    public RepositoryException(string? message) : base(message)
    {
    }
}