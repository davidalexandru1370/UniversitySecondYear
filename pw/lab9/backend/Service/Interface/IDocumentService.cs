using backend.Model;

namespace backend.Service.Interface;

public interface IDocumentService
{
    Task<Document> AddDocumentAsync(Document document);
    Task DeleteDocumentAsync(Guid documentId);
    Task<Document> UpdateDocumentAsync(Document document);
    Task<IEnumerable<Document>> GetAllDocuments();
}