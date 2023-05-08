import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Document } from 'src/model/document';
@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  constructor(private httpClient: HttpClient) {}
  baseUrl = 'http://localhost:8000/';
  header = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  getAllDocuments(): Observable<Document[]> {
    const url = this.baseUrl + `showAllDocuments.php`;
    return this.httpClient.get<Document[]>(url, this.header);
  }

  deleteDocumentById(documentId: number) {
    const url = this.baseUrl + `deleteDocumentScript.php`;
    return this.httpClient.delete(url, {
      headers: this.header.headers,
      body: JSON.stringify({ id: documentId }),
    });
  }
}
