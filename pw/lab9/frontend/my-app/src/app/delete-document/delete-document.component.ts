import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentService } from 'src/api/document.service';

@Component({
  selector: 'app-delete-document',
  templateUrl: './delete-document.component.html',
  styleUrls: ['./delete-document.component.css'],
})
export class DeleteDocumentComponent implements OnInit {
  documentId: string = '';
  constructor(
    private service: DocumentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.documentId = params['id'];
    });
  }

  handleConfirmButton(): void {
    if (this.documentId !== '') {
      this.service.deleteDocumentById(this.documentId).subscribe(() => {
        this.router.navigate(['allDocuments']);
      });
    }
  }

  handleCancelButton(): void {
    this.router.navigate(['allDocuments']);
  }
}
