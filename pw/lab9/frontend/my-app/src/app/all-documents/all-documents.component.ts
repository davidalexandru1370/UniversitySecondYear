import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentService } from 'src/api/document.service';
import { Document } from 'src/model/Document';

@Component({
  selector: 'app-all-documents',
  templateUrl: './all-documents.component.html',
  styleUrls: ['./all-documents.component.css'],
})
export class AllDocumentsComponent implements OnInit {
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

  handleUpdateButton(document: Document): void {
    this.router.navigate(['updateDocument'], {
      state: {
        document: document,
      },
    });
  }

  handleDeleteButton(documentId: string): void {
    this.router.navigate(['deleteDocument'], {
      queryParams: { id: documentId },
    });
  }
}
