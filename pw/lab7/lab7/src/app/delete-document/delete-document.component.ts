import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentService } from 'src/api/documentsApi/document.service';

@Component({
  selector: 'app-delete-document',
  templateUrl: './delete-document.component.html',
  styleUrls: ['./delete-document.component.css'],
})
export class DeleteDocumentComponent implements OnInit {
  documentId: number = -1;
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
    if (this.documentId !== -1) {
      this.service.deleteDocumentById(this.documentId).subscribe(() => {
        this.router.navigate(['showDocuments']);
      });
    }
  }

  handleCancelButton(): void {
    console.log('aici');
    this.router.navigate(['showDocuments']);
  }
}
