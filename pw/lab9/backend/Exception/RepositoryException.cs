namespace backend.Exception;

public class RepositoryException : System.Exception
{
    public RepositoryException(string? message) : base(message)
    {
    }

    public static void ThrowIfNull(object? obj)
    {
        if (obj is null)
        {
            throw new RepositoryException($"{nameof(obj)} is null");
        }
    }
}