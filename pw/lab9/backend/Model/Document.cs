using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backend.Model;

[Table("Document")]
public class Document
{
    [Key] public Guid Id { get; set; }
    public string Author { get; set; } = null!;
    public string Title { get; set; } = null!;
    public int NumberOfPages { get; set; }
    public string Type { get; set; } = null!;
    public string Format { get; set; } = null!;
}