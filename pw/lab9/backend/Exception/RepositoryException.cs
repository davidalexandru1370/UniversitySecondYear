namespace backend.Exception;

public class RepositoryException : System.Exception
{
    public RepositoryException(string? message) : base(message)
    {
    }

    public static void ThrowIfNull(object? obj, string message = "")
    {
        if (obj is null)
        {
            throw new RepositoryException(message == "" ? $"{nameof(obj)} is null" : message);
        }
    }
}