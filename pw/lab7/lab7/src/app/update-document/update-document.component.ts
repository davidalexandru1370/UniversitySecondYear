import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentService } from 'src/api/documentsApi/document.service';

@Component({
  selector: 'app-update-document',
  templateUrl: './update-document.component.html',
  styleUrls: ['./update-document.component.css'],
})
export class UpdateDocumentComponent implements OnInit {
  constructor(private documentService: DocumentService, router: Router) {}

  ngOnInit(): void {}

  handleUpdateDocument(document: Document) {}

  onCancel() {}
}
