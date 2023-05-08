import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Document } from 'src/model/document';
import { DocumentDto } from 'src/model/documentDto';
@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  constructor(private httpClient: HttpClient) {}
  baseUrl = 'http://localhost:8000/';
  header = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
    }),
  };

  getAllDocuments(
    type: string = '',
    format: string = ''
  ): Observable<Document[]> {
    const url =
      this.baseUrl + `showAllDocuments.php?type=${type}&format=${format}`;
    return this.httpClient.get<Document[]>(url, this.header);
  }

  deleteDocumentById(documentId: number) {
    const url = this.baseUrl + `deleteDocumentScript.php`;
    return this.httpClient.delete(url, {
      headers: this.header.headers,
      body: JSON.stringify({ id: documentId }),
    });
  }

  updateDocument(document: Document) {
    const body: Document = {
      author: 'das',
      format: 'dsadas',
      id: 56,
      numberOfPages: 34,
      title: '32321fs',
      type: 'pdf',
    };
    const url = this.baseUrl + 'updateDocumentScript.php';
    return this.httpClient.post<Document>(url, JSON.stringify(document), {
      headers: this.header.headers,
    });
  }

  addDocument(documentDto: DocumentDto) {
    const url = this.baseUrl + 'addDocument.php';
    return this.httpClient.put(url, JSON.stringify(documentDto), {
      headers: this.header.headers,
    });
  }
}
