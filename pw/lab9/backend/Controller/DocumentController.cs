using backend.Exception;
using backend.Model;
using backend.Service.Interface;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controller;

[ApiController]
[Route("api/[controller]")]
public class DocumentController : ControllerBase
{
    private readonly IDocumentService _documentService;

    public DocumentController(IDocumentService documentService)
    {
        _documentService = documentService;
    }

    [HttpGet("get-all-documents")]
    public async Task<ActionResult<IEnumerable<Document>>> GetAllDocuments()
    {
        var documents = await _documentService.GetAllDocuments();
        return Ok(documents);
    }

    [HttpPost("add-document")]
    public async Task<ActionResult<Document>> AddDocument(Document document)
    {
        try
        {
            var addedDocument = await _documentService.AddDocumentAsync(document);
            return Ok(addedDocument);
        }
        catch (ArgumentNullException)
        {
            return BadRequest();
        }
    }

    [HttpDelete("delete-document/{documentId}")]
    public async Task<IActionResult> DeleteDocument([FromRoute] Guid documentId)
    {
        try
        {
            await _documentService.DeleteDocumentAsync(documentId);
            return Ok();
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }

    [HttpPut("update-document")]
    public async Task<IActionResult> UpdateDocument([FromBody] Document document)
    {
        try
        {
            await _documentService.UpdateDocumentAsync(document);
            return Ok();
        }
        catch (RepositoryException)
        {
            return BadRequest();
        }
    }
}