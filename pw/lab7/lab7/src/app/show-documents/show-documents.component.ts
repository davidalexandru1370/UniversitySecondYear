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

  handleUpdateButton(documentId: number, document: Document): void {
    this.router.navigate(['updateDocument'], {
      queryParams: { id: documentId },
      state: {
        document: document,
      },
    });
  }

  refetch(type: string = '', format: string = '') {
    this.documentService
      .getAllDocuments(type, format)
      .subscribe((documents) => {
        this.documents = documents;
      });
  }

  handlerAddNewDocument(): void {
    console.log('aici');

    this.router.navigate(['addDocument']);
  }

  title = 'All documents';
}
