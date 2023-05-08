import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentService } from 'src/api/documentsApi/document.service';

@Component({
  selector: 'app-update-document',
  templateUrl: './update-document.component.html',
  styleUrls: ['./update-document.component.css'],
})
export class UpdateDocumentComponent implements OnInit {
  documentId: number = -1;

  constructor(
    private documentService: DocumentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.documentId = params['id'];
    });
  }

  handleUpdateDocument(
    author: string,
    title: string,
    numberOfPages: string,
    type: string,
    format: string
  ) {
    this.documentService
      .updateDocument({
        id: this.documentId,
        author: author,
        title: title,
        numberOfPages: parseInt(numberOfPages),
        type: type,
        format: format,
      })
      .subscribe(() => {
        this.router.navigate(['showDocuments']);
        alert('Updated succesfully');
      });
  }

  onCancel() {
    this.router.navigate(['showDocuments']);
  }
}
