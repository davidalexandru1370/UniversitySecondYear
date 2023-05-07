import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  constructor(private httpClient: HttpClient) {}
  baseUrl = 'http://localhost:8000/';

  getAllDocuments() {
    const header = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
    const url = this.baseUrl + `showAllDocuments.php`;
    return this.httpClient.get(url, header);
  }
}
