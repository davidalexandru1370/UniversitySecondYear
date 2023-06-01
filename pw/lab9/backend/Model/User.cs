using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backend.Model;

[Table("User")]
public class User
{
    [Key]
    [Column(TypeName = "varchar(100)")]
    [Required]
    public string Name { get; set; } = null!;

    [Column(TypeName = "varchar(100)")]
    [Required]
    public string Password { get; set; } = null!;
}