import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Document } from 'src/model/Document';

const baseUrl = 'http://localhost:5000/api/';
const documentController = 'document/';

@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  constructor(private httpClient: HttpClient) {}
  header = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Access-Control-Allow-Origin': '*',
    }),
  };

  getAllDocuments = (
    type: string = '',
    format: string = ''
  ): Observable<Document[]> => {
    return this.httpClient.get<Document[]>(
      baseUrl + documentController + 'get-all-documents',
      this.header
    );
  };
}
