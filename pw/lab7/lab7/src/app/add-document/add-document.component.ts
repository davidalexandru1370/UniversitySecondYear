import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentService } from 'src/api/documentsApi/document.service';

@Component({
  selector: 'app-add-document',
  templateUrl: './add-document.component.html',
  styleUrls: ['./add-document.component.css'],
})
export class AddDocumentComponent implements OnInit {
  constructor(
    private documentService: DocumentService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  handleAddDocument(
    title: string,
    author: string,
    numberOfPages: string,
    type: string,
    format: string
  ): void {
    this.documentService
      .addDocument({
        author: author,
        title: title,
        numberOfPages: parseInt(numberOfPages),
        type: type,
        format: format,
      })
      .subscribe((_) => {
        this.router.navigate(['showDocuments']);
      });
  }

  onCancel() {
    this.router.navigate(['showDocuments']);
  }
}
