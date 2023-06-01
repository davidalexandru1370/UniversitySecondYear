import { Component, OnInit } from '@angular/core';
import { DocumentService } from 'src/api/document.service';
import { Document } from 'src/model/Document';

@Component({
  selector: 'app-all-documents',
  templateUrl: './all-documents.component.html',
  styleUrls: ['./all-documents.component.css'],
})
export class AllDocumentsComponent implements OnInit {
  documents: Document[] = [];

  constructor(private documentService: DocumentService) {}

  ngOnInit(): void {
    this.documentService.getAllDocuments().subscribe((documents) => {
      this.documents = documents;
    });
  }
}
