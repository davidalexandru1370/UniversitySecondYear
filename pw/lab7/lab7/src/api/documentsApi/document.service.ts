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

  getAllDocuments(): Observable<Document[]> {
    const header = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
    const url = this.baseUrl + `showAllDocuments.php`;
    return this.httpClient.get<Document[]>(url, header);
  }
}
