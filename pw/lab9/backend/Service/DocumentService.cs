using backend.DbContext;
using backend.Exception;
using backend.Model;
using backend.Service.Interface;

namespace backend.Service;

public class DocumentService : IDocumentService
{
    private readonly DocumentDbContext _documentDbContext;

    public DocumentService(DocumentDbContext documentDbContext)
    {
        _documentDbContext = documentDbContext;
    }

    public async Task<Document> AddDocumentAsync(Document document)
    {
        if (document == null) throw new ArgumentNullException(nameof(document));
        var result = await _documentDbContext.AddAsync(document);
        await _documentDbContext.SaveChangesAsync();
        return result.Entity;
    }

    public async Task DeleteDocumentAsync(Guid documentId)
    {
        var document = _documentDbContext.Set<Document>().FirstOrDefault(d => d.Id == documentId);
        _documentDbContext.Set<Document>().Remove(document);
        await _documentDbContext.SaveChangesAsync();
    }

    public async Task<Document> UpdateDocumentAsync(Document document)
    {
        if (document == null) throw new ArgumentNullException(nameof(document));
        var foundDocument = _documentDbContext.Set<Document>().FirstOrDefault(d => d.Id == document.Id);
        if (foundDocument is null)
        {
            throw new RepositoryException("Invalid document");
        }

        _documentDbContext.Entry(foundDocument).CurrentValues.SetValues(document);
        await _documentDbContext.SaveChangesAsync();
        return foundDocument;
    }

    public Task<IEnumerable<Document>> GetAllDocuments()
    {
        var documents = _documentDbContext.Set<Document>().ToList() as IEnumerable<Document>;
        return Task.FromResult(documents);
    }
}