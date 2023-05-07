import { Component, OnInit } from '@angular/core';
import { DocumentService } from 'src/api/documentsApi/document.service';
import { Document } from 'src/model/document';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  documents: Document[] = [];

  constructor(private documentService: DocumentService) {}

  ngOnInit(): void {
    this.documentService.getAllDocuments().subscribe((documents) => {
      console.log(documents);
      this.documents = documents;
    });
  }
  title = 'All documents';
}
