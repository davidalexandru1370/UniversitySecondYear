import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentService } from 'src/api/document.service';
import { Document } from 'src/model/Document';
@Component({
  selector: 'app-update-document',
  templateUrl: './update-document.component.html',
  styleUrls: ['./update-document.component.css'],
})
export class UpdateDocumentComponent implements OnInit {
  document: Document;

  constructor(
    private documentService: DocumentService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.document =
      this.router.getCurrentNavigation()?.extras.state!['document'];
    //document = this.route.snapshot
  }

  ngOnInit(): void {}

  handleUpdateDocument(
    author: string,
    title: string,
    numberOfPages: string,
    type: string,
    format: string
  ) {
    this.documentService
      .updateDocument({
        id: this.document.id,
        author: author,
        title: title,
        numberOfPages: parseInt(numberOfPages),
        type: type,
        format: format,
      })
      .subscribe(() => {
        this.router.navigate(['allDocuments']);
        alert('Updated succesfully');
      });
  }

  onCancel() {
    this.router.navigate(['allDocuments']);
  }
}
