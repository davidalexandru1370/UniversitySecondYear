import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentService } from 'src/api/documentsApi/document.service';
import { Document } from 'src/model/document';

@Component({
  selector: 'app-show-documents',
  templateUrl: './show-documents.component.html',
  styleUrls: ['./show-documents.component.css'],
})
export class ShowDocumentsComponent implements OnInit {
  documents: Document[] = [];

  constructor(
    private documentService: DocumentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.documentService.getAllDocuments().subscribe((documents) => {
      this.documents = documents;
    });
  }

  handleDeleteButton(documentId: number): void {
    this.router.navigate(['deleteDocument'], {
      queryParams: { id: documentId },
    });
  }
  title = 'All documents';
}
