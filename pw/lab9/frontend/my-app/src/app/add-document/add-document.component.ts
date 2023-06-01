import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentService } from 'src/api/document.service';
import { DocumentDto } from 'src/model/DocumentDto';

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
    const document: DocumentDto = {
      author: author,
      format: format,
      numberOfPages: parseInt(numberOfPages),
      title: title,
      type: type,
    };
    this.documentService.addDocument(document).subscribe((_) => {
      this.router.navigate(['allDocuments']);
      alert('Added successfully');
    });
  }

  onCancel() {
    this.router.navigate(['allDocuments']);
  }
}
